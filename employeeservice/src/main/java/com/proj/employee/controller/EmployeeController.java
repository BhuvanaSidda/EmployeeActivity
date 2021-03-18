package com.proj.employee.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.employee.dto.EmployeeDetailsDto;
import com.proj.employee.dto.EmployeeDetailsResDto;
import com.proj.employee.dto.EmployeeReqDto;
import com.proj.employee.dto.EmployeeResDto;
import com.proj.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/add")
	public EmployeeResDto addEmployee(@Valid @RequestBody EmployeeReqDto employeeReqDto) {
		logger.info(":::::::::New employee adding functionality-Controller::::::::::::::");
		return employeeService.addEmployeeDetails(employeeReqDto);
	}

	@GetMapping()
	public EmployeeDetailsResDto getAllEmployees() {
		logger.info(":::::::::Fetching all employees-Controller::::::::::::::");
		return employeeService.getAllEmployees();
	}

	@PatchMapping("/update")
	public EmployeeResDto updateEmployee(@Valid @RequestBody EmployeeReqDto employeeReqDto) {
		logger.info(":::::::::Updating employee by employee code-Controller::::::::::::::");
		return employeeService.updateEmployee(employeeReqDto);
	}

	@GetMapping("{code}")
	public EmployeeDetailsDto getEmployeeByCode(@PathVariable Integer code) {
		logger.info(":::::::::Fetching employee details by employee code-Controller::::::::::::::");
		return employeeService.getEmployeeByCode(code);
	}

}
