package org.simulator.ocpp.command.downwards;

import ocpp.cp._2012._06.RemoteStartStopStatus;
import ocpp.cp._2012._06.RemoteStartTransactionRequest;
import ocpp.cp._2012._06.RemoteStopTransactionRequest;
import ocpp.cp._2012._06.RemoteStopTransactionResponse;
import ocpp.cs._2012._06.StopTransactionRequest;

import org.common.util.soap.XMLGregorianCalendarUtil;
import org.simulator.common.Cache;
import org.simulator.message.Indicator;
import org.simulator.message.receive.OcppDownwardsAction;
import org.simulator.message.service.MsgService;
import org.simulator.message.service.MsgServiceLocator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.simulator.ocpp15.command.upwards.Ocpp15Upwards;
import org.simulator.soap.SoapRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteStopTransaction extends
		OcppDownwardsAction<RemoteStopTransactionRequest, RemoteStopTransactionResponse> {

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15,
				Ocpp15Downwards.RemoteStopTransactionRequest.name());

	}
	
	@Override
	protected RemoteStopTransactionResponse createResponse(SoapRequest request) {

		RemoteStopTransactionRequest jaxb = (RemoteStopTransactionRequest) request
				.getRequestObject();
		RemoteStartTransactionRequest status = (RemoteStartTransactionRequest) Cache.get(jaxb
				.getTransactionId());
		if (status != null) {
			// if transaction exists
			Cache.remove(XMLGregorianCalendarUtil.generateKey(request.getDeviceSerial(), status.getConnectorId()));
			Cache.remove(jaxb.getTransactionId());

			sendStopTransaction(request, jaxb, status);

			return accept();
		} else {
			return reject();
		}

	}

	public void sendStopTransaction(SoapRequest request, RemoteStopTransactionRequest jaxb,
			RemoteStartTransactionRequest status) {
		StopTransactionRequest stopTransaction = new StopTransactionRequest();
		stopTransaction.setIdTag(status.getIdTag());
		stopTransaction.setMeterStop(XMLGregorianCalendarUtil.getMeter());
		stopTransaction.setTimestamp(XMLGregorianCalendarUtil.createXMLGregorianCalendar());
		stopTransaction.setTransactionId(jaxb.getTransactionId());

		try {
			MsgService msgService = serviceLocator.getMsgService(MsgService.Type.soap.name());

			msgService.send(request.getDeviceSerial(), ProtocolType.ocpp15upwards.name(),
					Ocpp15Upwards.StopTransaction.name());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public RemoteStopTransactionResponse accept() {

		RemoteStopTransactionResponse response = new RemoteStopTransactionResponse();
		response.setStatus(RemoteStartStopStatus.ACCEPTED);
		return response;
	}

	public RemoteStopTransactionResponse reject() {
		RemoteStopTransactionResponse response = new RemoteStopTransactionResponse();
		response.setStatus(RemoteStartStopStatus.REJECTED);
		return response;
	}

	@Autowired
	private MsgServiceLocator serviceLocator;
}
