package com.proj.employee.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeReqDto {

	@NotNull(message = "Employee code is mandatory")
	private Integer employeeCode;

	@NotNull
	@NotBlank(message = "Employee name is mandatory")
	@Size(min = 3, max = 100)
	private String employeeName;

	@NotNull
	@NotBlank(message = "Job title is mandatory")
	@Size(min = 3, max = 100)
	private String jobTitle;

	@NotNull
	@NotBlank(message = "Employee emailId is mandatory")
	@Email(message = "Email id is not valid")
	private String emailId;

	@NotNull(message = "Experience is mandatory")
	private Integer experience;

	private Long phoneNumber;
	private String location;
	private String projectStatus;

	public Integer getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

}
