package com.proj.employee.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import com.proj.employee.dto.EmployeeDetailsDto;
import com.proj.employee.dto.EmployeeDetailsResDto;
import com.proj.employee.dto.EmployeeReqDto;
import com.proj.employee.dto.EmployeeResDto;
import com.proj.employee.entity.EmployeeDetails;
import com.proj.employee.exception.CommonException;
import com.proj.employee.exception.NullCustomException;
import com.proj.employee.exception.TableNotFoundException;
import com.proj.employee.repository.EmployeeRepository;
import com.proj.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public EmployeeResDto addEmployeeDetails(EmployeeReqDto employeeReqDto) {
		logger.info(":::::::::New employee adding functionality-Service Implementation::::::::::::::");
		EmployeeDetails emp = new EmployeeDetails();
		EmployeeResDto res = new EmployeeResDto();
		try {
			EmployeeDetails employeeDetails = employeeRepository.findByEmployeeCode(employeeReqDto.getEmployeeCode());
			if (Objects.nonNull(employeeDetails)) {
				res.setResponseCode("01");
				res.setResponseDesc("Employee code already exist");
				return res;
			}
			BeanUtils.copyProperties(employeeReqDto, emp);
			employeeRepository.save(emp);
			res.setResponseCode("00");
			res.setResponseDesc("Employee Details Added successfully");
		} catch (InvalidDataAccessResourceUsageException e) {
			logger.error(e.getMessage());
			throw new TableNotFoundException("SqlException: Table does not exist", LocalDateTime.now());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return res;
	}

	@Override
	public EmployeeDetailsResDto getAllEmployees() {
		logger.info(":::::::::Fetching all employees-Service Implementation::::::::::::::");
		EmployeeDetailsResDto res = new EmployeeDetailsResDto();
		try {
			List<EmployeeDetails> employeeList = employeeRepository.findAll();
			logger.info("employeeList size:"+employeeList.size());
			List<EmployeeDetailsDto> li = employeeList.stream()
					.map(p -> new EmployeeDetailsDto(p.getId(), p.getEmployeeCode(), p.getEmployeeName(),
							p.getJobTitle(), p.getEmailId(), p.getExperience(), p.getPhoneNumber(), p.getLocation(),
							p.getProjectStatus()))
					.collect(Collectors.toList());

			logger.info("Employees list size:" + employeeList.size() + "  " + li.size());
			res.setEmployees(li);
		}catch (NullPointerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new NullCustomException("There are no employees", LocalDateTime.now());
		}catch (IndexOutOfBoundsException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new NullCustomException("There are no employees", LocalDateTime.now());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return res;
	}

	@Override
	public EmployeeResDto updateEmployee(EmployeeReqDto employeeReqDto) {
		logger.info(":::::::::Updating employee by employee code-Service Implementaion::::::::::::::");
		EmployeeResDto res = new EmployeeResDto();
		try {
			EmployeeDetails employeeDetails = employeeRepository.findByEmployeeCode(employeeReqDto.getEmployeeCode());
			BeanUtils.copyProperties(employeeReqDto, employeeDetails);
			employeeRepository.save(employeeDetails);
			res.setResponseCode("00");
			res.setResponseDesc("Employee Details updated succesfully");
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new NullCustomException("Employee not found", LocalDateTime.now());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return res;
	}

	@Override
	public EmployeeDetailsDto getEmployeeByCode(Integer code) {
		logger.info(":::::::::Fetching employee details by employee code-Controller::::::::::::::");
		EmployeeDetailsDto res = new EmployeeDetailsDto();
		try {
			EmployeeDetails employeeDetails = employeeRepository.findByEmployeeCode(code);
			BeanUtils.copyProperties(employeeDetails, res);
			logger.info("EmployeeDetails:" + employeeDetails);
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			throw new NullCustomException("Employee not found", LocalDateTime.now());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new NullCustomException("Employee not found", LocalDateTime.now());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return res;
	}

}
