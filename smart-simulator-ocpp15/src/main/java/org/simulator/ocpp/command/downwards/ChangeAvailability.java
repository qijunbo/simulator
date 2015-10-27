package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.AvailabilityStatus;
import ocpp.cp._2012._06.ChangeAvailabilityRequest;
import ocpp.cp._2012._06.ChangeAvailabilityResponse;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChangeAvailability
		extends
		OcppDownwardsAction<ChangeAvailabilityRequest, ChangeAvailabilityResponse> {

	protected ChangeAvailabilityResponse createResponse(SoapRequest request) {
		ChangeAvailabilityResponse response = new ChangeAvailabilityResponse();
		response.setStatus(AvailabilityStatus.ACCEPTED);
		return response;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.ChangeAvailabilityRequest.name());

	}

}
