package org.zerock.myapp.service;

import java.util.Date;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.exception.ServiceException;

public interface UserService {

	// =====================================================================
	// 1. 로그인 창에서 입력한 아이디와 암호에 매칭되는 회원이 있는지 확인
	// =====================================================================
	// ReturnType을 Boolean이 아닌 UserVO로 하는 이유는,
	// 이 객체를 바로 로그인 성공 정보로 Session Scope에 바인딩시키기 위해서이다.
	public abstract UserVO login(LoginDTO dto) throws ServiceException;
	
	// =====================================================================
	// 2. 자동 로그인 설정이 on된 상태로 로그인 성공시, 쿠키 정보를 기록
	// =====================================================================
	public abstract boolean modifyUserWithRememberMe( String userid, String rememberMe, Date rememberAge ) throws ServiceException;

	// =====================================================================
	// 3. 자동 로그인 쿠키값으로 사용자를 조회하여 인증정보를 생성
	// =====================================================================
	public abstract UserVO findUserByRememberMe(String rememberMe) throws ServiceException;
	
} // end interface
