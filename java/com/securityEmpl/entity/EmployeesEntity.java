package com.securityEmpl.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employee_id")
public class EmployeesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int employee_id;
	
	@JsonManagedReference
	@OneToMany(
			mappedBy = "manager_id",
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<EmployeesEntity> subordinates = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(
			mappedBy = "employee_id",
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<DependentsEntity> dependendents = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
			joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
	)
	private Collection<RoleEntity> roles;

	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phone_number")
	private String phone_number;
	
	@Column(name = "hire_date")
	private LocalDate hire_date;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "job_id", referencedColumnName = "job_id")
	private JobsEntity job_id;
		
	@JsonBackReference
	@ManyToOne	
	@JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
	private EmployeesEntity manager_id;  

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
	private DepartmentsEntity department_id;
	
	
	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public List<EmployeesEntity> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<EmployeesEntity> subordinates) {
		this.subordinates = subordinates;
	}

	public List<DependentsEntity> getDependendents() {
		return dependendents;
	}

	public void setDependendents(List<DependentsEntity> dependendents) {
		this.dependendents = dependendents;
	}
	
	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public LocalDate getHire_date() {
		return hire_date;
	}

	public void setHire_date(LocalDate hire_date) {
		this.hire_date = hire_date;
	}

	public JobsEntity getJob_id() {
		return job_id;
	}

	public void setJob_id(JobsEntity job_id) {
		this.job_id = job_id;
	}

	public EmployeesEntity getManager_id() {
		return manager_id;
	}

	public void setManager_id(EmployeesEntity manager_id) {
		this.manager_id = manager_id;
	}

	public DepartmentsEntity getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(DepartmentsEntity department_id) {
		this.department_id = department_id;
	}


	public EmployeesEntity() {}
}
