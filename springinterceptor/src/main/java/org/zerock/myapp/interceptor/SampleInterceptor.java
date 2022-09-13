package org.zerock.myapp.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
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

// Spring servlet-context.xml 설정파일에 수동으로 Bean 등록
//@Component
public class SampleInterceptor implements HandlerInterceptor {
	
	
	// 1. DispatcherServlet 이 받은 request를, Controller의 Handler Method로 위임하기 직전에, called back
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.trace("================================================================");
		log.trace("1. preHandle(req, res, handler) invoked.");
		log.trace("================================================================");

		log.info("\t+ 1. handler : {}", handler);
		log.info("\t+ 2. type    : {}", handler.getClass().getName());
		
		HandlerMethod controllerHandler = (HandlerMethod) handler;
		Object controller = controllerHandler.getBean();
		Method method = controllerHandler.getMethod();
		
		log.info("\t+ 3. controllerHandler: {}", controllerHandler);	// HandlerMethod 타입의 객체
		log.info("\t+ 4. controller: {}", controller);					// SampleController 객체
		log.info("\t+ 5. method: {}", method);							// doA() 메소드
		
		
		// if return false, incoming request를 뒤로 넘기지 않음(체인이 있든 없든)
		// if return true, incoming request를 뒤로 넘김(체인이 있든 없든)
		
		// 예제: 요청을 보낸 클라이언트의 IP주소를 체크하여, 위험한 소스에서 온 요청이면
		//       그 즉시 요청 처리를 끝내버림(through returning false)
//		String clientAddr = req.getRemoteAddr();
//		log.info("\t+ clientAddr: {}", clientAddr);		
//		return ("192.168.10.4".equals(clientAddr) ? false : true);
		
		return true;		// Default: return "true"
	} // preHandle

	
	// 2. Controller의 Handler Method 수행된 직후, called back (단, View가 호출되기 전에)
	//    (*주의*) 단, Controller's handler method에서 예외가 발생하면, 아래의 메소드는 호출되지 못함
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		log.trace("================================================================");
		log.trace("2. postHandle(req, res, handler, modelAndView) invoked.");
		log.trace("================================================================");

		log.info("\t+ 1. modelAndView : {}", modelAndView);
		
		modelAndView.setViewName("redirect:/login");		
		ModelMap model = modelAndView.getModelMap();
		model.put("serverTime", "^^;;");
		
		log.info("\t+ 2. modelAndView : {}", modelAndView);
		
	} // postHandle

	
	// 3. View까지 호출완료되어, request에 대한 response가 전송완료된 직후, called back
	//    (*주의*) 단, Controller's handler method에서 예외가 발생하든 안하든 무조건 호출됨
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) throws Exception {
		log.trace("================================================================");
		log.trace("3. afterCompletion(req, res, handler, e) invoked.");
		log.trace("================================================================");

		log.info("\t+ e: {}", e);
		
	} // afterCompletion

} // end class
