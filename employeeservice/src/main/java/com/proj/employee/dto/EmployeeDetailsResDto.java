package com.proj.employee.dto;

import java.util.List;

public class EmployeeDetailsResDto {

	List<EmployeeDetailsDto> Employees;

	public List<EmployeeDetailsDto> getEmployees() {
		return Employees;
	}

	public void setEmployees(List<EmployeeDetailsDto> employees) {
		Employees = employees;
	}

}
