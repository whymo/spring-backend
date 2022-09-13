package org.zerock.myapp.persistance;

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
import org.zerock.myapp.mapper.TimeMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// JUNIT5 - 테스트 메소드 수행시 Spring FrameWork를 구동시키는 어노테이션
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TimeMapperTests {
	
	//======================================================
	
	@Setter(onMethod_= { @Autowired })
	private TimeMapper timeMapper;
	
	//======================================================
	
	@BeforeAll
	void beforeAll() {
		
		log.info("beforeAll() invoked.");
		
		Objects.requireNonNull(this.timeMapper);
		log.info("\t + this.timeMapper : {}", this.timeMapper);
		// + this.timeMapper : org.apache.ibatis.binding.MapperProxy@44114b9f
		
	} // beforeAll
	
	//======================================================
		// Bean으로 출력 (***)
	//======================================================
	
	@Test
	@Order(1)
	@DisplayName("1. testGetCurrentTime1")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testGetCurrentTime1 () {
		
		log.info("testGetCurrentTime1() invoked.");
		
		String now = this.timeMapper.getCurrentTime1();
		log.info("\t + >>>> 1. now : {} <<<<",now);
		
	} // testGetCurrentTime1
	
	//======================================================
		// 마이바티스의 XML mapper 자동실행 규칙 (*****)
	//======================================================
	
	@Test
	@Order(2)
	@DisplayName("2. testGetCurrentTime2")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testGetCurrentTime2 () {
		
		log.info("testGetCurrentTime2() invoked.");
		
		String now = this.timeMapper.getCurrentTime2();
		log.info("\t + >>>> 2. now : {} <<<<",now);
		
	} // testGetCurrentTime2
	
	//======================================================

} // end class
