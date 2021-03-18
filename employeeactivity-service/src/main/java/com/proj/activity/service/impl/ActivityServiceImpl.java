package com.proj.activity.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import com.proj.activity.dto.ActivityDetailsRes;
import com.proj.activity.dto.ActivityReqDto;
import com.proj.activity.dto.ActivityResDto;
import com.proj.activity.dto.EmployeeActivitiesDto;
import com.proj.activity.dto.EmployeeActivityDetailsDto;
import com.proj.activity.dto.EmployeeActivityResDto;
import com.proj.activity.dto.EmployeeDetailsDto;
import com.proj.activity.entity.ActivityDetails;
import com.proj.activity.exception.ActivitiesNotFoundException;
import com.proj.activity.exception.CommonException;
import com.proj.activity.exception.NullCustomException;
import com.proj.activity.exception.ServiceUnavailableException;
import com.proj.activity.exception.TableNotFoundException;
import com.proj.activity.feignclient.EmployeeClient;
import com.proj.activity.repository.ActivityRepository;
import com.proj.activity.service.ActivityService;

import feign.FeignException;

@Service
public class ActivityServiceImpl implements ActivityService {

	private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	EmployeeClient employeeClient;

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;

	@Override
	public ActivityResDto addEmployeeActivityDetails(@Valid ActivityReqDto activityReqDto) {
		logger.info(":::::::::::::::Employee daily activity adding-Service implementation:::::::::::::::::::");
		ActivityDetails empActivity = new ActivityDetails();
		ActivityResDto res = new ActivityResDto();
		// employee code check
		EmployeeDetailsDto employeeDetailsDto = circuitBreakerCheck(activityReqDto.getEmployeeCode());

		try {
			BeanUtils.copyProperties(activityReqDto, empActivity);
			empActivity.setActivityDate(LocalDateTime.now());
			empActivity.setEmployeeName(employeeDetailsDto.getEmployeeName());
			activityRepository.save(empActivity);
			res.setResponseCode("00");
			res.setResponseDesc("Employee Activity Details Added successfully");
		} catch (InvalidDataAccessResourceUsageException e) {
			logger.error(e.getMessage());
			throw new TableNotFoundException("SqlException: Table does not exist", LocalDateTime.now());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return res;
	}

	// Activity details addition: Employee Service unable
	public EmployeeDetailsDto getDefaultMsg() {
		throw new ServiceUnavailableException("Please try after sometime", LocalDateTime.now());
	}

	public EmployeeDetailsDto circuitBreakerCheck(Integer empCode) {
		logger.info("Circuitebreaker - Employee service activation check");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("Employee code check");
		try {
			EmployeeDetailsDto emp = employeeClient.getEmployeeByCode(empCode);
			if (Objects.isNull(emp.getEmployeeCode())) {
				throw new NullCustomException("Please enter valid Employee code", LocalDateTime.now());
			}
			return circuitBreaker.run(() -> emp, throwable -> getDefaultMsg());
		} catch (NullCustomException e) {
			e.printStackTrace();
			throw new NullCustomException("Employee doesn't exist, Please enter valid Employee code",
					LocalDateTime.now());
		} catch (FeignException e) {
			e.printStackTrace();
			throw new ServiceUnavailableException("Employee service is down, please try after sometime",
					LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
	}

	@Override
	public EmployeeActivityResDto getActivitiesByEmpCode(Integer empCode) {
		logger.info(":::::::::::::::Fetching employee daily activities by employee code-Service implementation:::::::::::::::::::");
		EmployeeActivityResDto employeeActivityResDto = new EmployeeActivityResDto();
		List<ActivityDetailsRes> activityResList = new ArrayList<>();
		// employee code check
		EmployeeDetailsDto employeeDetailsDto = circuitBreakerCheck(empCode);
		employeeActivityResDto.setEmployeeDetailsDto(employeeDetailsDto);
		try {
			// activity list check
			List<ActivityDetails> activityList = activityRepository.findByEmployeeCode(empCode);

			if (activityList.isEmpty()) {
				throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
			}
			
			for (ActivityDetails li : activityList) {
				ActivityDetailsRes activityResDto = new ActivityDetailsRes();
				BeanUtils.copyProperties(li, activityResDto);
				activityResList.add(activityResDto);
			}
		} catch (ActivitiesNotFoundException e) {
			e.printStackTrace();
			throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		employeeActivityResDto.setActivityList(activityResList);
		return employeeActivityResDto;
	}

	@Override
	public ActivityResDto updateActivity(@Valid ActivityReqDto activityReqDto, Integer id) {
		logger.info(":::::::::::::::Eployee daily activity updation by employee code-Service implementation:::::::::::::::::::");
		ActivityResDto activityResDto = new ActivityResDto();
		// employee code check
		circuitBreakerCheck(activityReqDto.getEmployeeCode());
		try {
			ActivityDetails activityDetails = activityRepository.findById(id).get();
			BeanUtils.copyProperties(activityReqDto, activityDetails);
			activityDetails.setActivityDate(LocalDateTime.now());
			activityRepository.save(activityDetails);
			activityResDto.setResponseCode("00");
			activityResDto.setResponseDesc("Activity Details updated succesfully");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			throw new NullCustomException("Activity Id not found", LocalDateTime.now());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new NullCustomException("Activity Id not found", LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}

		return activityResDto;
	}

	@Override
	public EmployeeActivityDetailsDto getActivitiesByDate(String fromdate, String todate) {
		logger.info(":::::::::::::::Fetching employee daily activities by date range-Service implementation:::::::::::::::::::");
		EmployeeActivityDetailsDto employeeActivityDetailsDto = new EmployeeActivityDetailsDto();
		try {
			if (fromdate.equals(todate)) {
				throw new CommonException("From date and to date should not be equal", LocalDateTime.now());
			}
			List<ActivityDetails> activityList = activityRepository.findByActivityDates(fromdate, todate);
			if (activityList.isEmpty()) {
				throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
			}
			employeeActivityDetailsDto = framingResponse(activityList);

		} catch (ActivitiesNotFoundException e) {
			e.printStackTrace();
			throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
		} catch (CommonException e) {
			e.printStackTrace();
			throw new CommonException("Fromdate and Todate should not be equal", LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}

		return employeeActivityDetailsDto;
	}

	private EmployeeActivityDetailsDto framingResponse(List<ActivityDetails> activityList) {
		logger.info("framing response");
		EmployeeActivityDetailsDto employeeActivityDetailsDto = new EmployeeActivityDetailsDto();
		try {
			List<Integer> list = activityList.stream().map(p -> p.getEmployeeCode()).distinct()
					.collect(Collectors.toList());
			List<EmployeeActivitiesDto> employeeActivitiesList = new ArrayList<>();
			for (Integer li : list) {
				// employee code check
				EmployeeDetailsDto employeeDetailsDto = circuitBreakerCheck(li);

				EmployeeActivitiesDto employeeActivitiesDto = new EmployeeActivitiesDto();

				BeanUtils.copyProperties(employeeDetailsDto, employeeActivitiesDto);
				List<ActivityDetailsRes> activityResList = new ArrayList<>();
				for (ActivityDetails details : activityList) {
					if (Integer.compare(details.getEmployeeCode(), li) == 0) {
						ActivityDetailsRes activityResDto = new ActivityDetailsRes();
						BeanUtils.copyProperties(details, activityResDto);
						activityResList.add(activityResDto);
					}

				}
				employeeActivitiesDto.setActivityDetails(activityResList);
				employeeActivitiesList.add(employeeActivitiesDto);
				employeeActivityDetailsDto.setEmployeeDetails(employeeActivitiesList);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new NullCustomException("Employee details not found", LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return employeeActivityDetailsDto;
	}

	@Override
	public EmployeeActivityDetailsDto getEmpActivitiesByDate(String date) {
		logger.info("::::::::Fetching employee daily activities in a particular day-Service implementation::::::::::");
		EmployeeActivityDetailsDto employeeActivityDetailsDto = new EmployeeActivityDetailsDto();
		try {

			List<ActivityDetails> activityList = activityRepository.getActivityDetailsByDate(date);

			if (activityList.isEmpty()) {
				throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
			}
			// Response framing
			employeeActivityDetailsDto = framingResponse(activityList);
		} catch (ActivitiesNotFoundException e) {
			e.printStackTrace();
			throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return employeeActivityDetailsDto;
	}

	@Override
	public EmployeeActivityDetailsDto getActivitieswithCodeAndDateRange(Integer empcode, String fromdate,
			String todate) {
		logger.info(
				":::::::::Fetching employee daily activities by employee code and date range-Service implementation:::::::::");
		EmployeeActivityDetailsDto employeeActivityDetailsDto = new EmployeeActivityDetailsDto();
		try {

			if (fromdate.equals(todate)) {
				throw new CommonException("From date and to date should not be equal", LocalDateTime.now());
			}
			List<ActivityDetails> activityList = activityRepository.getActivityDetailsByDateAndCode(empcode, fromdate,
					todate);
			if (activityList.isEmpty()) {
				throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
			}
			// Response Framing
			employeeActivityDetailsDto = framingResponse(activityList);
		} catch (ActivitiesNotFoundException e) {
			e.printStackTrace();
			throw new ActivitiesNotFoundException("No activities found", LocalDateTime.now());
		} catch (CommonException e) {
			e.printStackTrace();
			throw new CommonException("Fromdate and Todate should not be equal", LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}
		return employeeActivityDetailsDto;
	}

}
