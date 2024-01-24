package com.wallace18.payment.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wallace18.payment.system.Util.RandomString;
import com.wallace18.payment.system.entity.User;
import com.wallace18.payment.system.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerUser(User user) {
		if(userRepository.findByEmail(user.getEmail()) != null ) {
			throw new RuntimeException("This email alredy exists");
		} else {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			
			String randomCode = RandomString.generaterandomString(64);
			user.setVerificationCode(randomCode);
			user.setEnable(false);
			
			User savedUser = userRepository.save(user);
			return savedUser;
		}
	}
}
