package com.securityEmpl.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.securityEmpl.dto.DepartmentDTO;
import com.securityEmpl.dto.JobDTO;
import com.securityEmpl.dto.ManagerDTO;
import com.securityEmpl.entity.EmployeesEntity;

public interface EmployeeService {
	List<ManagerDTO> getAllManagers();
	List<DepartmentDTO> getAllDepartments();
	List<JobDTO> getAllJobs();
	List<EmployeesEntity> findEmployeesInSameDepartment(Integer employee_id);
	ObjectNode deleteEmployee(int id);
}
