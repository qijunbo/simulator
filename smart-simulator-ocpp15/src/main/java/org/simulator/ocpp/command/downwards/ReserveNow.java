package org.simulator.ocpp.command.downwards;

import javax.xml.datatype.XMLGregorianCalendar;

import ocpp.cp._2012._06.ReservationStatus;
import ocpp.cp._2012._06.ReserveNowRequest;
import ocpp.cp._2012._06.ReserveNowResponse;

import org.common.util.soap.XMLGregorianCalendarUtil;
import org.simulator.common.Cache;
import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.soap.SoapRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReserveNow extends OcppDownwardsAction<ReserveNowRequest, ReserveNowResponse> {

	@Override
	protected ReserveNowResponse createResponse(SoapRequest request) {
		ReserveNowResponse response = new ReserveNowResponse();
		ReserveNowRequest jaxb = (ReserveNowRequest) request.getRequestObject();

		ReserveNowRequest status = (ReserveNowRequest) Cache.get(XMLGregorianCalendarUtil.generateKey(request.getDeviceSerial(),
				jaxb.getConnectorId()));

		XMLGregorianCalendar calendar = XMLGregorianCalendarUtil.createXMLGregorianCalendar();

		if (status == null) {
			// if no reservation
			setReservation(request, jaxb);
			response.setStatus(ReservationStatus.ACCEPTED);
		} else if (status.getExpiryDate().compare(calendar) < 0) {
			// if reservation is expired
			setReservation(request, jaxb);
			response.setStatus(ReservationStatus.ACCEPTED);
		} else if (status.getConnectorId() != jaxb.getConnectorId()) {
			// if the connector is free
			setReservation(request, jaxb);
			response.setStatus(ReservationStatus.ACCEPTED);
		} else {

			response.setStatus(ReservationStatus.REJECTED);
		}

		return response;
	}

	public void setReservation(SoapRequest request, ReserveNowRequest jaxb) {
		// key is reservationid, so that we can find the reservation by
		// reservation id.
		Cache.put(jaxb.getReservationId(), jaxb);
		// key is also deviceid, so that we can also find the reservation by
		// device id.
		Cache.put(XMLGregorianCalendarUtil.generateKey(request.getDeviceSerial(), jaxb.getConnectorId()), jaxb);
	}
	
	
	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.ReserveNowRequest.name());

	}
}
