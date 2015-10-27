package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.SendLocalListRequest;
import ocpp.cp._2012._06.SendLocalListResponse;
import ocpp.cp._2012._06.UpdateStatus;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendLocalList extends OcppDownwardsAction<SendLocalListRequest, SendLocalListResponse> {

	@Override
	protected SendLocalListResponse createResponse(SoapRequest request) {
		SendLocalListResponse response = new SendLocalListResponse();
		response.setStatus(UpdateStatus.ACCEPTED);
		return response;
	}
	
	
	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.SendLocalListRequest.name());

	}
}
