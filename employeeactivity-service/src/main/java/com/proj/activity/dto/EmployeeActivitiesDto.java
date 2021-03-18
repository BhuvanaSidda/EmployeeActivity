package com.proj.activity.dto;

import java.util.List;

public class EmployeeActivitiesDto {
	private Integer Id;
	private Integer employeeCode;
	private String employeeName;
	private String jobTitle;
	private String emailId;
	private Integer experience;
	private Long phoneNumber;
	private String location;
	private String projectStatus;
	List<ActivityDetailsRes> activityDetails;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

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

	public List<ActivityDetailsRes> getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(List<ActivityDetailsRes> activityDetails) {
		this.activityDetails = activityDetails;
	}

}
