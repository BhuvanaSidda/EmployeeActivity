package com.proj.activity.dto;

import java.util.List;

public class EmployeeActivityResDto {

	private EmployeeDetailsDto employeeDetailsDto;
	private List<ActivityDetailsRes> activityList;

	public EmployeeDetailsDto getEmployeeDetailsDto() {
		return employeeDetailsDto;
	}

	public void setEmployeeDetailsDto(EmployeeDetailsDto employeeDetailsDto) {
		this.employeeDetailsDto = employeeDetailsDto;
	}

	public List<ActivityDetailsRes> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityDetailsRes> activityList) {
		this.activityList = activityList;
	}

}
