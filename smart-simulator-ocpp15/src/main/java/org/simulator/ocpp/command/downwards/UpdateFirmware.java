package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.UpdateFirmwareRequest;
import ocpp.cp._2012._06.UpdateFirmwareResponse;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateFirmware extends
		OcppDownwardsAction<UpdateFirmwareRequest, UpdateFirmwareResponse> {

	@Override
	protected UpdateFirmwareResponse createResponse(SoapRequest request) {
		UpdateFirmwareResponse response = new UpdateFirmwareResponse();
		return response;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.UpdateFirmwareRequest.name());

	}
}
