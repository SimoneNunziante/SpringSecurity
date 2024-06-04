package com.securityEmpl.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.securityEmpl.entity.DepartmentsEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentsEntity, Integer>{
	
}
