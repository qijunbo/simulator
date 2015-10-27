package org.simulator.message;

public interface MsgCreator<T> {

	T create() throws Exception;

}
