package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.GetLocalListVersionRequest;
import ocpp.cp._2012._06.GetLocalListVersionResponse;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetLocalListVersion extends OcppDownwardsAction<GetLocalListVersionRequest, GetLocalListVersionResponse> {

	@Override
	protected GetLocalListVersionResponse createResponse(SoapRequest request) {
		GetLocalListVersionResponse response = new GetLocalListVersionResponse();
		response.setListVersion(2);
		return response;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.GetLocalListVersionRequest.name());

	}
	
}
