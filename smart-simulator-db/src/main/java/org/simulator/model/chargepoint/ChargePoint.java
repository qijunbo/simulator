package org.simulator.model.chargepoint;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.mongodb.util.JSON;

public class ChargePoint {

	@Id
	String id;

	String serial;

	String centralURL;

	@SuppressWarnings("unchecked")
	List<Connector> connectors = Collections.EMPTY_LIST;

	String version;

	@DateTimeFormat(iso = ISO.DATE_TIME )
	Date createDate;
	

	int heartbeat;

	public String getCentralURL() {
		return centralURL;
	}

	public List<Connector> getConnectors() {
		return connectors;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public int getHeartbeat() {
		return heartbeat;
	}

	public String getId() {
		return id;
	}

	public String getVersion() {
		return version;
	}

	public void setCentralURL(String centralURL) {
		this.centralURL = centralURL;
	}

	public void setConnectors(List<Connector> connectors) {
		this.connectors = connectors;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setHeartbeat(int heartbeat) {
		this.heartbeat = heartbeat;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Override
	public String toString() {
		return JSON.serialize(this);
	}

}
