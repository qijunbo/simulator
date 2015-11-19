package org.simulator.model.chargepoint;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChargePointRepository extends MongoRepository<ChargePoint, String>{

	public ChargePoint findById(String id);
	
	
	public ChargePoint findBySerial(String serial);
}
