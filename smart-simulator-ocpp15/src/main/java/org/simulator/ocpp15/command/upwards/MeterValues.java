package org.simulator.ocpp15.command.upwards;

import ocpp.cs._2012._06.MeterValuesRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeterValues extends AbstractMsgCreator<MeterValuesRequest> {

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.MeterValues.name());
	}

	@Override
	public MeterValuesRequest create() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
