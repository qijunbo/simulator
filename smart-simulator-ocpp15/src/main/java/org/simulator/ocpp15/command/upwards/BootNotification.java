package org.simulator.ocpp15.command.upwards;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

import ocpp.cs._2012._06.BootNotificationRequest;

@Configuration
public class BootNotification extends AbstractMsgCreator<BootNotificationRequest> {

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.BootNotification.name());
	}

	@Override
	public BootNotificationRequest create() throws Exception {
		BootNotificationRequest request = new BootNotificationRequest();
		return request;
	}

}
