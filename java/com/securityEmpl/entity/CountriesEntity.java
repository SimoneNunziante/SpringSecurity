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
@Table(name = "countries")
public class CountriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private String country_id;
	
	@JsonManagedReference
	@OneToMany(
			mappedBy = "country_id",
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<LocationsEntity> locations = new ArrayList<>();
	
	@Column(name = "country_name")
	private String country_name;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private RegionsEntity region_id;

	
	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public List<LocationsEntity> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationsEntity> locations) {
		this.locations = locations;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public RegionsEntity getRegion_id() {
		return region_id;
	}

	public void setRegion_id(RegionsEntity region_id) {
		this.region_id = region_id;
	}

	
	public CountriesEntity() {}

}
