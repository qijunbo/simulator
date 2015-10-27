package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.DataTransferResponse;
import ocpp.cp._2012._06.DataTransferStatus;
import ocpp.cs._2012._06.DataTransferRequest;

import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;

public class DataTransfer extends OcppDownwardsAction<DataTransferRequest, DataTransferResponse> {
	
	public DataTransfer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DataTransferResponse createResponse(SoapRequest request) {
		DataTransferResponse cacheResponse = new DataTransferResponse();
		cacheResponse.setStatus(DataTransferStatus.ACCEPTED);
		return cacheResponse;
	}
	
	
	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.DataTransferRequest.name());

	}

}
