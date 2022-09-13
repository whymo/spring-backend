package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component // @Component로 HandlerInterceptor를 빈으로 등록하였다.
public class SampleInterceptor2 implements HandlerInterceptor {
	// + 인터셉터는 반드시 HandlerInterceptor를 상속받아야 한다. (***)
	
	// =======================================================================================================
	
	// + 1. Dispatcher Servlet이 받은 request를 Controller의 Handler Method로 전달되기 전에 가로챈다.
	// + Servlet-context/xml에서 sample/doA에 들어오는 것을 SampleInterceptor가 가로채도록 되어 있기에,
	// + /sample/doA로 접속을 시도하면, 그 전에 preHandle(req, res, handler) invoked.가 발생한다.
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		
		log.trace("\t + 1. preHandle(req, res, handler) invoked.");

		return true; // 기본값이 true로 설정되어 있다.
	} // preHandle
	
	// =======================================================================================================

	// + 2. Controller의 Handler Method가 수행된 직후 ( 단, view가 호출되기 전 )
	// + (*주의*) 단, Controller의 Handler Method에서 예외가 발생하면, 아래의 메소드는 호출되지 못한다.
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.trace("\t + 2. postHandle(req, res, handler, modelAndView) invoked.");

	} // postHandle
	
	// =======================================================================================================

	// + 3. View까지 호출이 완료되어, request에 대한 response가 전송완료된 직후
	// + (*주의*) 단, Controller의 Handler Method에서 예외가 발생하든 안하든, 무조건 호출된다.
	// + afterCompletion는 응답을 완전히 바꿔버릴 수도 있다.
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e)
			throws Exception {
		
		log.trace("\t + 3.afterCompletion(req, res, handler, e) invoked.");

	} // afterCompletion
	
	// =======================================================================================================

} // end class
