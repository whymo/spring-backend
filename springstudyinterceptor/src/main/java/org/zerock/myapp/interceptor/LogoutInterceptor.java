package org.zerock.myapp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import org.zerock.myapp.common.SharedScopeKeys;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.service.UserService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class LogoutInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService service;
	
	// ===================================================================================================
	// + 1. preHandle
	// ===================================================================================================
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		log.info("========================================================");
		log.trace("1. preHandle(req, res, handler) invoked.");
		log.info("========================================================");
		
		// =======================================================================================
		// 1. 현재 브라우저에 할당된 Session Scope를 모두 파괴하고, ( 즉, 로그인 성공증빙도 파괴 )
		//    웹 브라우저에 할당된 이름( Session ID )도 무효화시킨다.
		// =======================================================================================
		
		HttpSession session = req.getSession();
		String sessionId = session.getId();
		log.info("\t + sessionID : {}", sessionId);
		
		UserVO userVO = (UserVO) session.getAttribute(SharedScopeKeys.USER_KEY);
		// + 로그아웃을 하기 위해서 로그아웃을 할 UserVO를 얻어낸다. ( 3단계에서 사용 )
		
		session.invalidate();
		log.info("\t + Current Session Scope Destroyed.");
		
		// =======================================================================================
		// 2. 로그인 성공시, 자동 로그인 옵션도 on시켜 놓은 웹 브라우저가 로그아웃을 명시적으로 수행하는 경우로 
		// 1단계도 수행되어야 하지만 자동로그인 쿠키도 삭제해줘야 한다. (***)
		// =======================================================================================
		
		Cookie rememberMeCookieToBeDestroyed = WebUtils.getCookie(req, SharedScopeKeys.REMEMBER_ME_KEY );
		// + WebUtils.getCookie(HttpServletRequest request, String name)
		// + Spring에서는 WebUtils.getCookie를 통해서 원해는 쿠키를 얻어낼 수 있다.
		
		if ( rememberMeCookieToBeDestroyed != null ) {
			
			rememberMeCookieToBeDestroyed.setMaxAge(0);
			// + 쿠키는 유효기간을 0으로 설정함으로써 즉시 파괴할 수 있다.
			
			rememberMeCookieToBeDestroyed.setPath("/");
			res.addCookie(rememberMeCookieToBeDestroyed);
			
		} // if
		
		// =======================================================================================
		// 3. 명시적으로 로그아웃 하는 것이므로, tbl_User 테이블에 있는 자동 로그인 쿠키 컬럼(rememerme)과
		// expire 일시를 기록한 rememberage 칼럼의 값을 모두 Null로 제거해 줘야 한다.
		// 그래서 명시적 로그아웃한 이후에는 더 이상 자동 로그인이 불가능하게 해야 한다. (*****)
		// =======================================================================================
		
		this.service.modifyUserWithRememberMe(userVO.getUserid(), null, null);
		// + .modifyUserWithRememberMe(String userid, String rememberMe, Date rememberAge)
		
		// =======================================================================================
		// 4. 최종적으로 로그인 화면으로 리다이렉트 수행
		// =======================================================================================
		
		req.getSession().setAttribute(SharedScopeKeys.RESULT, "Signed Out Successfully");
		// + rttrs.addAttribute(key, value)와 동일한 기능을 한다.
		
		res.sendRedirect("/user/login");
		// + 최종적으로 로그인 화면 창으로 리다이렉트 수행
		// + Redirect할 경우에는 Session Scope이 파괴된다는 것을 잊지 말아야 한다.
		// + 그렇기에, 로드인 페이지에서는 Signed Out Successfully 메시지를 얻어낼 수 없게 된다.
		
		return false;
		// + 로그아웃 요청을 컨트롤러의 핸들러로 요청을 보내지 않는다. (***)
		// + 그러나, URI는 필요하기에 컨트롤러의 메소드를 삭제하면 인터셉터도 수행이 불가능하다.
		// + 로그인 컨트롤러의 로그아웃 메소드는 단순히 형식으로만 존재하게 된다.
		
	} // preHandle
	
	// ===================================================================================================
	// + 2. postHandle
	// ===================================================================================================
	// + 컨트롤러 헨들러에서 예외가 발생하면, 아래의 callback을 수행되지 않는다.
	// ===================================================================================================

//	@Override
//	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) 
//			throws Exception {
//		
//		log.info("=======================================================================");
//		log.trace("2. postHandle(req, res, handler, {}) invoked.", modelAndView);
//		log.info("=======================================================================");
//		
//		// + 현재 브라우저에 할당된 Session Scope를 모두 파괴하고, ( 즉, 로그인 성공증빙도 파괴 )
//		// + 웹 브라우저에 할당된 이름( Session ID )도 무효화시킨다.
//		
//		HttpSession session = req.getSession();
//		String sessionId = session.getId();
//		log.info("\t + sessionID : {}", sessionId);
//		
//		session.invalidate();
//		log.info("\t + Current Session Scope Destroyed.");
//		
//	} // postHandle

} // end class
