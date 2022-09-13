package org.zerock.myapp.controller;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.WebUtils;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/user")
@Controller("userController")
public class UserController {

	private static final String loginKey = "__LOGIN__";
	private static final String rememberMeKey = "__REMEMBER_ME__";		// Original Remember-Me Cookie name
	
	@Setter(onMethod_= {@Autowired})
	private UserService service;

	
	
	@PostMapping("/loginPost")
	public void loginPost(
			/* @ModelAttribute("loginDTO") */ LoginDTO dto,
			Model model,
			HttpSession session ) throws Exception {
		
		log.debug("loginPost(dto, model) invoked.");
		
		log.info("\t+ dto: " + dto);
		
		Objects.requireNonNull(this.service);
		log.info("\t+ service: " + this.service);
		
		//-------------------------------------------------------------//
		// 1. To check the user.
		//-------------------------------------------------------------//
		UserVO user = this.service.login(dto);		// To check the user.
		
		if(user != null) {	// if the check succeeded.
			
			model.addAttribute(loginKey, user);		// To bind login attribute to the request scope.
			
			//-------------------------------------------------------------//
			// 2. If rememberMe on, process Remember-Me option.
			//-------------------------------------------------------------//
			if(dto.isRememberMe()) {
				int timeAmount = 1000 * 60 * 60 * 24 * 7;	// 7 days.
				
				String userId = dto.getUserid();
				String rememberMe = session.getId();
				Date rememberAge = new Date(System.currentTimeMillis() + timeAmount);
				
				this.service.modifyUserWithRememberMe(userId, rememberMe, rememberAge);
			} // if
			
		} // if
		
	} // loginPost
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		log.debug("logout(req, res, session) invoked.");
		
		//-------------------------------------------------------------//
		// 1. To get login info from http session.
		//-------------------------------------------------------------//
		UserVO user = (UserVO) session.getAttribute(loginKey);
		log.info("\t+ user: " + user);
		
		//-------------------------------------------------------------//
		// 2. To destroy current http session.
		//-------------------------------------------------------------//
		session.invalidate();
		log.info("\t+ session destroyed("+session.getId()+")");
		
		//-------------------------------------------------------------//
		// 3. To destroy Remember-Me cookie.
		//-------------------------------------------------------------//
		Cookie destroyRememberMeCookie = WebUtils.getCookie(req, rememberMeKey);
			if(destroyRememberMeCookie != null) {
			destroyRememberMeCookie.setPath("/");
			destroyRememberMeCookie.setMaxAge(0);
			
			res.addCookie(destroyRememberMeCookie);
		} // if
		
		//-------------------------------------------------------------//
		// 4. To update tbl_user.
		//-------------------------------------------------------------//
		if(user != null) {
			String userId = user.getUserid();
			
			this.service.modifyUserWithRememberMe(userId, null, null);
		} // if
		
		//-------------------------------------------------------------//
		// 5. To redirect into the login form.
		//-------------------------------------------------------------//
		return "redirect:/user/login";
	} // logout
	
	
} // end class
