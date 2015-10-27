package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.GetConfigurationRequest;
import ocpp.cp._2012._06.GetConfigurationResponse;
import ocpp.cp._2012._06.KeyValue;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetConfiguration extends OcppDownwardsAction<GetConfigurationRequest, GetConfigurationResponse> {

	@Override
	protected GetConfigurationResponse createResponse(SoapRequest request) {
		GetConfigurationResponse response = new GetConfigurationResponse();
		KeyValue keyValue = new KeyValue();
		keyValue.setKey("Simulator Test");
		keyValue.setValue("10000");
		response.getConfigurationKey().add(keyValue);
		return response;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.GetConfigurationRequest.name());

	}
}
