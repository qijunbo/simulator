package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.UnlockConnectorRequest;
import ocpp.cp._2012._06.UnlockConnectorResponse;
import ocpp.cp._2012._06.UnlockStatus;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnlockConnector extends
		OcppDownwardsAction<UnlockConnectorRequest, UnlockConnectorResponse> {

	@Override
	protected UnlockConnectorResponse createResponse(SoapRequest request) {
		UnlockConnectorResponse response = new UnlockConnectorResponse();
		response.setStatus(UnlockStatus.ACCEPTED);
		return response;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.UnlockConnectorRequest.name());

	}

}
