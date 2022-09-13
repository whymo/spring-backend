package org.zerock.myapp.aop;

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
import org.zerock.myapp.service.SampleService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogAdviceTests {
	
	@Setter(onMethod_= {@Autowired})
	private SampleService service;
	
	// =======================================================
	// + 1. Service 객체 확인
	// =======================================================
	
	@BeforeAll
	void beforeAll() {
		
		log.trace("\t + beforeAll() invoked.");
		
		Objects.requireNonNull(this.service);
		log.info("\t + 1. service : {}", this.service);
		// + 1. service : org.zerock.myapp.service.SampleServiceImpl@f000000
		log.info("\t + 2. ****** type(proxy) ****** : {}", this.service.getClass().getName());
		// + 2. ****** type(proxy) ****** : com.sun.proxy.$Proxy43 ( proxy 객체가 들어온다. )
		
	} // beforeAll
	
	// =======================================================
	// + 2. Advice가 발생하는지 확인
	// =======================================================
	
	@Test
	@Order(1)
	@DisplayName("1. testDoAdd")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testDoAdd() throws Exception {
		
		log.trace("\t + testDoAdd() invoked.");
		
		int result = this.service.doAdd("1000", "200");
		//int result = this.service.doAdd("1000", "이백이다아아앙"); // + afterThrowing 확인
		log.info("\t + result : {}", result);
		
	} // testDoAdd
	
	// =======================================================
	// + 3. @Around가 발생하는지 확인
	// =======================================================
	
	@Test
	@Order(2)
	@DisplayName("2. testjoinPoint")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testjoinPoint() throws Exception {
		
		log.trace("\t + testjoinPoint() invoked.");
		
		String result = this.service.joinPoint("이츠미", "마리오오오오");
		log.info("\t + result : {}", result);
		
	} // testjoinPoint
	
} // end class
