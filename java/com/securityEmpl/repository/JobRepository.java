package com.securityEmpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securityEmpl.entity.JobsEntity;

public interface JobRepository extends JpaRepository<JobsEntity, Integer>{

}
