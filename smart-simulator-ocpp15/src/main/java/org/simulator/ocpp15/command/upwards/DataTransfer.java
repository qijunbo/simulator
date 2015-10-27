package org.simulator.ocpp15.command.upwards;

import ocpp.cp._2012._06.DataTransferRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataTransfer extends AbstractMsgCreator<DataTransferRequest> {

	private String vendorId;

	private String data;

	private String messageId;

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.DataTransfer.name());
	}

	@Override
	public DataTransferRequest create() throws Exception {

		DataTransferRequest request = new DataTransferRequest();
		request.setVendorId(vendorId);
		request.setMessageId(messageId);
		request.setData(data);
		return request;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
