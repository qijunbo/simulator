package org.simulator.model.audit;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class OcppAudit {

	private static final String template = "[%s]: ID: [%s], DeviceSerial:[%s] Transaction ID: [%s], \nRequest: [%s], \nResponse: [%s], \nError: [%s]";

	@Id
	String id;

	String deviceSerial;

	String transactionId;

	String request;

	Date requestTime;

	String response;

	Date responseTime;

	String error;

	public OcppAudit() {
	}

 

	public String getError() {
		return error;
	}

	public String getId() {
		return id;
	}

	public String getRequest() {
		return request;
	}

	public String getResponse() {
		return response;
	}


	public String getTransactionId() {
		return transactionId;
	}

 

	public void setError(String error) {
		this.error = error;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRequest(String request) {
		this.request = request;
		 
	}

	public void setResponse(String response) {
		this.response = response;
		 
	}

 
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	@Override
	public String toString() {

		return String.format(template, this.getClass().getName(), this.getId(), getDeviceSerial(),
				getTransactionId(), getRequest(), getResponse(), getError());
	}
}
