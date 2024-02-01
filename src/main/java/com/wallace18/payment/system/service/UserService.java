package com.wallace18.payment.system.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wallace18.payment.system.Util.RandomString;
import com.wallace18.payment.system.dto.UserResponse;
import com.wallace18.payment.system.entity.User;
import com.wallace18.payment.system.repository.UserRepository;

import jakarta.mail.MessagingException;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailService mailService;
	
	public UserResponse registerUser(User user) throws UnsupportedEncodingException, MessagingException {
		if(userRepository.findByEmail(user.getEmail()) != null ) {
			throw new RuntimeException("This email alredy exists");
		} else {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			
			String randomCode = RandomString.generaterandomString(64);
			user.setVerificationCode(randomCode);
			user.setEnable(false);
			
			User savedUser = userRepository.save(user);
			UserResponse userResponse = new UserResponse(savedUser.getId(), 
					savedUser.getName(), 
					savedUser.getEmail(), 
					savedUser.getPassword());
			
			mailService.senderVerificationEmail(user);
			return userResponse;
		}
	}
	
	public boolean verify(String verificationCode) {
		User user = userRepository.findByVerificationCode(verificationCode);
		
		if(user == null || user.isEnable()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnable(true);
			userRepository.save(user);
		
			return true;
		}
	}
	
}
