package org.zerock.myapp.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component // @Component로 HandlerInterceptor를 빈으로 등록하였다.
public class SampleInterceptor implements HandlerInterceptor {
	// + 인터셉터는 반드시 HandlerInterceptor를 상속받아야 한다. (***)
	
	// =======================================================================================================
	
	// + 1. Dispatcher Servlet이 받은 request를 Controller의 Handler Method로 전달되기 전에 가로챈다.
	// + Servlet-context/xml에서 sample/doA에 들어오는 것을 SampleInterceptor가 가로채도록 되어 있기에,
	// + /sample/doA로 접속을 시도하면, 그 전에 preHandle(req, res, handler) invoked.가 발생한다.
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		
		log.trace("======================================================");
		log.trace("1. preHandle(req, res, handler) invoked.");
		log.trace("======================================================");
		
		log.info("\t + 1. handler : {}", handler);
		// + 1. handler : org.zerock.myapp.controller.SampleController#doB(Locale, Model)
		
		log.info("\t + 2. handler type : {}", handler.getClass().getName());
		// + 2. handler type : org.springframework.web.method.HandlerMethod
		log.trace("\t ======================================================");
		
		HandlerMethod controllerHandler = (HandlerMethod) handler;
		Object controller = controllerHandler.getBean();
		Method method = controllerHandler.getMethod();
		
		log.info("\t + 3. controllerHandler : {}", controllerHandler);		// HandlerMethod 타입의 객체
		// + 3. controllerHandler : org.zerock.myapp.controller.SampleController#doB(Locale, Model)
		
		log.info("\t + 4. controller : {}", controller);					// SampleController 객체
		// + 4. controller : org.zerock.myapp.controller.SampleController@1b6656
		
		log.info("\t + 5. method : {}", method);							// SampleController 안에 있는 메소드
		// + 5. method : 
		// + public java.lang.String org.zerock.myapp.controller.SampleController.doB(java.util.Locale,org.springframework.ui.Model)
		// + throws org.zerock.myapp.exception.ControllerException
		log.trace("\t ======================================================");
		
		String clientAddr = req.getRemoteAddr();
		log.info("\t + clientAddr : {}", clientAddr);
		// + 접속하는 사람의 IP주소를 확인할 수 있다.
		log.trace("\t ======================================================");

		return true; // 기본값이 true로 설정되어 있다.
		// + return은 다음 체인으로 넘길지 말지 지정해 주는 것이다.
		// + true라면, 만약 다음 인터셉트가 있을 경우 다음으로 넘겨주고, 다음이 없다면 뒤의 컨트롤러 메소드를 수행한다.
		// + false라면, 만약 다음 인터셉트가 있을지라도 다음으로 넘겨주지 않는다.
		// + false일 경우에는 컨트롤러 메소드뿐만이 아니라, postHandle()과 afterCompletion()도 수행기키지 않는다!! (***)
		
	} // preHandle
	
	// =======================================================================================================

	// + 2. Controller의 Handler Method가 수행된 직후 ( 단, view가 호출되기 전 )
	// + (*주의*) 단, Controller의 Handler Method에서 예외가 발생하면, 아래의 메소드는 호출되지 못한다.
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.trace("============================================================");
		log.trace("2. postHandle(req, res, handler, modelAndView) invoked.");
		log.trace("============================================================");
		
		log.info("\t + 1. modelAndView : {}", modelAndView);
		// + 1. modelAndView : ModelAndView [view="home"; model={serverTime=/sample/doA}]
		// + 컨트롤러에서 반환하는 View의 이름과 model을 보여준다.
		
		log.trace("\t ======================================================");
		
		modelAndView.setViewName("redirect:/login");
		ModelMap model = modelAndView.getModelMap();
		model.put("serverTime", "*^^*");
		// + 이렇게 전달된 뷰의 이름이나 model을 수정할 수 있다. (**)
		log.info("\t + 2. update modelAndView : {}", modelAndView);

	} // postHandle
	
	// =======================================================================================================

	// + 3. View까지 호출이 완료되어, request에 대한 response가 전송완료된 직후
	// + (*주의*) 단, Controller의 Handler Method에서 예외가 발생하든 안하든, 무조건 호출된다.
	// + afterCompletion는 응답을 완전히 바꿔버릴 수도 있다.
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e)
			throws Exception {
		
		log.trace("======================================================");
		log.trace("3.afterCompletion(req, res, handler, e) invoked.");
		log.trace("======================================================");

	} // afterCompletion
	
	// =======================================================================================================

} // end class
