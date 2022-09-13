package org.zerock.myapp.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@Repository
public class UserDAOImpl implements UserDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	
	@Override
	public UserVO selectUser(LoginDTO dto) throws DAOException {
		log.trace("selectUser({}) invoked.", dto);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			String namespace = "org.zerock.myapp.persistence.UserDAO";
			String sqlId = "selectUser";
			String sql = namespace +"." + sqlId;
			
			return sqlSession.<UserVO>selectOne(sql, dto);
		} catch(Exception e) {
			throw new DAOException(e);
		} // try-with-resources
	} // selectUser


	@Override
	public int updateUserWithRememberMe(String userid, String rememberMe, Date rememberAge) throws DAOException {
		log.trace("selectUser({} ,{} ,{}) invoked.", userid, rememberMe, rememberAge);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			String namespace = "org.zerock.myapp.persistence.UserDAO";
			String sqlId = "updateUserWithRememberMe";
			String sql = namespace +"." + sqlId;
			
			Map<String, Object> params = new HashMap<>();
			params.put("userid", userid);
			params.put("rememberMe", rememberMe);
			params.put("rememberAge", rememberAge);
			
			return sqlSession.update(sql, params);
		} catch(Exception e) {
			throw new DAOException(e);
		} // try-with-resources
	} // updateUserWithRememberMe


	@Override
	public UserVO selectUserByRememberMe(String rememberMe) throws DAOException {
		log.trace("selectUserByRememberMe({}) invoked.", rememberMe);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			String namespace = "org.zerock.myapp.persistence.UserDAO";
			String sqlId = "selectUserByRememberMe";
			String sql = namespace +"." + sqlId;
			
			return sqlSession.<UserVO>selectOne(sql, rememberMe);
		} catch(Exception e) {
			throw new DAOException(e);
		} // try-with-resources
	} // selectUserByRememberMe

} // end class
