package org.zerock.myapp.persistence;

import java.util.Date;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;


public interface UserDAO {
	
	
	public UserVO selectUser(LoginDTO dto) throws Exception;
	public UserVO selectUserWithRememberMe(String rememberMe) throws Exception;
	public int updateUserWithRememberMe(String userId, String rememberMe, Date rememberAge) throws Exception;

} // end interface
