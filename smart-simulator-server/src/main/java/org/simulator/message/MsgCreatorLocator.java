package org.simulator.message;

public interface MsgCreatorLocator {

	void register(AbstractMsgCreator<?> creator);

	MsgCreator<?> get(Indicator indicator);

}
