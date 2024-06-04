package com.securityEmpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.securityEmpl.dto.DepartmentDTO;
import com.securityEmpl.dto.JobDTO;
import com.securityEmpl.dto.ManagerDTO;
import com.securityEmpl.dto.RegistrationRequest;
import com.securityEmpl.service.EmployeeService;
import com.securityEmpl.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	EmployeeService employeeService;
	
	
	@PostMapping("/registerUser")
	public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest){
		return registrationService.register(registrationRequest);
	}
	
	@GetMapping("/getJobs")
	public ResponseEntity<?> getAllJobs() {
	    List<JobDTO> jobs = employeeService.getAllJobs();
	    return ResponseEntity.ok(jobs);
	}

	@GetMapping("/getManagers")
	public ResponseEntity<?> getAllManagers(){
		List<ManagerDTO> managers = employeeService.getAllManagers();
		return ResponseEntity.ok(managers);
	}
	
	
	@GetMapping("/getDepartments")
	public ResponseEntity<?> getDepartments(){
		List<DepartmentDTO> departments = employeeService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
}
