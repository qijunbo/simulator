package org.simulator.rest.audit;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;
import java.util.List;

import org.simulator.model.audit.OcppAudit;
import org.simulator.model.audit.OcppAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class AuditController {

	@Autowired
	private OcppAuditRepository repository;

	@RequestMapping(value = "/{deviceSerial}", method = GET)
	@ResponseBody
	public List<OcppAudit> getAuditByDeviceSerial(@PathVariable String deviceSerial) {

		Sort.Order order = new Order(Direction.DESC, "requestTime");
		Sort sort = new Sort(order);
		return repository.findFirst50ByDeviceSerial(deviceSerial, sort);
	}

	@RequestMapping(value = "/{deviceSerial}", method = DELETE)
	@ResponseBody
	public long deleteAuditByDeviceSerial(@PathVariable String deviceSerial) {

		return repository.deleteByDeviceSerial(deviceSerial);
	}
	
	
	@RequestMapping(value = "/{deviceSerial}/{time}", method = GET)
	@ResponseBody
	public List<OcppAudit> getAuditByDeviceSerialAfter(@PathVariable String deviceSerial, @PathVariable long time) {

		Sort.Order order = new Order(Direction.DESC, "requestTime");
		Sort sort = new Sort(order);
		List<OcppAudit> result = repository.findByDeviceSerialAndRequestTimeAfter(deviceSerial, new Date(time), sort);
		return result;
	}

}
