package org.zerock.myapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ControllerAdvice
public class SpecificExceptionHandler {
	
	// =======================================================
	// 4. hadlerSQLException( Exception e, Model model )
	// =======================================================
	
	@ExceptionHandler
	public String hadlerSQLException( Exception e, Model model ) {
		
		log.trace("hadlerSQLException(e, model) invoked.");
		
		log.error("\t + 1. Exception Type : {}", e.getClass().getName() );
		log.error("\t + 2. Exception Message : {}", e.getMessage() );
		
		// + Request Scope 공유속성으로 바인딩
		model.addAttribute("exception", e);
		
		return "errors/errorPage";
	} // hadlerSQLException

} // end class
