package org.simulator.soap.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SOAPFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

public abstract  class AbstractMarshaller implements SimpleMarshaller {

	public abstract ProtocolType getProtocolType();

	private MarshallerLocator locator;

	@Autowired
	public void setLocator(MarshallerLocator locator) {
		this.locator = locator;
		this.locator.register(this);
	}


	@Autowired
	private SOAPFactory factory;
	
	public JAXBContext getJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(getContextPath());
	}

	public Object ummarshal(Document document) throws JAXBException {

		Unmarshaller u = getJAXBContext().createUnmarshaller();
		@SuppressWarnings("rawtypes")
		JAXBElement jaxb = (JAXBElement) u.unmarshal(document);
		return jaxb.getValue();

	}
	
	@Override
	public Object ummarshal(SOAPMessage soapMessage) throws JAXBException,
			SOAPException {
		return ummarshal(soapMessage.getSOAPBody().getOwnerDocument());

	}

	public Document marshal(Object jaxb) throws ParserConfigurationException, JAXBException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// dbf.setNamespaceAware(true);
		Document document = dbf.newDocumentBuilder().newDocument();

		Marshaller m = getJAXBContext().createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(jaxb, document);
		return document;
	}
	
	
	@Override
	public SOAPMessage marshalSOAP(Object jaxb)
			throws ParserConfigurationException, JAXBException, SOAPException {
		SOAPMessage message = factory.createSOAPMessage();
		message.getSOAPBody().addDocument(marshal(jaxb));
		return message;
	}

	abstract String getContextPath();

}
