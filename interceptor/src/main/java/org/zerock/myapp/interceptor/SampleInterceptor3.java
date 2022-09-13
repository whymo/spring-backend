package org.zerock.myapp.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//(1) 	기존에 String Interceptor 구현시, HandlerInterceptorAdapter 클래스를 상속받아 구현하였으나,
//		현재 Spring 버전에서는, 이 HandlerInterceptorAdapter class 가 deprecated 되어 사용하지 못함.
//		대신, 인터페이스인 HandlerInterceptor 안의 기존 추상 메소드를 모두 default method로 구현하여, 
//		Interceptor 구현 시, 필요한 것만 메소드 오버라이딩하여 구현하도록 변경됨.

//(2)	구현된 Spring Interceptor는 Spring Beans Container에 Bean으로 등록되어야 함.
//		이를 위해, Spring 설정파일(이 경우는, 웹과 관련 있으므로, Servlet-Context.xml 파일에 직접 Bean으로
//		등록하거나, 아래와 같이 @Component 와 같은 자동 빈등록 어노테이션을 사용하여, Spring Beans Container에
//		Bean으로 등록시켜야 함.(이 어노테이션 방식을 사용할 경우, Spring 설정파일에서, Component Scan을 통해
//		자동으로 Bean 등록 하도록 추가 설정을 해줘야 함.

@Component("sampleInterceptor3")
public class SampleInterceptor3 implements HandlerInterceptor {
	
	
	
	// 뒤의 컨트롤러의 메소드가 호출되기 전에 수행.
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.debug("=============================================================");
		log.debug("1. preHandle(req, res, handler) invoked.");
		log.debug("=============================================================");
		
		log.info("\t+ handler: " + handler);
		log.info("\t\ttype: " + handler.getClass().getName());
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Object beanObj = handlerMethod.getBean();
		Method method = handlerMethod.getMethod();
		
		log.info("\t+ handlerMethod: " + handlerMethod);
		log.info("\t+ beanObj: " + beanObj);
		log.info("\t+ method: " + method);
		log.info("");
		
		
		// 1. true를 리턴하면, 뒤의 Interceptor Chain을 수행시키고, Interceptor Chain이 없다면, 뒤의 컨트롤러 메소드를 수행시킴
		// 2. false를 리턴하면, 뒤의 Interceptor Chain을 수행시키지 않고, Interceptor Chain이 없다면, 뒤의 컨트롤러 메소드를 수행시키지 않음.
		//	  뿐만 아니라, postHandle(), afterCompletion() 조차 수행시키지 않음.
		return true;
//		return false;
	} // preHandle
	

	// 뒤의 컨트롤러 메소드 호출이 완료된 후, 그리고 View 가 호출되기 전에 수행.
	// 단, 컨트롤러 메소드 수행에서 예외가 발생되지 말아야 함.
	//    컨트롤러 메소드 수행에서 에외가 발생되면, 이 메소드는 수행되지 않음.
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		log.debug("=============================================================");
		log.debug("2. postHandle(req, res, handler, modelAndView) invoked.");
		log.debug("=============================================================");
		
		Object result = modelAndView.getModel().get("result");
		
		if(result != null) {
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("result", result);
			
			res.sendRedirect("/sample/doA");
		} // if
	} // postHandle
	

	// 뒤의 컨트롤러 메소드 수행시, 예외의 발생여부와 상관없이, View 까지 호출된 후에 수행.
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) throws Exception {
		log.debug("=============================================================");
		log.debug("3. afterCompletion(req, res, handler, e) invoked.");
		log.debug("=============================================================");
		
		log.info("\t+ handler: " + handler);
		log.info("\t\ttype: " + handler.getClass().getName());
		
		log.info("\t+ e: " + e);
		log.info("");
		
	} // afterCompletion

}// end class
