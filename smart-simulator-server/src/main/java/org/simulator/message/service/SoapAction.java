package org.simulator.message.service;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;

import org.simulator.message.Indicator;
import org.simulator.message.receive.MessageAction;
import org.simulator.message.receive.MessageActionLocator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.simulator.soap.SoapResponse;
import org.simulator.soap.jaxb.DefaultMarshallerLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapAction implements MessageAction<SoapRequest, SoapResponse> {

	@Autowired
	private MessageActionLocator actionLocator;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SoapResponse doRequest(SoapRequest request) throws JAXBException,
			SOAPException {

		Object requestObject = getRequestObject(request);
		request.setRequestObject(requestObject);

		SoapResponse soapResponse = null;

		try {

			Indicator indicator = new OcppIndicator(
					ProtocolType.valueOf(request.getProtocol()),
					request.getCommand());

			MessageAction action = actionLocator.getAction(indicator);
			// soapResponse = clazz.newInstance().doRequest(request);

			soapResponse = (SoapResponse) action.doRequest(request);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return soapResponse;
	}

	protected Object getRequestObject(SoapRequest request)
			throws SOAPException, JAXBException {
 
		return marshallerLocator.get(ProtocolType.ocpp15).ummarshal(
				request.getSoapMessage());
	}

	@Autowired
	private DefaultMarshallerLocator marshallerLocator;
}
