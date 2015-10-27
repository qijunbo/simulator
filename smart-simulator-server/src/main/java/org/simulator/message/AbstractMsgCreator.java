package org.simulator.message;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMsgCreator<T> implements MsgCreator<T> {


	private MsgCreatorLocator locator;

	@Autowired
	public void setLocator(MsgCreatorLocator locator) {
		this.locator = locator;
		this.locator.register(this);
	}

	public abstract Indicator getIndicator();

}
