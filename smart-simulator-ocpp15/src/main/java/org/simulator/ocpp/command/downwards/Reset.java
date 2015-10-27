package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.ResetRequest;
import ocpp.cp._2012._06.ResetResponse;
import ocpp.cp._2012._06.ResetStatus;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Reset extends OcppDownwardsAction<ResetRequest, ResetResponse> {

	@Override
	protected ResetResponse createResponse(SoapRequest request) {
		ResetResponse response = new ResetResponse();
		response.setStatus(ResetStatus.ACCEPTED);
		return response;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.ResetRequest.name());

	}
}
