package com.proj.employee.dto;

public class EmployeeDetailsDto {

	public EmployeeDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDetailsDto(Integer id, Integer employeeCode, String employeeName, String jobTitle, String emailId,
			Integer experience, Long phoneNumber, String location, String projectStatus) {
		super();
		this.id = id;
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.jobTitle = jobTitle;
		this.emailId = emailId;
		this.experience = experience;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.projectStatus = projectStatus;
	}

	private Integer id;
	private Integer employeeCode;
	private String employeeName;
	private String jobTitle;
	private String emailId;
	private Integer experience;
	private Long phoneNumber;
	private String location;
	private String projectStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
