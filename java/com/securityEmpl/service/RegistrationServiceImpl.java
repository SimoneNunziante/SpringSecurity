package com.securityEmpl.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securityEmpl.dto.RegistrationRequest;
import com.securityEmpl.entity.EmployeesEntity;
import com.securityEmpl.repository.DepartmentRepository;
import com.securityEmpl.repository.JobRepository;
import com.securityEmpl.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	@Override
	public ResponseEntity<?> register(RegistrationRequest registrationRequest) {
		boolean userAlreadyExists = userRepository.existsByEmail(registrationRequest.getEmail());
		
		if(userAlreadyExists) {
			return ResponseEntity.ok().body(Map.of("exist", userAlreadyExists));
		}
		else {
			String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());	
			EmployeesEntity user = new EmployeesEntity();
			user.setFirst_name(registrationRequest.getName());
			user.setLast_name(registrationRequest.getSurname());
			user.setEmail(registrationRequest.getEmail());
			user.setPassword(encodedPassword);
			user.setPhone_number(registrationRequest.getNumber());
			user.setHire_date(registrationRequest.getHire_date());
			
			user.setJob_id(jobRepository.findById(registrationRequest.getJob())
					.orElseThrow(() -> new RuntimeException("Job not found")));
			user.setManager_id(userRepository.findById(registrationRequest.getManager())
					.orElseThrow(() -> new RuntimeException("Manager not found")));
			user.setDepartment_id(departmentRepository.findById(registrationRequest.getDepartment())
					.orElseThrow(() -> new RuntimeException("Department not found")));
			
			userRepository.save(user);
			
			return ResponseEntity.ok().body(Map.of("user saved", userAlreadyExists));
		}
	}
}
