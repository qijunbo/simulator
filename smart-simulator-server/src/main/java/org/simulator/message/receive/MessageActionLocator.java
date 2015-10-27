package org.simulator.message.receive;

import org.simulator.message.Indicator;


public interface MessageActionLocator {

	void register(OcppDownwardsAction<?, ?> service);

	MessageAction<?,?>  getAction(Indicator indicator);
}
