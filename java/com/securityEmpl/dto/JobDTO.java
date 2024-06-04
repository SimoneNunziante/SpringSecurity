package com.securityEmpl.dto;

public class JobDTO {

	private int job_id;
	private String job_title;
	
	
	public int getJob_id() {
		return job_id;
	}
	
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	
	public String getJob_title() {
		return job_title;
	}
	
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	
	
	public JobDTO(int job_id, String job_title) {
		this.job_id = job_id;
		this.job_title = job_title;
	}
	
	public JobDTO() {}
}
