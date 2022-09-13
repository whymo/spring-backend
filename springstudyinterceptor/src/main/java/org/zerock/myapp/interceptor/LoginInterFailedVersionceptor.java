package org.zerock.myapp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.common.SharedScopeKeys;
import org.zerock.myapp.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class LoginInterFailedVersionceptor implements HandlerInterceptor {
	
	// ===================================================================================================
	// + 전제 :
	// + 로그인에 성공하면 UserVO를 Session Scope에 올려 놓을 예정이다.
	// ===================================================================================================
	// + /user/loginPost로 접속 -> preHandle -> LoginController의 로그인 처리 -> postHandle
	// + preHandle에서는 이전에 접속한 
	// ===================================================================================================

	// ===================================================================================================
	// + 1. preHandle : 로그인
	// ===================================================================================================
	// + Incoming Request가 Controller's Handler Method로 위임되기 직전에 가로 채는 부분
	// + Session Scope에 저장되어 있는 모든 정보를 파괴 수행
	// + (*주의*) 명시적으로 로그아웃 요청을 보내지 않는 이상, 세션 자체를 파괴해서는 안된다.
	// ===================================================================================================
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		log.info("========================================================");
		log.trace("1. preHandle(req, res, handler) invoked.");
		log.info("========================================================");
		
		// ===================================================================
		// 1. Session Scope에 접근할 수 있는 HttpSession 객체 획득
		// ===================================================================
		HttpSession session = req.getSession();
		
		// ===================================================================
		// 2. Session Scope에 UserVO 객체가 공유 되어 있으면 삭제 처리
		// ===================================================================
		UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.USER_KEY);
		// + 현재 Session Scope에 UserVO가 있는지, 로그인 되어있는 상태인지 확인
		
		if ( vo != null ) {
			session.removeAttribute(SharedScopeKeys.USER_KEY);
			log.info("\t + Remove UserVO : {}", vo);
		} else {
			log.info("\t + No UserVO found in Session Scope");
		} // if : null이 아니라면, UserVO가 Session Scope에 올려진 상태이기에 삭제해버린다.
		
		return true;
	} // preHandle
	
	// ===================================================================================================
	// + 2. postHandle : 로그인 성공 증빙 + 자동 로그인 처리
	// ===================================================================================================

	// + 만약 컨트롤러에서 예외가 발생하면, 아래의 callback 메소드는 실행되지 않는다.
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.info("========================================================");
		log.trace("2. postHandle(req, res, handler, {}) invoked.", modelAndView);
		log.info("========================================================");
		
		// ===================================================================
		// 1. 로그인 성공 증빙 데이터 바인딩
		// ===================================================================
		
		// + 매개변수인 modelAndView에 Model 상자 안에 UserVO 객체가 있는지 확인해 보고 ( 즉, 로그인 결과 )
		// + 만약 성공했다면, 이 UserVO 객체를 Session Scope에 있는 로그인 성공증빙으로 올려 놓기로 한다.
		
		// ===================================================================
		// 1 ) modelAndView 객체 안에 UserVO가 있는지 확인
		// ===================================================================
		ModelMap modelMap = modelAndView.getModelMap();
		UserVO vo = (UserVO) modelMap.getAttribute(SharedScopeKeys.LOGIN_KEY);
		
		// ===================================================================
		// 2 ) UserVO가 있다면( 로그인 성공 시 ) Session Scope에 UserVO를 올려 놓는다. ( 로그인 성공 증빙 )
		// ===================================================================
		if ( vo != null ) { // + 성공했을 때
			
			// + 현재 Session Scope에 접근하기 위해 HttpSession 객체 생성
			HttpSession session = req.getSession();
			
			// + Session Scope에 공유 데이터로 로그인 성공 증빙 데이터를 바인딩
			session.setAttribute(SharedScopeKeys.USER_KEY, vo);
			
			// ===================================================================
			// 2. 자동 로그인 처리
			// ===================================================================
			
			// ===================================================================
			// 1 ) 자동 로그인(rememberMe) 옵션의 on / off 여부 확인
			// ===================================================================
			boolean isRememberMeOption = checkRememberMeOption(req);
			log.info("\t + isRememberMeOption : {}", isRememberMeOption);
			
			if ( isRememberMeOption ) { // + 자동 로그인 기능 적용
				
				// + Response Message의 Header에 쿠키를 저장해서 보낸다.
				// + 이때 쿠키값으로는 현재 브라우저의 이름인 세션ID를 저장하자!
				// + (*주의*) 세션 ID는 UUID이다.
				
				String sessionId = session.getId();
				Cookie rememberMeCookie = new Cookie(SharedScopeKeys.REMEMBER_ME_KEY, sessionId);
				// + 쿠키는 전부 문자열이기에, 숫자로 보일지라도 문자열이다.
				// + public Cookie(String name, String value)
				
				rememberMeCookie.setMaxAge( 1 * 60 * 60 * 24 * 7 ); // 1주일
				// + 쿠키의 유효기간( 자동 로그인 유효기간 ) 설정
				// + .setMaxAge의 단위는 초이다.
				
				rememberMeCookie.setPath("/");
				
				// ===================================================================
				// + 쿠키 확인
				// ===================================================================
				log.info("\t + rememberMeCookie - 1. name : {}", rememberMeCookie.getName());
				log.info("\t + rememberMeCookie - 2. value : {} ", rememberMeCookie.getValue());
				log.info("\t + rememberMeCookie - 3. path : {} ", rememberMeCookie.getPath());
				log.info("\t + rememberMeCookie - 4. MaxAge : {} ", rememberMeCookie.getMaxAge());
				
				res.addCookie(rememberMeCookie);
				// + 응답 메세지의 'set-cookie' 헤더에 자동설정된다.
				
			} // inner if
			
		} // if : 성공했다면

	} // postHandle
	
	// ===================================================================================================
	// + 3. afterCompletion : 주로 자원 해제하는데 사용
	// ===================================================================================================
	// + afterCompletion은 view처리가 끝난 후 User에게 응답이 나가기 직전에 인터셉트하게 되는데,
	// + 만약 처리할게 없다면, 삭제해도 된다.
	// ===================================================================================================

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e)
			throws Exception {
		
		log.info("========================================================");
		log.trace("3. afterCompletion(req, res, handler, handler, e) invoked.");
		log.info("========================================================");
		
		// ===================================================================
		// 1. Session Scope에 인증정보(UserVO)의 바인딩 되어있는지 여부 확인
		// ===================================================================
		HttpSession session = req.getSession();
		UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.USER_KEY);
		
		// + 인증이 되어있지 않은 경우, 자동 로그인도 불가능하게 해야 한다.
		if ( vo != null ) {
			
			// ===================================================================
			// 2. 자동 로그인(rememberMe) 옵션의 on / off 여부 확인
			// ===================================================================
			boolean isRememberMeOption = checkRememberMeOption(req);
			log.info("\t + isRememberMeOption : {}", isRememberMeOption);
			
			if ( isRememberMeOption ) { // + 자동 로그인 기능 적용
				
				// + Response Message의 Header에 쿠키를 저장해서 보낸다.
				// + 이때 쿠키값으로는 현재 브라우저의 이름인 세션ID를 저장하자!
				// + (*주의*) 세션 ID는 UUID이다.
				
				String sessionId = session.getId();
				Cookie rememberMeCookie = new Cookie(SharedScopeKeys.REMEMBER_ME_KEY, sessionId);
				// + 쿠키는 전부 문자열이기에, 숫자로 보일지라도 문자열이다.
				// + public Cookie(String name, String value)
				
				rememberMeCookie.setMaxAge( 1 * 60 * 60 * 24 * 7 ); // 1주일
				// + 쿠키의 유효기간( 자동 로그인 유효기간 ) 설정
				// + .setMaxAge의 단위는 초이다.
				
				rememberMeCookie.setPath("/");
				
				// ===================================================================
				// + 쿠키 확인
				// ===================================================================
				log.info("\t + rememberMeCookie - 1. name : {}", rememberMeCookie.getName());
				log.info("\t + rememberMeCookie - 2. value : {} ", rememberMeCookie.getValue());
				log.info("\t + rememberMeCookie - 3. path : {} ", rememberMeCookie.getPath());
				log.info("\t + rememberMeCookie - 4. MaxAge : {} ", rememberMeCookie.getMaxAge());
				
				res.addCookie(rememberMeCookie);
				// + 응답 메세지의 'set-cookie' 헤더에 자동설정된다.
				
			} // inner if
			
		} // if
		
	} // afterCompletion
	
	// ===================================================================================================
	// + 4. 자동 로그인 on / off 체크 여부 확인 메소드
	// ===================================================================================================
	
	private boolean checkRememberMeOption(HttpServletRequest req) {
		
		log.trace("checkRememberMeOption(req) invoked.");
		
		String rememberMe = req.getParameter("rememberMe");
		log.info("\t + rememberMe : {}",rememberMe);
		// + 전송 파라미터 중에서 rememberMe로 들어온 값을 변수 rememberMe에 저장
		
		return rememberMe != null;
		// + rememberMe가 null이 아니면 true를 반환하고,
		// + rememberMe가 null이라면 false를 반환한다.
		
	} // checkRememberMeOption
	
	// ===================================================================================================

} // end class
