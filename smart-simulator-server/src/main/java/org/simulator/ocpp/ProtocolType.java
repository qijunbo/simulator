package org.simulator.ocpp;

/**
 * OCPP12 means OCPP12Downwards, the word "Downwards" is omitted, because
 * downwards is must often used in simulator, we want to make it shorter.
 * 
 * @author qijunbo
 *
 */

public enum ProtocolType {

	ocpp12("urn://Ocpp/Cp/2010/08/", "ocpp.cp._2010._08"),

	ocpp12upwards("urn://Ocpp/Cs/2010/08/", "ocpp.cs._2010._08"),

	ocpp15("urn://Ocpp/Cp/2012/06/", "ocpp.cp._2012._06"),

	ocpp15upwards("urn://Ocpp/Cs/2012/06/", "ocpp.cs._2012._06"),

	ocpp20("NotSupportedYet", "NotSupportedYet"),

	ocpp20upwards("NotSupportedYet", "NotSupportedYet");

	private String namespaceURI;

	private String contextPath;

	ProtocolType(String namespaceURI, String contextPath) {
		this.namespaceURI = namespaceURI;
		this.contextPath = contextPath;
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getNamespaceURI() {
		return namespaceURI;
	}

}
