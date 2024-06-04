package com.securityEmpl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.securityEmpl.dto.DeleteRequestDTO;
import com.securityEmpl.service.EmployeeService;

@RestController
public class PermissionController {
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/deleteUser")
	@PreAuthorize("hasPermission(#id, 'delete')")
	public ResponseEntity<?> delete(@RequestBody DeleteRequestDTO id){
		employeeService.deleteEmployee(id.getId());
		return ResponseEntity.ok(Map.of("risposta", "Deleted"));
	}
}
