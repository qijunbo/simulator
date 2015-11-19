package org.simulator.web;

import javax.xml.soap.SOAPMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simulator.message.Indicator;
import org.simulator.message.receive.MessageAction;
import org.simulator.message.receive.MessageActionLocator;
import org.simulator.model.audit.AuditService;
import org.simulator.model.audit.OcppAudit;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SOAPMessageFactory;
import org.simulator.soap.SoapRequest;
import org.simulator.soap.SoapResponse;
import org.simulator.soap.jaxb.DefaultMarshallerLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/simulator/r")
public class SOAPReceiveController {
 
	private Log log = LogFactory.getLog(SOAPReceiveController.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/{protocol}/{deviceSerial}")
	@ResponseBody
	public String onReceive(@PathVariable String protocol,
			@PathVariable String deviceSerial, @RequestBody String soapXml) {
		try {
			SOAPMessage soapMessage = soapMessageFactory
					.readSOAPMessage(soapXml);
			Object jaxb = marshallerLocator.get(
					ProtocolType.valueOf(protocol.toLowerCase())).ummarshal(
					soapMessage);
			String command = jaxb.getClass().getSimpleName();
			Indicator indicator = new OcppIndicator(
					ProtocolType.valueOf(protocol.toLowerCase()), command);

			SoapRequest soapRequest = new SoapRequest();
			soapRequest.setProtocol(protocol);
			soapRequest.setDeviceSerial(deviceSerial);

			MessageAction action = actionLocator.getAction(indicator);
			SoapResponse soapResponse = (SoapResponse) action
					.doRequest(soapRequest);

			String responseBody = soapMessageFactory.toString(soapResponse
					.getResponse());

			// in this case, request and response almost happened at same time
			// and no delay.
			OcppAudit audit = auditService.audit(deviceSerial, soapXml,
					responseBody);
			if (log.isInfoEnabled()) {
				log.info(audit);
			}
			return responseBody;
		} catch (Exception e) {
			OcppAudit audit = auditService.auditError(deviceSerial, soapXml, e);
			if (log.isInfoEnabled()) {
				log.info(audit);
			}
			return String.format(template, protocol, deviceSerial, soapXml,
					e.getLocalizedMessage());
		}

	}

	@Autowired
	private AuditService auditService;

	@Autowired
	private SOAPMessageFactory soapMessageFactory;

	@Autowired
	private DefaultMarshallerLocator marshallerLocator;

	@Autowired
	private MessageActionLocator actionLocator;

	private static final String template = "Testing device on [%s] protocal, device id: %s, Message:[%s]\n exception: [%s]";

}
