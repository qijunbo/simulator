package org.simulator.message.service;

public interface MsgServiceLocator {

	void register(MsgService service);

	MsgService getMsgService(String type);

}
