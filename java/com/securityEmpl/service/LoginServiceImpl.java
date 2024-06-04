package com.securityEmpl.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.securityEmpl.entity.EmployeesEntity;
import com.securityEmpl.repository.UserRepository;
import com.securityEmpl.userdetails.CustomUserDetails;

public class LoginServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
 	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeesEntity employee = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Set<GrantedAuthority> grantedAuthorities = employee.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole_name().toUpperCase()))
            .collect(Collectors.toSet());

        return new CustomUserDetails(
            employee.getEmail(),
            employee.getPassword(),
            grantedAuthorities,
            employee.getEmployee_id()
        );
    }
}
