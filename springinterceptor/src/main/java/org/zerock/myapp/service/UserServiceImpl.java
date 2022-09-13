package org.zerock.myapp.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.UserDAO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	
	@Override
	public UserVO login(LoginDTO dto) throws ServiceException {
		log.trace("login({}) invoked.", dto);
		
		try {
			return this.userDAO.selectUser(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // login


	@Override
	public UserVO findUserByRememberMe(String rememberMe) throws ServiceException {
		log.trace("findUserByRememberMe({}) invoked.", rememberMe);
		
		try {
			return this.userDAO.selectUserByRememberMe(rememberMe);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // findUserByRememberMe


	@Override
	public boolean modifyUserWithRememberMe(String userid, String rememberMe, Date rememberAge) throws ServiceException {
		log.trace("modifyUserWithRememberMe({}, {}, {}) invoked.", userid, rememberMe, rememberAge);
		
		try {
			return this.userDAO.updateUserWithRememberMe(userid, rememberMe, rememberAge) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // findUserByRememberMe

} // end class
