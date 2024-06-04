package com.securityEmpl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.securityEmpl.dto.ManagerDTO;
import com.securityEmpl.entity.EmployeesEntity;

public interface EmployeeRepository extends JpaRepository<EmployeesEntity, Integer>{
    
	@Query("SELECT new com.securityEmpl.dto.ManagerDTO(e.employee_id, e.first_name, e.last_name, e.email) FROM EmployeesEntity e WHERE e.employee_id IN (SELECT DISTINCT m.manager_id.employee_id FROM EmployeesEntity m WHERE m.manager_id IS NOT NULL)")
	List<ManagerDTO> findAllManagers();

	 @Query("SELECT e FROM EmployeesEntity e WHERE e.department_id = (SELECT e2.department_id FROM EmployeesEntity e2 WHERE e2.employee_id = :employee_id)")
	 List<EmployeesEntity> findEmployeesInSameDepartment(@Param("employee_id") Integer employee_id); 
}
