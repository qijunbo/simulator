package org.simulator.soap.jaxb;

import java.util.HashMap;
import java.util.Map;

import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultMarshallerLocator implements MarshallerLocator {

	Map<ProtocolType, SimpleMarshaller> map = new HashMap<ProtocolType, SimpleMarshaller>();

	@Override
	public void register(AbstractMarshaller marshaller) {
		if (map.put(marshaller.getProtocolType(),  marshaller) != null) {
			throw new RuntimeException(marshaller.getClass().getName()
					+ " is registered more than once.");
		}

	}

	@Override
	public SimpleMarshaller get(ProtocolType protocol) {
		return map.get(protocol);
	}

}
