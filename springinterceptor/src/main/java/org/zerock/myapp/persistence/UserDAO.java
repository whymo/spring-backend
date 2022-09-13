package org.zerock.myapp.persistence;

import java.util.Date;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.DAOException;


public interface UserDAO {
	
	
	// 1. 로그인 창에서 입력한 아이디와 암호에 매칭되는 사용자의 정보 회득
	public abstract UserVO selectUser(LoginDTO dto) throws DAOException;
	
	// 2. 자동로그인 설정이 on된 상태로 로그인 성공 수행시, 쿠키정보를 기록
	public abstract int updateUserWithRememberMe(String userid, String rememberMe, Date rememberAge) throws DAOException;
	
	// 3. 자동로그인 쿠키값으로 사용자를 조회하여 인증정보를 생성
	public abstract UserVO selectUserByRememberMe(String rememberMe) throws DAOException;

} // end interface
