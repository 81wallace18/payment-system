package com.wallace18.payment.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallace18.payment.system.dto.UserRequest;
import com.wallace18.payment.system.entity.User;
import com.wallace18.payment.system.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest){
		User user = userRequest.toModel();
		User userSaved = userService.registerUser(user);
		return ResponseEntity.ok().body(userSaved);
	}
}
