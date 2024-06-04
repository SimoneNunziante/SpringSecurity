package com.securityEmpl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteRequestDTO {

	@JsonProperty
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
