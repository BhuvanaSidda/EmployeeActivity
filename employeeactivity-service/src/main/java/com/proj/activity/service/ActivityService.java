package com.proj.activity.service;

import javax.validation.Valid;

import com.proj.activity.dto.ActivityReqDto;
import com.proj.activity.dto.ActivityResDto;
import com.proj.activity.dto.EmployeeActivityDetailsDto;
import com.proj.activity.dto.EmployeeActivityResDto;

public interface ActivityService {

	ActivityResDto addEmployeeActivityDetails(@Valid ActivityReqDto activityReqDto);

	EmployeeActivityResDto getActivitiesByEmpCode(Integer empcode);

	ActivityResDto updateActivity(@Valid ActivityReqDto activityReqDto, Integer id);

	EmployeeActivityDetailsDto getActivitiesByDate(String fromdate, String todates);

	EmployeeActivityDetailsDto getEmpActivitiesByDate(String date);

	EmployeeActivityDetailsDto getActivitieswithCodeAndDateRange(Integer empcode, String fromdate, String todate);

}
