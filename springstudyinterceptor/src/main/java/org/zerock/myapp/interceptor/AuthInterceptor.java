package org.zerock.myapp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.zerock.myapp.common.SharedScopeKeys;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.service.UserService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class AuthInterceptor implements HandlerInterceptor { // 인증, 인가 처리
	
	@Autowired
	private UserService service;
	
	// ===================================================================================================
	// + 1. preHandle
	// ===================================================================================================
	// + (1) 게시판에 관련된 모든 요청에 대해서 인증된 사용자(브라우저)인지를 가장 먼저 체크하고
	// + 만약, 인증되지 않은 사용자(웹 브라우저)라면, 로그인을 하도록 로그인 창으로 리다이렉트해야 한다,
	// + 위의 로직은 모든 인증 기능에서 가장 먼저 수행되어야 할 "공통 보안 로직"이다.
	// ===================================================================================================
	// + (2) 자동로그인 기능이 on되어있는 경우, 특수한 처리를 해야 된다.
	// + 	( 로그인 성공 인증 정보는 없는데, 자동 로그인 쿠키가 요청으로 날라오는 경우 )
	// ===================================================================================================
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		log.info("========================================================");
		log.trace("1. preHandle(req, res, handler) invoked.");
		log.info("========================================================");
		
		// =======================================================================
		// 1. 현재 요청 URI를 전송한 사용자(웹 브라우저)의 인증여부 확인
		// =======================================================================
		
		HttpSession session = req.getSession();
		log.info("\t + 1. Session ID : {}", session.getId() );
		
		UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.USER_KEY);
		log.info("\t + 2. vo : {}", vo);
		// + Session Scope에 있는 공유 데이터 중 로그인 성공 데이터를 찾는다.
		
		if ( vo != null ) { // + 인증된 사용자(웹 브라우저)라면...
			
			log.info("\t + 3. Already Authenticated : {}", vo);
			
			return true;
			// + 요청을 컨트롤러의 헨들러로 넘긴다.
			
		} else { // + 인증되지 않은 사용자(웹 브라우저)라면...
			
			// =======================================================================
			// 2. 자동로그인 처리
			// =======================================================================
			// + 자동 로그인 처리에 필요한 처리는 2가지
			// + (1) 현재 웹 브라우저에 할당된 Session Scope에 UserVO(인증정보)를 복구해줘야 한다. (***)
			// + (2) (1) 조치를 해놓은 상태에서 무사통과시켜야 한다.
			// =======================================================================
			
			Cookie rememberMeCookie = WebUtils.getCookie(req, SharedScopeKeys.REMEMBER_ME_KEY);
			// + WebUtils.getCookie(HttpServletRequest request, String name);
			// + 자동 로그인 처리 쿠키가 존재하는지 확인한다.
			
			// =======================================================================
			// 1 ) + 자동 로그인 쿠키가 존재할 경우 (***)
			// =======================================================================
			
			if ( rememberMeCookie != null ) {
				
				String cookieName = rememberMeCookie.getName();
				String cookieValue = rememberMeCookie.getValue();
				
				log.info("\t + 4. This User has rememberMeCookie.");
				log.info( "\t + 5. rememberMeCookie found - name : {}, value : {}", cookieName, cookieValue );
				
				UserVO userVO = this.service.findUserByRememberMe(cookieValue);
				log.info("\t + 6. Found userVO : {}", userVO);
				// + 이를 통해서 쿠키의 값을 통해서, userVO객체를 얻어낸다.
				
				session.setAttribute(SharedScopeKeys.USER_KEY, userVO);
				// + findUserByRememberMe를 통해 UserVO객체를 받아와 로그인 성공증빙과 같이 만든다.
				
				return true;
				// + 쿠키가 있을 경우 로그인 성공 인증정보가 없어도 무사히 통과할 수 있도록
				// + 요청을 컨트롤러의 헨들러로 넘긴다.
				
			} // if : 자동 로그인 처리 수행 -> 무사통과
			
			// =======================================================================
			// 2 ) + 자동 로그인 쿠키가 존재하지 않을 경우
			// =======================================================================
			
			log.info("\t + NO Authenticated User");
			
			res.sendRedirect("/user/login");
			// + 로그인 창으로 리다이렉트해버린다.
			
			return false;
			// + 요청을 컨트롤러의 헨들러로 넘기지 않는다.
			// + 인증되지 않은 사용자의 경우에는 게시물로 접근이 불가능해야 한다.
			
		} // if - else
		
	} // preHandle
	
	// ===================================================================================================
	// + 2. postHandle
	// ===================================================================================================

	// + 만약 컨트롤러에서 예외가 발생하면, 아래의 callback 메소드는 실행되지 않는다.
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.info("========================================================");
		log.trace("2. postHandle(req, res, handler, {}) invoked.", modelAndView);
		log.info("========================================================");

	} // postHandle
	
	// ===================================================================================================
	// + 3. afterCompletion
	// ===================================================================================================

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e)
			throws Exception {
		
		log.info("========================================================");
		log.trace("3. afterCompletion(req, res, handler, handler, e) invoked.");
		log.info("========================================================");
		
	} // afterCompletion
	
} // end class
