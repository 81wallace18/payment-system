package com.wallace18.payment.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.wallace18.payment.system.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	UserDetails findByEmail(String email);
}
