package org.simulator.message.service;

import java.util.Map;

import org.apache.http.impl.client.BasicResponseHandler;
import org.simulator.http.client.HttpClientHelper;
import org.simulator.message.Indicator;
import org.simulator.message.MsgCreator;
import org.simulator.message.MsgCreatorLocator;
import org.simulator.model.audit.AuditService;
import org.simulator.model.chargepoint.ChargePoint;
import org.simulator.model.chargepoint.ChargePointRepository;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SOAPFactory;
import org.simulator.soap.SOAPMessageDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SOAPMsgService extends BasicResponseHandler implements MsgService {

	@Autowired
	private ChargePointRepository service;

	@Autowired
	private MsgServiceLocator locator;

	@Autowired
	private MsgCreatorLocator msgCreatorLocator;

	@Autowired
	private SOAPFactory factory;

	@Autowired
	private HttpClientHelper httpClient;

	@Autowired
	private SOAPMessageDecorator decorator;

	@Autowired
	private AuditService auditService;

	@Override
	public Type getType() {
		return Type.soap;
	}

	@Override
	public String send(String deviceSerial, String protocol, String command) throws Exception {
		String soapXML = null;
		try {
			ChargePoint device = service.findBySerial(deviceSerial);

			if (device == null) {
				throw new IllegalArgumentException("Device with id " + deviceSerial
						+ " does not exist.");
			}

			Indicator indicator = new OcppIndicator(ProtocolType.valueOf(protocol.toLowerCase()),
					command);
			MsgCreator<?> creator = msgCreatorLocator.get(indicator);

			if (creator == null) {
				throw new IllegalArgumentException("Cannot find MsgCreator for "
						+ indicator.toKey());
			}

			decorator.setCreator(creator);

			soapXML = factory.toString(decorator.create());

			String response = httpClient.postSOAP(device.getCentralURL(), soapXML, this);

			auditService.audit(deviceSerial, soapXML, response);

			return response;
		} catch (Exception e) {

			auditService.auditError(deviceSerial, soapXML, e);
			throw e;
		}

	}
	
	
	

	@Override
	public String send(String device, String protocol, String command, Map<String, String> parms)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String send(String device, String protocol, Object bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setLocator(MsgServiceLocator locator) {
		this.locator = locator;
		this.locator.register(this);
	}

}
