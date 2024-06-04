package com.securityEmpl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dependents")
public class DependentsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dependent_id")
	private int dependent_id;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "relationship")
	private String relationship;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
	private EmployeesEntity employee_id;
	
	
	public int getDependent_id() {
		return dependent_id;
	}

	public void setDependent_id(int dependent_id) {
		this.dependent_id = dependent_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public EmployeesEntity getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(EmployeesEntity employee_id) {
		this.employee_id = employee_id;
	}

	public DependentsEntity() {}
}
