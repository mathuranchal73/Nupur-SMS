package com.sms.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sms.exception.ResourceNotFoundException;
import com.sms.model.User;
import  com.sms.repository.UserRepository;
import com.sms.security.UserPrincipal;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	
	 @Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		User user= userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(()-> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
				);
		return UserPrincipal.create(user);
	}

	@Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}