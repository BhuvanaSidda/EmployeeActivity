package com.proj.activity.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proj.activity.dto.EmployeeDetailsDto;

@FeignClient(name = "http://employee-service/api/employee")
public interface EmployeeClient {

	@GetMapping("{code}")
	public EmployeeDetailsDto getEmployeeByCode(@PathVariable Integer code);
	
	
}
