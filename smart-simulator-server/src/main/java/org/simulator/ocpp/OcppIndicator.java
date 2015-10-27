package org.simulator.ocpp;

import org.simulator.message.Indicator;

public class OcppIndicator implements Indicator {

	private ProtocolType protocol;

	private String command;

	public OcppIndicator(ProtocolType protocol, String command) {
		super();
		this.protocol = protocol;
		this.command = command;
	}

	@Override
	public String toKey() {
		return (protocol.name() + ":" + command).toLowerCase();
	}

	public ProtocolType getProtocol() {
		return protocol;
	}

	public String getCommand() {
		return command;
	}

}
