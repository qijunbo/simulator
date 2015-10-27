package org.simulator.message.receive;

import java.util.HashMap;
import java.util.Map;

import org.simulator.message.Indicator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultMessageActionLocator implements MessageActionLocator {

	Map<String, MessageAction<?, ?>> map = new HashMap<String, MessageAction<?, ?>>();

	@Override
	public void register(OcppDownwardsAction<?, ?> service) {
		if (map.put(service.getIndicator().toKey(), service) != null) {
			throw new RuntimeException(service.getClass().getName()
					+ " is registered more than once.");
		}

	}

	@Override
	public MessageAction<?, ?> getAction(Indicator indicator) {
		return map.get(indicator.toKey());
	}

}
