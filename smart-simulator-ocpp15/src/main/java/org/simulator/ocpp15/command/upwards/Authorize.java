package org.simulator.ocpp15.command.upwards;

import ocpp.cs._2012._06.AuthorizeRequest;

import org.simulator.message.AbstractMsgCreator;
import org.simulator.message.Indicator;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Authorize extends AbstractMsgCreator<AuthorizeRequest>
{

	private String idTag;

	public String getIdTag() {
		return idTag;
	}

	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}

	@Override
	public Indicator getIndicator() {
		return new OcppIndicator(ProtocolType.ocpp15upwards, Ocpp15Upwards.Authorize.name());
	}

	@Override
	public AuthorizeRequest create() throws Exception {
		AuthorizeRequest request = new AuthorizeRequest();
		request.setIdTag(idTag);
		return request;
	}

}
