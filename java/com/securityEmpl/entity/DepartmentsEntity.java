package com.securityEmpl.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private int department_id;
	
	@JsonManagedReference
	@OneToMany(
			mappedBy = "department_id",
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<EmployeesEntity> employees = new ArrayList<>();
	
	@Column(name = "department_name")
	private String department_name;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private LocationsEntity location_id;

	
	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public List<EmployeesEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeesEntity> employees) {
		this.employees = employees;
	}

	public String getDepartment_name() {
		return department_name;
	}


	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public LocationsEntity getLocation_id() {
		return location_id;
	}

	public void setLocation_id(LocationsEntity location_id) {
		this.location_id = location_id;
	}

	public DepartmentsEntity() {}

}
