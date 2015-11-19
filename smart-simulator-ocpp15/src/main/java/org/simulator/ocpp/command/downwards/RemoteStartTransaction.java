package org.simulator.ocpp.command.downwards;

import javax.xml.datatype.XMLGregorianCalendar;

import ocpp.cp._2012._06.RemoteStartStopStatus;
import ocpp.cp._2012._06.RemoteStartTransactionRequest;
import ocpp.cp._2012._06.RemoteStartTransactionResponse;
import ocpp.cp._2012._06.ReserveNowRequest;
import ocpp.cs._2012._06.StartTransactionRequest;

import org.common.util.soap.XMLGregorianCalendarUtil;
import org.simulator.common.Cache;
import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.message.service.MsgService;
import org.simulator.message.service.MsgServiceLocator;
import org.simulator.model.chargepoint.ChargePointRepository;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.ocpp15.command.upwards.Ocpp15Upwards;
import org.simulator.soap.SoapRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteStartTransaction extends
		OcppDownwardsAction<RemoteStartTransactionRequest, RemoteStartTransactionResponse> {

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.RemoteStartTransactionRequest.name());

	}
	
	@Override
	protected RemoteStartTransactionResponse createResponse(SoapRequest request) {

		RemoteStartTransactionRequest jaxb = (RemoteStartTransactionRequest) request
				.getRequestObject();

		Object history = Cache.get(XMLGregorianCalendarUtil.generateKey(request.getDeviceSerial(),
				jaxb.getConnectorId()));
		if (history instanceof RemoteStartTransactionRequest) {
			// some body is using this connector.
			return reject();
		}

		ReserveNowRequest status = (ReserveNowRequest) history;
		XMLGregorianCalendar calendar = XMLGregorianCalendarUtil.createXMLGregorianCalendar();
		if (status == null) {
			// if no reservation
			return accept(request, jaxb);
		} else if (jaxb.getConnectorId() != status.getConnectorId()) {
			// if using another connector.
			return accept(request, jaxb);
		} else if (status.getExpiryDate().compare(calendar) < 0) {
			// if reservation is expired
			return accept(request, jaxb);
		} else {
			return reject();
		}

	}

	public RemoteStartTransactionResponse accept(SoapRequest request,
			RemoteStartTransactionRequest jaxb) {

		// so that we can get transaction information by device id.
		Cache.put(XMLGregorianCalendarUtil.generateKey(request.getDeviceSerial(), jaxb.getConnectorId()), jaxb);
		// TODO actually we should also set key transaction id too, but
		// unfortunately we have no transaction id yet.
		// so that we can get transaction information by transaction id.
		Cache.put(jaxb.getIdTag(), jaxb);

		RemoteStartTransactionResponse response = new RemoteStartTransactionResponse();
		response.setStatus(RemoteStartStopStatus.ACCEPTED);

		sendStartTransaction(request, jaxb);

		return response;
	}

	public void sendStartTransaction(SoapRequest request, RemoteStartTransactionRequest jaxb) {

		StartTransactionRequest startTransaction = new StartTransactionRequest();
		startTransaction.setConnectorId(jaxb.getConnectorId());
		startTransaction.setIdTag(jaxb.getIdTag());
		startTransaction.setMeterStart(XMLGregorianCalendarUtil.getMeter());
		// startTransaction.setReservationId(reservationId); // not need when
		// making a remote start manually.
		startTransaction.setTimestamp(XMLGregorianCalendarUtil.createXMLGregorianCalendar());

		try {

			MsgService msgService = serviceLocator.getMsgService(MsgService.Type.soap.name());

			msgService.send(request.getDeviceSerial(), ProtocolType.ocpp15upwards.name(),
					Ocpp15Upwards.StartTransaction.name());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public RemoteStartTransactionResponse reject() {
		RemoteStartTransactionResponse response = new RemoteStartTransactionResponse();
		response.setStatus(RemoteStartStopStatus.REJECTED);
		return response;
	}

	@Autowired
	private ChargePointRepository repository;

	@Autowired
	private MsgServiceLocator serviceLocator;
}
