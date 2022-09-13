package org.zerock.myapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ControllerAdvice
// + @ControllerAdvice가 붙으면 Controller에서 발생한 예외를 처리하는 클래스가 된다.
public class CommonsExceptionHandler {
	
	// =======================================================
	// 1. IllegalStateExceptionHandler( Exception e, Model model )
	// =======================================================
	
	@ExceptionHandler(IllegalStateException.class)
	// + Model 객체를 통해서 JSP로 전달하고자 한다.
	// + Spring MVC의 예외처리가 web.xml 예외처리보다 우선되기에
	// + 만약 web.xml파일에도 같은 예외를 처리하는 것이 있다 하더라도
	// + Spring MVC에 예외처리 핸들러가 있다면 여기서 처리되어 web.xml을 처리하지 않는다.
	public String handleIllegalStateException(Exception e, Model model) {
		
		log.trace("IllegalStateException() invoked.");
		
		log.info("\t + Exception e : {}", e);
		// + 예외 객체는 log로 {}안에 찍으려고 해도, {} 안에 찍히지 않고 콘솔에 찍힌다.
		// + 그렇기에 log.info(e);로만 해도 같은 결과가 나온다.
		
		log.error("\t + 1. Exception Type : {}", e.getClass().getName() );
		log.error("\t + 2. Exception Message : {}", e.getMessage() );
		
		// + Request Scope 공유속성으로 바인딩
		model.addAttribute("exception",e);
		
		return "errors/errorPage";
		
	} // IllegalState
	
	// =======================================================
	// 2. NoHandlerFoundException( Exception e, Model model )
	// =======================================================
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String NoHandlerFoundException(Exception e, Model model) {
		
		log.trace("NoHandlerFoundException() invoked.");
		
		log.error("\t + 1. Exception Type : {}", e.getClass().getName() );
		log.error("\t + 2. Exception Message : {}", e.getMessage() );
		
		// + Request Scope 공유속성으로 바인딩
		model.addAttribute("exception",e);
		
		return "errors/errorPage";
	} // NoHandlerFoundException
	
	// =======================================================
	// 3. Exception( Exception e, Model model )
	// =======================================================
	// + Exception으로 하면 원래는 모든 예외를 다루기에
	// + 위의 예외들보다 우선시하다고 생각할 수 있으나,
	// + 특정 예외를 처리하는 핸들러가 우선시 된다.
	// =======================================================
	
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler( Exception e, Model model ) {
		
		log.trace("AllExceptionHandler() invoked.");
		
		log.error("\t + 1. Exception Type : {}", e.getClass().getName() );
		log.error("\t + 2. Exception Message : {}", e.getMessage() );
		
		// + Request Scope 공유속성으로 바인딩
		model.addAttribute("exception", e);
		
		return "errors/errorPage";
	} // ExceptionHandler

} // end class
