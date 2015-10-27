package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.ChangeConfigurationRequest;
import ocpp.cp._2012._06.ChangeConfigurationResponse;
import ocpp.cp._2012._06.ConfigurationStatus;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChangeConfiguration extends OcppDownwardsAction<ChangeConfigurationRequest, ChangeConfigurationResponse> {

	@Override
	protected ChangeConfigurationResponse createResponse(SoapRequest request) {
		ChangeConfigurationResponse response = new ChangeConfigurationResponse();
		response.setStatus(ConfigurationStatus.ACCEPTED);
		return response;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.ChangeConfigurationRequest.name());

	}

}
