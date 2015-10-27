package org.simulator.soap;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public interface SOAPFactory {

	SOAPMessage readSOAPMessage(InputStream inputStream) throws SOAPException, IOException;

	SOAPMessage readSOAPMessage(String xml) throws SOAPException, IOException;

	String toString(SOAPMessage soapMessage) throws SOAPException, IOException;

	SOAPMessage createSOAPMessage() throws SOAPException;
}
