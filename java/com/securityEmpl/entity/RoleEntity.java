package com.securityEmpl.entity;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int role_id;
	
	@Column(name = "role_name")
	private String role_name;

	@ManyToMany(mappedBy = "roles")
    Collection<EmployeesEntity> employees;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
    public Collection<EmployeesEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<EmployeesEntity> employees) {
		this.employees = employees;
	}
	
}