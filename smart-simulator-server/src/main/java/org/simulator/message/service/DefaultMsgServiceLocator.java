package org.simulator.message.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultMsgServiceLocator implements MsgServiceLocator{

	Map<String, MsgService> map = new HashMap<String, MsgService>();
	
	@Override
	public void register(MsgService service) {
		if (map.put(service.getType().name() , service) != null) {
			throw new RuntimeException(service.getClass().getName()
					+ " is registered more than once.");
		}
		
	}

	@Override
	public MsgService getMsgService(String type) {

		return map.get(type.toLowerCase());
	}
	
 
	
}
