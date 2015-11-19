package org.simulator.message.receive;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.simulator.message.Indicator;
import org.simulator.model.chargepoint.ChargePointRepository;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SOAPMessageFactory;
import org.simulator.soap.SoapRequest;
import org.simulator.soap.SoapResponse;
import org.simulator.soap.jaxb.MarshallerLocator;
import org.simulator.soap.jaxb.SimpleMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

public abstract class OcppDownwardsAction<Req, Conf> implements
		MessageAction<SoapRequest, SoapResponse> {

	private MessageActionLocator locator;

	@Autowired
	public void setLocator(MessageActionLocator locator) {
		this.locator = locator;
		this.locator.register(this);
	}

	public abstract Indicator getIndicator();

	protected abstract Conf createResponse(SoapRequest request);

	protected static final String DEVICE = "DEVICE";

	@Override
	public SoapResponse doRequest(SoapRequest request) throws JAXBException,
			SOAPException {

		Object device = service.findBySerial(request.getDeviceSerial());

		if (device == null) {
			throw new IllegalArgumentException("Device with id "
					+ request.getDeviceSerial() + " does not exist.");
		}

		request.put(DEVICE, device);

		SoapResponse ocppResponse = new SoapResponse();
		try {
			ocppResponse.setResponse(createSOAPMessage(request));
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		return ocppResponse;
	}

	private SOAPMessage createSOAPMessage(SoapRequest request)
			throws SOAPException, ParserConfigurationException, JAXBException {

		ProtocolType protocol = ProtocolType.valueOf(request.getProtocol());

		SimpleMarshaller marshaller = marshallerLocator.get(protocol);

		Document document = marshaller.marshal(createResponse(request));
		SOAPMessage soapMessage = soapMessageFactory.createSOAPMessage();
		soapMessage.getSOAPBody().addDocument(document);
		return soapMessage;

	}

	@Autowired(required = true)
	private ChargePointRepository service;

	@Autowired
	private MarshallerLocator marshallerLocator;

	@Autowired
	private SOAPMessageFactory soapMessageFactory;

}
