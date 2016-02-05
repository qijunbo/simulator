package org.simulator.user.model;

import org.springframework.data.annotation.Id;

import com.mongodb.util.JSON;

public class User {

	@Id
	private String email;

	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return JSON.serialize(this);
	}

}
