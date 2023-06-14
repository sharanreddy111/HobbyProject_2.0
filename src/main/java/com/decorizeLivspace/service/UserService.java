package com.decorizeLivspace.service;

import com.decorizeLivspace.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.decorizeLivspace.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
