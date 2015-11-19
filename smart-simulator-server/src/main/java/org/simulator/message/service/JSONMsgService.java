package org.simulator.message.service;

import java.util.Map;

import org.apache.http.impl.client.BasicResponseHandler;
import org.simulator.http.client.HttpClientHelper;
import org.simulator.message.Indicator;
import org.simulator.message.MsgCreator;
import org.simulator.message.MsgCreatorLocator;
import org.simulator.model.audit.AuditService;
import org.simulator.model.chargepoint.ChargePoint;
import org.simulator.model.chargepoint.ChargePointRepository;
import org.simulator.ocpp.OcppIndicator;
import org.simulator.ocpp.ProtocolType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class JSONMsgService extends BasicResponseHandler implements MsgService {

	private MsgServiceLocator locator;

	@Autowired
	private ChargePointRepository service;

	@Autowired
	private HttpClientHelper httpClient;

	@Autowired
	private Gson gson;

	@Autowired
	private MsgCreatorLocator msgCreatorLocator;

	@Autowired
	private AuditService auditService;

	@Override
	public Type getType() {

		return Type.json;
	}

	@Override
	public String send(String deviceSerial, String protocol, String command) throws Exception {

		String message = null;

		try {
			ChargePoint device = service.findBySerial(deviceSerial);

			if (device == null) {
				throw new IllegalArgumentException("Device with id " + deviceSerial
						+ " does not exist.");
			}

			Indicator indicator = new OcppIndicator(ProtocolType.valueOf(protocol.toLowerCase()),
					command);

			MsgCreator<?> creator = msgCreatorLocator.get(indicator);

			if (creator == null) {
				throw new IllegalArgumentException("Cannot find MsgCreator for "
						+ indicator.toKey());
			}

			message = gson.toJson(creator.create());

			String response = httpClient.postJSON(device.getCentralURL(), message, this);

			auditService.audit(deviceSerial, message, response);

			return response;

		} catch (Exception e) {
			auditService.auditError(deviceSerial, message, e);
			throw e;
		}

	}

	@Override
	public String send(String device, String protocol, String command, Map<String, String> parms)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String send(String device, String protocol, Object bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setLocator(MsgServiceLocator locator) {
		this.locator = locator;
		this.locator.register(this);
	}

}
