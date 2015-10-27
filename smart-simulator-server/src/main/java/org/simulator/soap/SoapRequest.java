package org.simulator.soap;

import java.util.HashMap;
import java.util.Map;

import javax.xml.soap.SOAPMessage;

public class SoapRequest {

	private String protocol;

	private String deviceSerial;

	private String command;

	private SOAPMessage soapMessage;

	private Object requestObject;

	@SuppressWarnings("rawtypes")
	private Map other = new HashMap();

	public Object get(String key) {
		return other.get(key);
	}

	public String getCommand() {
		return command;
	}

	public String getProtocol() {
		return protocol;
	}

	public Object getRequestObject() {
		return requestObject;
	}

	public SOAPMessage getSoapMessage() {
		return soapMessage;
	}

	@SuppressWarnings("unchecked")
	public void put(String key, Object value) {
		other.put(key, value);
	}

	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * As defined in enum ProtocolType, all the items are in lowercase.
	 * @param protocol
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol.toLowerCase();
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public void setRequestObject(Object requestObject) {
		this.requestObject = requestObject;
	}

	public void setSoapMessage(SOAPMessage requestBody) {
		this.soapMessage = requestBody;
	}

}
