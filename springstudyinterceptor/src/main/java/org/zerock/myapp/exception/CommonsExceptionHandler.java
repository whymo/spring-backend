package org.zerock.myapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ControllerAdvice
public class CommonsExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handleException( Exception e, Model model ) {
		
		log.trace("handleException(e, model) invoked.");
			
		model.addAttribute("_EXCEPTION_", e);
		
		Throwable originEx = e;
		
		do {
			// + 무조건 가장 바깥쪽 예외에 대한 정보를 출력한 후, 안쪽에 겹쳐있는 예외를 모두 출력
			// + error는 원래 log.error로 출력해야 붉은 글씨로 출력이 된다.
			log.error("\\t =============================================================");
			log.error("\t + 1. Exception type : {}", e.getClass().getName());
			log.error("\t + 2. Exception message : {}", e.getMessage());
		} while( ( originEx = originEx.getCause() ) != null);
		
		return "errorPage";
		
	} // handleException

} // end class
