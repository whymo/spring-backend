package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// JUNIT 5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTests {
	
	// ====================================================================================
	
	@Autowired
	private UserDAO userDAO; // 의존성 주입
	
	// ====================================================================================
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.userDAO);
		log.info("\t + this.userDAO : {}", this.userDAO);
	} // beforeAll
	
	// ====================================================================================
	// 1. 로그인 창에서 입력한 아이디와 암호에 매칭되는 사용자의 정보 획득
	
	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. UserDAO.selectUser")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testSelectUser () throws DAOException {
		
		log.trace("testSelectUser() invoked.");
		
		LoginDTO dto = new LoginDTO();
		dto.setUserid("USER_7");
		dto.setUserpw("PASS_7");
		dto.setRememberMe(false);
		
		UserVO vo = this.userDAO.selectUser(dto);
		log.info("\t + vo : {}", vo);
		
	} // testSelectUser
	
	// ====================================================================================
	
	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. UserDAO.updateUserWithRememberMe")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testupdateUserWithRememberMe () throws DAOException {
		
		log.trace("testupdateUserWithRememberMe() invoked.");
		
		String userid = "USER_9";
		String rememberMe = "7D8860FAED04BB82750566D763D3D969"; // + 세션 아이디
		
		// + 쿠키는 기본단위를 초로 카운팅한다.
		long oneWeekTime = 1000 * 1 * 60 * 60 * 24 * 7;		// + 밀리세컨드 단위로 맞춰준다.
		long now = System.currentTimeMillis();				// + 밀리세건드 단위이다.
		
		Date rememberAge = new Date(now + oneWeekTime);
		// + rememberMe 쿠키의 유효기간에 맞게 미래싯점의 Timestamp로 변환
		
		int affectedLines = this.userDAO.updateUserWithRememberMe(userid, rememberMe, rememberAge);
		log.info("\t + affectedLines : {}",affectedLines);
		
	} // testupdateUserWithRememberMe
	
	// ====================================================================================
	
	// @Disabled
	@Test
	@Order(3)
	@DisplayName("3. UserDAO.selectUserByRememberMe")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testselectUserByRememberMe () throws DAOException {
		
		log.trace("testselectUserByRememberMe() invoked.");
		
		String rememberMe = "7D8860FAED04BB82750566D763D3D969";
		
		UserVO vo = this.userDAO.selectUserByRememberMe(rememberMe);
		log.info("\t + vo : {}", vo);
		
	} // testselectUserByRememberMe

} // end class
