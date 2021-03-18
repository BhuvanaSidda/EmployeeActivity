package com.proj.activity.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.activity.dto.ActivityReqDto;
import com.proj.activity.dto.ActivityResDto;
import com.proj.activity.dto.EmployeeActivityDetailsDto;
import com.proj.activity.dto.EmployeeActivityResDto;
import com.proj.activity.service.ActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	ActivityService activityService;

	@PostMapping("/add")
	public ActivityResDto addEmployee(@Valid @RequestBody ActivityReqDto activityReqDto) {
		logger.info(":::::::::::::::Employee daily activity adding-Controller:::::::::::::::::::");
		return activityService.addEmployeeActivityDetails(activityReqDto);
	}

	@GetMapping("{empcode}")
	public EmployeeActivityResDto getActivitiesByEmpCode(@PathVariable Integer empcode) {
		logger.info(":::::::::::::::Fetching employee daily activities by employee code-Controller:::::::::::::::::::");
		return activityService.getActivitiesByEmpCode(empcode);
	}

	@PatchMapping("/update/{id}")
	public ActivityResDto updateActivityDetails(@Valid @RequestBody ActivityReqDto activityReqDto,
			@PathVariable Integer id) {
		logger.info(":::::::::::::::Eployee daily activity updation by employee code-Controller:::::::::::::::::::");
		return activityService.updateActivity(activityReqDto, id);
	}

	@GetMapping("/dates")
	public EmployeeActivityDetailsDto getActivitiesBetweenDates(@RequestParam String fromdate,
			@RequestParam String todate) {
		logger.info(":::::::::::::::Fetching employee daily activities by date range-Controller:::::::::::::::::::");
		return activityService.getActivitiesByDate(fromdate, todate);
	}

	@GetMapping("/date/{date}")
	public EmployeeActivityDetailsDto getActivitiesByDate(@PathVariable String date) {
		logger.info("::::::::Fetching employee daily activities in a particular day-Controller::::::::::");
		return activityService.getEmpActivitiesByDate(date);
	}

	@GetMapping()
	public EmployeeActivityDetailsDto getActivitieswithCodeAndDateRange(@RequestParam Integer empcode,
			@RequestParam String fromdate, @RequestParam String todate) {
		logger.info(":::::::::Fetching employee daily activities by employee code and date range-Controller:::::::::");
		return activityService.getActivitieswithCodeAndDateRange(empcode, fromdate, todate);
	}

}
