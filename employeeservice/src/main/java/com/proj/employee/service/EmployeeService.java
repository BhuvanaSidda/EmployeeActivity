package com.proj.employee.service;

import com.proj.employee.dto.EmployeeDetailsDto;
import com.proj.employee.dto.EmployeeDetailsResDto;
import com.proj.employee.dto.EmployeeReqDto;
import com.proj.employee.dto.EmployeeResDto;

public interface EmployeeService {

	EmployeeResDto addEmployeeDetails(EmployeeReqDto employeeReqDto);

	EmployeeDetailsResDto getAllEmployees();

	EmployeeResDto updateEmployee(EmployeeReqDto employeeReqDto);

	EmployeeDetailsDto getEmployeeByCode(Integer code);

}
