package org.simulator.soap.jaxb;

import org.simulator.ocpp.ProtocolType;

public interface MarshallerLocator {
	
	void register(AbstractMarshaller creator);

	SimpleMarshaller get(ProtocolType protocol);

}
