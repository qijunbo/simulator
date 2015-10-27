package org.simulator.ocpp15.command.upwards;

import ocpp.cs._2012._06.FirmwareStatusNotificationRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmwareStatusNotification extends
		AbstractMsgCreator<FirmwareStatusNotificationRequest> {

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.FirmwareStatusNotification.name());
	}

	@Override
	public FirmwareStatusNotificationRequest create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
