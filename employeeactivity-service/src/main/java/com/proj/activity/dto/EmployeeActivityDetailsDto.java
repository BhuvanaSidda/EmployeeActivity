package com.proj.activity.dto;

import java.util.List;

public class EmployeeActivityDetailsDto {

	private List<EmployeeActivitiesDto> employeeDetails;
	public List<EmployeeActivitiesDto> getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(List<EmployeeActivitiesDto> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	
}
