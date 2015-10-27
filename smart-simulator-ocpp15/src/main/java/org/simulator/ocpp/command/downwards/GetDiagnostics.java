package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.GetDiagnosticsRequest;
import ocpp.cp._2012._06.GetDiagnosticsResponse;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetDiagnostics extends OcppDownwardsAction<GetDiagnosticsRequest, GetDiagnosticsResponse> {

	@Override
	protected GetDiagnosticsResponse createResponse(SoapRequest request) {
		GetDiagnosticsResponse response = new GetDiagnosticsResponse();
		response.setFileName("http://ieqq.iteye.com/blog/2114077");
		return response;
	}

	
	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.GetDiagnosticsRequest.name());

	}
}
