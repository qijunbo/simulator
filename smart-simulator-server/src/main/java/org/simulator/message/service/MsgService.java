package org.simulator.message.service;

import java.util.Map;

public interface MsgService {

	public static enum Type {
		json, soap
	}

	Type getType();

	/**
	 * 
	 * @param device
	 * @param protocol
	 * @param command
	 * @return the response message from client.
	 * @throws Exception
	 */
	public String send(String device, String protocol, String command) throws Exception;

	/**
	 * 
	 * @param device
	 * @param protocol
	 * @param command
	 * @param parms
	 * @return the response message from client.
	 * @throws Exception
	 */
	public String send(String device, String protocol, String command, Map<String, String> parms)
			throws Exception;

	public String send(String device, String protocol, Object bean) throws Exception;
}
