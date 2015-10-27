package org.simulator.message;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultMsgCreatorLocator implements MsgCreatorLocator {

	Map<String, MsgCreator<?>> map = new HashMap<String, MsgCreator<?>>();

	@Override
	public void register(AbstractMsgCreator<?> creator) {
		if (map.put(creator.getIndicator().toKey(), creator) != null) {
			throw new RuntimeException(creator.getClass().getName()
					+ " is registered more than once.");
		}
	}

	@Override
	public MsgCreator<?> get(Indicator indicator) {
		 
		return map.get(indicator.toKey());
	}

}
