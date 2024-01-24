package com.wallace18.payment.system.dto;

import com.wallace18.payment.system.entity.User;

public record UserRequest(String name, String email, String password) {
	
	public User toModel() {
		return new User(name, email, password);
	}
}
