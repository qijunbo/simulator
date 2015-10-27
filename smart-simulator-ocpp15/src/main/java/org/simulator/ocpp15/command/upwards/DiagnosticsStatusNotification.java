package org.simulator.ocpp15.command.upwards;

import ocpp.cs._2012._06.DiagnosticsStatus;
import ocpp.cs._2012._06.DiagnosticsStatusNotificationRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiagnosticsStatusNotification extends
		AbstractMsgCreator<DiagnosticsStatusNotificationRequest> {

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.DiagnosticsStatusNotification.name());
	}

	@Override
	public DiagnosticsStatusNotificationRequest create() throws Exception {
		DiagnosticsStatusNotificationRequest request = new DiagnosticsStatusNotificationRequest();
		request.setStatus(DiagnosticsStatus.UPLOADED);
		return request;
	}

}
