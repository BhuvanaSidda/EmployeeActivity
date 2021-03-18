package com.proj.activity.exception;

import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = TableNotFoundException.class)
	public ErrorMessages handleTableNotFoundException(TableNotFoundException ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getErrorMessage());
		objError.setErrorOccuredTime(
				ex.getErrorOccuredTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
		return objError;
	}

	@ExceptionHandler(value = CommonException.class)
	public ErrorMessages handleCommonException(CommonException ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getErrorMessage());
		objError.setErrorOccuredTime(
				ex.getErrorOccuredTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
		return objError;
	}

	@ExceptionHandler(value = NullCustomException.class)
	public ErrorMessages handleNullPointerException(NullCustomException ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getErrorMessage());
		objError.setErrorOccuredTime(
				ex.getErrorOccuredTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
		return objError;
	}

	@ExceptionHandler(value = ServiceUnavailableException.class)
	public ErrorMessages handleCommonException(ServiceUnavailableException ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getErrorMessage());
		objError.setErrorOccuredTime(
				ex.getErrorOccuredTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
		return objError;
	}
	
	@ExceptionHandler(value = ActivitiesNotFoundException.class)
	public ErrorMessages handleCommonException(ActivitiesNotFoundException ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getErrorMessage());
		objError.setErrorOccuredTime(
				ex.getErrorOccuredTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
		return objError;
	}
}
