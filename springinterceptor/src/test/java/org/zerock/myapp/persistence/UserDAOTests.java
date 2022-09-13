package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
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
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// For JUnit 4
//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)

// For JUnit 5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTests {
	
	@Setter(onMethod_= {@Autowired})
	private UserDAO userDAO;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.userDAO);
		log.info("\t+ this.userDAO: {}", this.userDAO);
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. UserDAO.selectUser")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testSelectUser() throws DAOException {
		log.trace("testSelectUser() invoked.");
		
		LoginDTO dto = new LoginDTO();
		dto.setUserid("USER_7");
		dto.setUserpw("PASS_7");
		dto.setRememberMe(false);
		
		UserVO vo = this.userDAO.selectUser(dto);
		log.info("\t+ vo: {}", vo);
	} // testSelectUser
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. UserDAO.updateUserWithRememberMe")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdateUserWithRememberMe() throws DAOException {
		log.trace("testUpdateUserWithRememberMe() invoked.");
		
		String userid = "USER_3";
		String rememberMe = "B3545DE081ACAEDA3044D9C1625B25F2";
		
		// rememberMe 쿠키의 유효기간(MaxAge, 기본 7일)에 맞게 미래싯점의 TimeStamp 로 변환
		long oneWeek = 1000 * 60 * 60 * 24 * 7;	// in milliseconds.
		long now = System.currentTimeMillis();		// in milliseconds.
		
		Timestamp rememberAge = new Timestamp(now + oneWeek);
//		Date rememberAge = new Date(now + oneWeek);
		
		int affectedLines = this.userDAO.updateUserWithRememberMe(userid, rememberMe, rememberAge);
		log.info("\t+ affectedLines: {}", affectedLines);
	} // testUpdateUserWithRememberMe
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. UserDAO.selectUserByRememberMe")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testSelectUserByRememberMe() throws DAOException {
		log.trace("testSelectUserByRememberMe() invoked.");
		
		String rememberMe = "B3545DE081ACAEDA3044D9C1625B25F2";
		
		UserVO vo = this.userDAO.selectUserByRememberMe(rememberMe);
		log.info("\t+ vo: {}", vo);
	} // testSelectUserByRememberMe

} // end class
