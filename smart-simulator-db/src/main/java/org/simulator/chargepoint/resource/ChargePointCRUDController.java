package org.simulator.chargepoint.resource;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Date;
import java.util.List;

import org.simulator.chargepoint.model.ChargePoint;
import org.simulator.chargepoint.model.ChargePointRepository;
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

		Date createDate = new Date();
        chargePoint.setCreateDate(createDate);
		chargePoint.setCreateDate(createDate);
		chargePoint = repository.save(chargePoint);
		return chargePoint;
	}

	@RequestMapping(method = PUT)
	public @ResponseBody boolean update(@RequestBody ChargePoint chargePoint) {

		if (repository.exists(chargePoint.getId())) {
			chargePoint.setUpdateDate(new Date());
			chargePoint = repository.save(chargePoint);
			return true;
		}
		return false;
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
		return repository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public @ResponseBody String delete(@PathVariable String id) {
		repository.delete(id);
		return null;
	}
}
