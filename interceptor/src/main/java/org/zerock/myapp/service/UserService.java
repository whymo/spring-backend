package org.zerock.myapp.service;

import java.util.Date;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;



public interface UserService {
	
	
	public UserVO 	login(LoginDTO dto) throws Exception;
	public UserVO 	findUserByRememberMe(String rememberMe) throws Exception;
	public void 	modifyUserWithRememberMe(String userId, String rememberMe, Date rememberAge) throws Exception;

} // end interface
