package org.simulator.message.receive;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;

public interface MessageAction<Request, Response> {
	public Response doRequest(Request request) throws JAXBException, SOAPException;
}
