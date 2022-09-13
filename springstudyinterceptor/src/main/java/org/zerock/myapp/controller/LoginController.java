package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.common.SharedScopeKeys;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.UserService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/user/")
@Controller
public class LoginController {
	
	@Autowired
	private UserService service;
	
	// ===============================================================================================================
	// + 1. 로그인 처리
	// ===============================================================================================================
	
	@PostMapping("/loginPost")
	// + @ModelAttribute 어노테이션을 통해 모델에 저장할 수 있다.
	public String loginPost ( /* @ModelAttribute("loginDTO") */ LoginDTO dto, Model model, RedirectAttributes rttrs ) 
			throws ControllerException {
		
		log.trace("loginPost({}, {}) invoked.", dto, model);
		// + loginPost(LoginDTO(userid=hohohoh, userpw=1234, rememberMe=true)) invoked.
		// + view에서 post로 정보가 들어온다.
		
		try {
			
			UserVO vo = this.service.login(dto);
			log.info("\t + vo : {}", vo);
			
			if( vo != null ) { 							// 성공했을 때
				
				model.addAttribute(SharedScopeKeys.LOGIN_KEY, vo);
				
				return "user/loginPost";
				// + redirect로 할 경우에는 Request Scope이 깨지기 때문에, model안의 vo가 나오지 못한다.
				
			} else { 									// 실패했을 때
				
				rttrs.addAttribute(SharedScopeKeys.RESULT, "Login Failed.");
				return "redirect:/user/login";
				
			} // if - else
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try - catch
		
	} // loginPost
	
	// ===============================================================================================================
	// + 2. 로그 아웃 처리
	// ===============================================================================================================
	// + 로그 아웃 요청시에 수행되어야 할 일 : --> LogoutInterceptor가 수행
	// + 1. Session Scope 자체를 파괴시켜야 한다. ( 즉, session.invalidate() 메소드 수행 )
	// + 2. 현재 웹 브라우저에 할당된 이름( 세션 ID )도 삭제
	// ===============================================================================================================
	
	@GetMapping("/logout")
	public void logout( RedirectAttributes rttrs ) throws ControllerException {
		
		log.trace("logout() invoked.");
		// + 로그아웃은 LogoutInterceptor를 설정하기 위한 용도로만 사용될 뿐, 진짜 호출되지는 않는다.
		
	} // logout
	
	// ===============================================================================================================

} // end class
