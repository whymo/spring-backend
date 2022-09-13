package org.zerock.myapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@ControllerAdvice		// For all packages.
//@ControllerAdvice(basePackages= { "org.zerock.myapp.controller" })	// Only for the specified packages.
public class CommonExceptionHandler {
	
	
	@ExceptionHandler( Exception.class )
	public String handleBindException(Exception e, Model model) {
		log.debug("handleBindException(e, model) invoked.");
		
		log.error("1. Exception Type: " + e.getClass().getName());
		log.error("2. Exception Message: " + e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "errorPage";
	} // handleException

} // end class
