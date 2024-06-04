package com.securityEmpl.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.securityEmpl.dto.DepartmentDTO;
import com.securityEmpl.dto.JobDTO;
import com.securityEmpl.dto.ManagerDTO;
import com.securityEmpl.entity.DepartmentsEntity;
import com.securityEmpl.entity.EmployeesEntity;
import com.securityEmpl.entity.JobsEntity;
import com.securityEmpl.helper.ResponseManager;
import com.securityEmpl.repository.DepartmentRepository;
import com.securityEmpl.repository.EmployeeRepository;
import com.securityEmpl.repository.JobRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	
	@Override
	public List<ManagerDTO> getAllManagers() {
        return employeeRepository.findAllManagers();
    }
	
	@Override
	public List<DepartmentDTO> getAllDepartments() {
	    List<DepartmentsEntity> departments = departmentRepository.findAll();
	    return departments.stream()
	                      .map(department -> {
	                          DepartmentDTO dto = new DepartmentDTO();
	                          dto.setDepartment_id(department.getDepartment_id());  
	                          dto.setDepartment_name(department.getDepartment_name()); 
	                          return dto;
	                      })
	                      .collect(Collectors.toList());
	}
	
	@Override
	public List<JobDTO> getAllJobs(){
		List<JobsEntity> jobs = jobRepository.findAll();
		return jobs.stream()
				.map(job -> {
					JobDTO dto = new JobDTO();
					dto.setJob_id(job.getJob_id());
					dto.setJob_title(job.getJob_title());
					return dto;
				})
				.collect(Collectors.toList());
	}
	
	@Override
	public List<EmployeesEntity> findEmployeesInSameDepartment(Integer employee_id) {
	    List<EmployeesEntity> allEmployees = employeeRepository.findEmployeesInSameDepartment(employee_id);
	    Set<Integer> subordinatesIds = new HashSet<>();
	    for (EmployeesEntity emp : allEmployees) {
	        emp.getSubordinates().forEach(sub -> subordinatesIds.add(sub.getEmployee_id()));
	    }
	    return allEmployees.stream()
	            .filter(emp -> !subordinatesIds.contains(emp.getEmployee_id()))
	            .collect(Collectors.toList());
    }


	@Override
	public ObjectNode deleteEmployee(int id) {
		Optional<EmployeesEntity> employeeOptional = employeeRepository.findById(id);
		if(!employeeOptional.isPresent()) {
			return new ResponseManager(404, "director not found").getResponse();
		}
		else {
			employeeRepository.delete(employeeOptional.get());
			return new ResponseManager(202, "director deleted").getResponse();
		}
	}
}
