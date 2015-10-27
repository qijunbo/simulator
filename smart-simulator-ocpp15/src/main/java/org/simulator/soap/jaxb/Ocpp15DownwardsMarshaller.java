package org.simulator.soap.jaxb;

import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ocpp15DownwardsMarshaller extends AbstractMarshaller {

	@Override
	String getContextPath() {
		return getProtocolType().getContextPath();
	}

	@Override
	public ProtocolType getProtocolType() {

		return ProtocolType.ocpp15;
	}

}
