package org.simulator.model.audit;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditServiceImpl implements AuditService {

	@Autowired
	private OcppAuditRepository repository;

	/* (non-Javadoc)
	 * @see org.simulator.web.AuditService#audit(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public OcppAudit audit(String deviceSerial, String request, String response) {
		OcppAudit audit = new OcppAudit();
		audit.setDeviceSerial(deviceSerial);
		audit.setRequestTime(new Date());
		audit.setRequest(request);
		audit.setResponse(response);
		audit.setResponseTime(new Date());
		repository.save(audit);
		return audit;
	}

	/* (non-Javadoc)
	 * @see org.simulator.web.AuditService#auditError(java.lang.String, java.lang.String, java.lang.Exception)
	 */
	@Override
	public OcppAudit auditError(String deviceSerial, String request, Exception e) {
		OcppAudit audit = new OcppAudit();
		audit.setDeviceSerial(deviceSerial);
		audit.setRequestTime(new Date());
		audit.setRequest(request);
		audit.setError(e.getMessage());
		audit.setResponseTime(new Date());
		repository.save(audit);
		return audit;
	}

}