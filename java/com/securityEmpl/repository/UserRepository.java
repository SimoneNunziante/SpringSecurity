package com.securityEmpl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securityEmpl.entity.EmployeesEntity;

public interface UserRepository extends JpaRepository<EmployeesEntity, Integer>{
   boolean existsByEmail(String email);
   Optional<EmployeesEntity> findByEmail(String email);
}
