package org.simulator.rest.chargepoint;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import org.simulator.model.chargepoint.ChargePoint;
import org.simulator.model.chargepoint.ChargePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController	
@RequestMapping("/chargepoint")
public class ChargePointCRUDController {

	@Autowired
	private ChargePointRepository repository;

	 
	@RequestMapping(method = POST)
	public @ResponseBody ChargePoint create(@RequestBody ChargePoint chargePoint) {

		chargePoint.setCreateDate(new Date());
		chargePoint = repository.save(chargePoint);
		return chargePoint;
	}

	/**
	 * @return all the ChargePoints
	 */
	@RequestMapping(method = GET)
	public @ResponseBody List<ChargePoint> get() {
		return repository.findAll();
	}

	/**
	 * @param id
	 *            the identifier of the charge point
	 * @return charge point
	 */
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody ChargePoint get(@PathVariable String id) {
		return repository.findById(id);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public @ResponseBody String delete(@PathVariable String id) {
		repository.delete(id);
		return null;
	}
}
