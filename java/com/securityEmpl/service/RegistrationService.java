package com.securityEmpl.service;


import org.springframework.http.ResponseEntity;

import com.securityEmpl.dto.RegistrationRequest;

public interface RegistrationService {
	public ResponseEntity<?> register(RegistrationRequest registrationRequest);
}
