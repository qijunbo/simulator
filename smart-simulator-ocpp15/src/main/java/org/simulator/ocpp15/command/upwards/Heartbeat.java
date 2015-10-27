package org.simulator.ocpp15.command.upwards;

import ocpp.cs._2012._06.HeartbeatRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Heartbeat extends AbstractMsgCreator<HeartbeatRequest> {

	private int interval;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.Heartbeat.name());
	}

	@Override
	public HeartbeatRequest create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
