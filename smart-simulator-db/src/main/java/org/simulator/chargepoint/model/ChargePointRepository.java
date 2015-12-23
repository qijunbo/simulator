package org.simulator.chargepoint.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChargePointRepository extends MongoRepository<ChargePoint, String> {

	public ChargePoint findBySerial(String serial);
}
