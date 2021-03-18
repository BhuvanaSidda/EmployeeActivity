package com.proj.activity.dto;

import java.time.LocalDateTime;

public class ActivityDetailsRes {

	public Integer id;

	public String description;

	public String status;

	public LocalDateTime activityDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(LocalDateTime activityDate) {
		this.activityDate = activityDate;
	}

}
