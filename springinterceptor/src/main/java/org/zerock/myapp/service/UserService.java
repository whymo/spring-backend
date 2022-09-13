package org.zerock.myapp.service;

import java.util.Date;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.ServiceException;


public interface UserService {
	
	
	// -------------------------------------------------------------------- //
	// 1. 로그인 창에서 입력한 아이디와 암호에 매칭되는 회원이 있는지 확인
	// -------------------------------------------------------------------- //
	// Return type을 Boolean 으로 하지 않고, UserVO로 하는 이유는, 
	// 이 객체를 바로 로그인 성공 정보로 Session Scope에 바인딩 시키기 위해서임.
	public abstract UserVO login(LoginDTO dto) throws ServiceException;

	// -------------------------------------------------------------------- //
	// 2. 자동로그인 쿠키값으로 사용자를 조회하여 인증정보(UserVO)를 생성
	// -------------------------------------------------------------------- //
	public abstract UserVO findUserByRememberMe(String rememberMe) throws ServiceException;
	
	// -------------------------------------------------------------------- //
	// 3. 자동로그인 쿠키값으로 사용자를 조회하여 인증정보(UserVO)를 생성
	// -------------------------------------------------------------------- //
	public abstract boolean modifyUserWithRememberMe(String userid, String rememberMe, Date rememberAge) throws ServiceException;
	

} // end interface
