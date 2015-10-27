package org.simulator.ocpp15.command.upwards;

import javax.xml.datatype.XMLGregorianCalendar;

import ocpp.cs._2012._06.StopTransactionRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StopTransaction extends AbstractMsgCreator<StopTransactionRequest> {

	String idTag;

	int transactionId;

	int meter;

	private XMLGregorianCalendar timestamp;

	public String getIdTag() {
		return idTag;
	}

	public int getMeter() {
		return meter;
	}

	public XMLGregorianCalendar getTimestamp() {
		return timestamp;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}

	public void setMeter(int meter) {
		this.meter = meter;
	}

	public void setTimestamp(XMLGregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.StopTransaction.name());
	}

	@Override
	public StopTransactionRequest create() throws Exception {
		StopTransactionRequest request = new StopTransactionRequest();
		request.setIdTag(idTag);
		request.setMeterStop(meter);
		request.setTimestamp(timestamp);
		request.setTransactionId(transactionId);
		return request;
	}

}
