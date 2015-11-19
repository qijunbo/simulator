package org.simulator.web;

import org.simulator.message.service.MsgService;
import org.simulator.message.service.MsgServiceLocator;
import org.simulator.model.audit.OcppAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simulator/send")
public class SOAPSendController {

	private static final String template = "Sending command [%s] to devicd [%s] on protocol [%s]. exception:[%s]";

	@RequestMapping("/{format}/{protocol}/{command}/{deviceSerial}")
	public String doPost(@PathVariable String format,
			@PathVariable String protocol, @PathVariable String deviceSerial,
			@PathVariable String command,
			@RequestBody(required = false) String body) {

		try {
			MsgService msgService = serviceLocator.getMsgService(format
					.toLowerCase());
			return msgService.send(deviceSerial, protocol, command);

		} catch (Exception e) {
			return String.format(template, body, deviceSerial, protocol, e);
		}

	}

	@Autowired
	private OcppAuditRepository repository;

	@Autowired
	private MsgServiceLocator serviceLocator;

}
