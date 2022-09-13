package org.zerock.myapp.service;

import java.util.Objects;
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
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/**/root-context.xml"
})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private UserService userService;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.userService);
		log.info("\t+ this.userService: {}", this.userService);
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. UserService.login")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testLogin() throws ServiceException {
		log.trace("testLogin() invoked.");
		
		LoginDTO dto = new LoginDTO();
		dto.setUserid("USER_7");
		dto.setUserpw("PASS_7");
		
		UserVO vo = this.userService.login(dto);
		log.info("\t+ vo: {}", vo);
	} // testLogin
	

} // end class
