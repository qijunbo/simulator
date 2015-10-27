package org.simulator.soap.jaxb;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;

public interface SimpleMarshaller {

	Object ummarshal(Document document) throws JAXBException;

	Object ummarshal(SOAPMessage document) throws JAXBException, SOAPException;

	Document marshal(Object jaxb) throws ParserConfigurationException, JAXBException;

	SOAPMessage marshalSOAP(Object jaxb) throws ParserConfigurationException,
			JAXBException, SOAPException;

}
