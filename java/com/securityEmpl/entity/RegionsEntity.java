package com.securityEmpl.entity;

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
@Table(name = "regions")
public class RegionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private int region_id;
	
	@JsonManagedReference
	@OneToMany(
			mappedBy = "region_id",
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<CountriesEntity> countries = new ArrayList<>();
	
	@Column(name = "region_name")
	public String region_name;

	
	public int getRegion_id() {
		return region_id;
	}

	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}

	public List<CountriesEntity> getCountries() {
		return countries;
	}

	public void setCountries(List<CountriesEntity> countries) {
		this.countries = countries;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}


	public RegionsEntity() {}
	
}
