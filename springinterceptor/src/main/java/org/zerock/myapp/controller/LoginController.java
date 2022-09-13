package org.zerock.myapp.controller;

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

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@RequestMapping("/user/")
@Controller
public class LoginController {
	
	private UserService service;
	
	
	
	// 1. Login 처리
	@PostMapping("loginPost")
	public String loginPost(LoginDTO dto, RedirectAttributes rttrs, Model model)
		throws ControllerException {
		log.trace("loginPost({}, {}) invoked.", dto, rttrs);
		
		try {
			UserVO vo = this.service.login(dto);
			log.info("\t+ vo: {}", vo);
			
			if(vo != null) {	// if sign-in succeed,				
				// If redirected, model not transfered due to the 307 redirect response.
				model.addAttribute(SharedScopeKeys.LOGIN_KEY, vo);
				
				return "user/loginPost";
			} else {			// if sign-in failed,
				// Request Scope Binding
				rttrs.addFlashAttribute(SharedScopeKeys.RESULT_KEY, "Login Failed");
				
				return "redirect:/user/login";
			} // if-else
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // loginPost
	
	
	// 2. Logout 처리 - 목적: LogoutInterceptor를 설정하기 위한 용도로만 사용될 뿐, 진짜 호출되지는 못한다!!
	//    실제 로그아웃을 위한 모든 처리는 LogoutInterceptor에서 처리 (***)
	@GetMapping("/logout")
	public void dummyLogout() throws ControllerException {
		log.trace("dummyLogout() invoked.");
		
	} // dummyLogout
	

} // end class
