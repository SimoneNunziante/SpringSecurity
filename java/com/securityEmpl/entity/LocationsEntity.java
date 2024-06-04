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
@Table(name = "locations")
public class LocationsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private int location_id;
	
	@JsonManagedReference
	@OneToMany(
			mappedBy = "location_id",
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<DepartmentsEntity> departments = new ArrayList<>();
	
	@Column(name = "street_address")
	private String street_address;
	
	@Column(name = "postal_code")
	private String postal_code;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state_province")
	private String state_province;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
	private CountriesEntity country_id;

	
	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public List<DepartmentsEntity> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentsEntity> departments) {
		this.departments = departments;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState_province() {
		return state_province;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public CountriesEntity getCountry_id() {
		return country_id;
	}

	public void setCountry_id(CountriesEntity country_id) {
		this.country_id = country_id;
	}


	public LocationsEntity() {}
}
