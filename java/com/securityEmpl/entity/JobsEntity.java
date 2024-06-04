package com.securityEmpl.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class JobsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private int job_id;
	
	@JsonManagedReference
	@OneToMany(
			mappedBy = "job_id",
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<EmployeesEntity> employees = new ArrayList<>();
	
	@Column(name = "job_title")
	private String job_title;
	
	@Column (name = "min_salary")
	private BigDecimal min_salary;
	
	@Column(name = "max_salary")
	private BigDecimal max_salary;
	
	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public List<EmployeesEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeesEntity> employees) {
		this.employees = employees;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public BigDecimal getMin_salary() {
		return min_salary;
	}

	public void setMin_salary(BigDecimal min_salary) {
		this.min_salary = min_salary;
	}

	public BigDecimal getMax_salary() {
		return max_salary;
	}

	public void setMax_salary(BigDecimal max_salary) {
		this.max_salary = max_salary;
	}
	

	public JobsEntity() {}

}
