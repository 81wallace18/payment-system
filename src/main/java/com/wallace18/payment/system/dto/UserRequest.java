package com.wallace18.payment.system.dto;

import com.wallace18.payment.system.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
		@NotNull(message =  "O nome não pode ser null") 
		@NotBlank(message = "O nome não pode está vazio") 
		String name,
		
		@NotNull(message =  "O email não pode ser null") 
		@NotBlank(message = "O email não pode está vazio") 
		@Email
		String email,
		
		@NotNull(message =  "A senha não pode ser null") 
		@NotBlank(message = "A senha não pode está vazio") 
		@Size(message = "a senha deve ter no mínimo 8 caracteres")
		String password) {
	
	public User toModel() {
		return new User(name, email, password);
	}
}
