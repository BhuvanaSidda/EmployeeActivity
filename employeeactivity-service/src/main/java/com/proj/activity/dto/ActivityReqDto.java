package com.proj.activity.dto;

import javax.validation.constraints.NotNull;

public class ActivityReqDto {

	@NotNull(message = "EmployeeCode is mandatory")
	public Integer employeeCode;

	public String description;

	public String status;

	public Integer getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
