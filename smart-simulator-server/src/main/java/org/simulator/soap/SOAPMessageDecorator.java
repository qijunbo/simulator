package org.simulator.soap;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPMessage;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.MsgCreator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.soap.jaxb.MarshallerLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;

@Configuration
public class SOAPMessageDecorator implements MsgCreator<SOAPMessage> {

	private MsgCreator<?> creator;

	public void setCreator(MsgCreator<?> creator) {
		this.creator = creator;
	}

	@Autowired
	private SOAPFactory soapFactory;

	@Autowired
	private MarshallerLocator locator;

	@Override
	public SOAPMessage create() throws ParserConfigurationException, JAXBException, Exception {
		SOAPMessage result = soapFactory.createSOAPMessage();

		OcppIndicator createIndicator = (OcppIndicator) ((AbstractMsgCreator<?>) creator)
				.getIndicator();
		 

		Document doc = locator.get(createIndicator.getProtocol()).marshal(creator.create());
		result.getSOAPBody().addDocument(doc);
		return result;
	}

	public SOAPMessageDecorator(AbstractMsgCreator<?> creator) {
		super();
		this.creator = creator;
	}

	public SOAPMessageDecorator() {

	}
}
