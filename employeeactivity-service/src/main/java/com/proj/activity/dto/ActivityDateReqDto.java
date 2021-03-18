package com.proj.activity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ActivityDateReqDto {

	@NotNull
	@NotBlank(message = "from date is mandatory")
	private String fromDate;
	
	@NotNull
	@NotBlank(message = "to date is mandatory")
	private String toDate;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
