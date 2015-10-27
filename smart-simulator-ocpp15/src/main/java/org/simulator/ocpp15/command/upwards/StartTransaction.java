package org.simulator.ocpp15.command.upwards;

import javax.xml.datatype.XMLGregorianCalendar;

import ocpp.cs._2012._06.StartTransactionRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartTransaction extends AbstractMsgCreator<StartTransactionRequest> {

	String idTag;

	int meter;

	int connectorId;

	int reservationId;

	XMLGregorianCalendar timestamp;

	public int getConnectorId() {
		return connectorId;
	}

	public String getIdTag() {
		return idTag;
	}

	public int getMeter() {
		return meter;
	}

	public int getReservationId() {
		return reservationId;
	}

	public XMLGregorianCalendar getTimestamp() {
		return timestamp;
	}

	public void setConnectorId(int connectorId) {
		this.connectorId = connectorId;
	}

	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}

	public void setMeter(int meter) {
		this.meter = meter;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public void setTimestamp(XMLGregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.StartTransaction.name());
	}

	@Override
	public StartTransactionRequest create() throws Exception {
		StartTransactionRequest request = new StartTransactionRequest();
		request.setConnectorId(connectorId);
		request.setIdTag(idTag);
		request.setReservationId(reservationId);
		request.setTimestamp(timestamp);
		request.setMeterStart(meter);
		return request;
	}

}
