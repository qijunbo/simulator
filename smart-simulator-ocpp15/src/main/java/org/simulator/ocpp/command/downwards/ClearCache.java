package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.ClearCacheRequest;
import ocpp.cp._2012._06.ClearCacheResponse;
import ocpp.cp._2012._06.ClearCacheStatus;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClearCache extends OcppDownwardsAction<ClearCacheRequest, ClearCacheResponse> {

	@Override
	protected ClearCacheResponse createResponse(SoapRequest request) {
		ClearCacheResponse cacheResponse = new ClearCacheResponse();
		cacheResponse.setStatus(ClearCacheStatus.ACCEPTED);
		return cacheResponse;
	}
	
	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.ClearCacheRequest.name());

	}

}
