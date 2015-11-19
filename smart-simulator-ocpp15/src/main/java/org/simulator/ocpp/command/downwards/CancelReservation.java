package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.CancelReservationRequest;
import ocpp.cp._2012._06.CancelReservationResponse;
import ocpp.cp._2012._06.CancelReservationStatus;
import ocpp.cp._2012._06.ReserveNowRequest;

import org.common.util.soap.XMLGregorianCalendarUtil;
import org.simulator.common.Cache;
import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CancelReservation
		extends
		OcppDownwardsAction<CancelReservationRequest, CancelReservationResponse> {

	protected CancelReservationResponse createResponse(SoapRequest request) {

		CancelReservationRequest jaxb = (CancelReservationRequest) request
				.getRequestObject();
		CancelReservationResponse response = new CancelReservationResponse();

		ReserveNowRequest status = (ReserveNowRequest) Cache.get(jaxb
				.getReservationId());

		if (status != null) {
			// if reservation exists
			Cache.remove(jaxb.getReservationId());
			Cache.remove(XMLGregorianCalendarUtil.generateKey(
					request.getDeviceSerial(), status.getConnectorId()));
			response.setStatus(CancelReservationStatus.ACCEPTED);
		} else {
			response.setStatus(CancelReservationStatus.REJECTED);
		}
		return response;
	}

	@Override
	public Indicator getIndicator() {

		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.CancelReservationRequest.name());
	}

}
