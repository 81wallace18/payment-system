package com.wallace18.payment.system.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallace18.payment.system.dto.UserRequest;
import com.wallace18.payment.system.dto.UserResponse;
import com.wallace18.payment.system.entity.User;
import com.wallace18.payment.system.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) throws UnsupportedEncodingException, MessagingException{
		User user = userRequest.toModel();
		UserResponse userSaved = userService.registerUser(user);
		return ResponseEntity.ok().body(userSaved);
	}
	
	@GetMapping("/verify")
	public String verifyuser(@Param("code") String code) {
		if(userService.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		}
	}
}
