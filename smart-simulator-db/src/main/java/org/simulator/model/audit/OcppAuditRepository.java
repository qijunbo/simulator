package org.simulator.model.audit;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OcppAuditRepository extends PagingAndSortingRepository<OcppAudit, String> {
	
	public List<OcppAudit> findById(String id, Sort sort);
	
	public List<OcppAudit> findByDeviceSerial(String deviceSerial, Sort sort);
	
	public List<OcppAudit> findFirst50ByDeviceSerial(String deviceSerial, Sort sort);
	
	public List<OcppAudit> findByDeviceSerialAndRequestTimeAfter(String deviceSerial, Date date, Sort sort);
	
	public long deleteByDeviceSerial(String deviceSerial);
	
}
