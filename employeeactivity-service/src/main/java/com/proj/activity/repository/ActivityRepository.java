package com.proj.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proj.activity.entity.ActivityDetails;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityDetails, Integer> {

	List<ActivityDetails> findByEmployeeCode(Integer empCode);

	@Query(value = "select * from Activity_Details where DATE_FORMAT(activity_date, '%Y-%m-%d') between ? and ? ", nativeQuery = true)
	List<ActivityDetails> findByActivityDates(String fromDate, String toDate);

	@Query(value = "select * from Activity_Details where DATE_FORMAT(activity_date, '%Y-%m-%d')= ?", nativeQuery = true)
	List<ActivityDetails> getActivityDetailsByDate(String date);

	@Query(value = "select * from Activity_Details where employee_code=? and DATE_FORMAT(activity_date, '%Y-%m-%d') between ? and ?", nativeQuery = true)
	List<ActivityDetails> getActivityDetailsByDateAndCode(Integer empcode, String fromdate, String todate);

}
