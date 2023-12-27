package com.egitim.egitimSitesi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.egitim.egitimSitesi.core.utilities.exception.BusinessException;
import com.egitim.egitimSitesi.core.utilities.exception.ProblemDetails;

@SpringBootApplication
@RestControllerAdvice
public class EgitimSitesiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgitimSitesiApplication.class, args);
	}
	@Bean   
	public ModelMapper getModelMapper() {
	return new ModelMapper();
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinesException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		
		return problemDetails;
	}
	
}