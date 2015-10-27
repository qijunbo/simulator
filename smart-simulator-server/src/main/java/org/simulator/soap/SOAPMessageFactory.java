package org.simulator.soap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;

@Configuration
public class SOAPMessageFactory implements SOAPFactory {

	private String charset = "UTF-8";

	@Autowired(required = false)
	private XmlFormatter formatter;

	@Autowired
	private MessageFactory messageFactory;

	public Document createEmptyDocument() throws ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// dbf.setNamespaceAware(true);
		return dbf.newDocumentBuilder().newDocument();

	}

	@Override
	public String toString(SOAPMessage soapMessage) throws SOAPException, IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		soapMessage.writeTo(output);

		if (formatter != null) {
			//return formatter.format(output.toString(charset));
			return formatter.format(soapMessage.getSOAPBody().getOwnerDocument());
		}

		return output.toString(charset);
	}

	@Override
	public SOAPMessage createSOAPMessage() throws SOAPException {
		SOAPMessage outputSOAPMessage = messageFactory.createMessage();
		return outputSOAPMessage;
	}

	public String getCharset() {
		return charset;
	}

	@Override
	public SOAPMessage readSOAPMessage(InputStream inputStream) throws SOAPException, IOException {
		return messageFactory.createMessage(new MimeHeaders(), inputStream);
	}

	@Override
	public SOAPMessage readSOAPMessage(String xml) throws SOAPException, IOException {
		return readSOAPMessage(new ByteArrayInputStream(xml.getBytes(charset)));
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}
}
