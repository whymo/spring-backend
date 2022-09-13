package org.zerock.myapp.di;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
import org.zerock.myapp.sample.Hotel;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


// Lombok's annotation
@Log4j2
@NoArgsConstructor // JUNIT 테스트 클래스의 필수 사항

//================================================
// For JUnit 5 (***)
// JUnit 테스트 메소드 수행시, Spring FrameWork를 함께 구동시키는 구동자 설정
// @ExtendWith는 스프링 프레임워크 구동하는 클래스 지정
@ExtendWith(SpringExtension.class)
//================================================
// For JUnit 4
// JUnit 테스트 메소드 수행시, Spring FrameWork를 함께 구동시키는 구동자 설정
// @RunWith(SpringJUnit4ClassRunner.class)
// @RunWith(SpringRunner.class)
//================================================

//================================================
// JUnit4와 5에서 모두 공통으로 함께 실행될 Spring FrameWork의 설정파일을 알려주는 역할
// file로 지정하면 절대 경로가 아니라 프로젝트 아래의 파일을 확인하게 된다.
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
//================================================

// JUnit-jupiter's annotation
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DiBySetterTests {
	
	// ================================================
	// 의존성 주입(DI) 어노테이션 종류 
	// ================================================
	
	// @Setter가 가장 추천되는 방식이다. - 세터 메소드를 통해 주입된다. (****)
	// @Setter(onMethod_ = @Resource)
	// @Setter(onMethod_ = @Inject)
	// @Setter(onMethod_ = @Autowired)
	
	// @Resource
	// @Inject
	@Autowired
	private Hotel hotel;
	
	// ================================================
	// JUnit5에서는 자유롭게 이름을 작성해도 된다.
	// ================================================
	
	@Test
	@DisplayName("contextLoads")
	@Order(1)
	@Timeout(value=1000, unit = TimeUnit.MILLISECONDS)
	void contextLoads() {
		
		log.trace("contextLoads() invoked.");
		
		Objects.requireNonNull(this.hotel);
		log.info("\t + this.hotel : {}", this.hotel);
		// + this.hotel : Hotel(chef=Chef()) ( 호텔 객체가 들어왔음을 알 수 있다. )
		// + 호텔 객체 빈 안에도 세프 객체 빈이 필드로 주입된 것을 알 수 있다.
		
	} // contextLoads
	
	// ================================================
	// JUnit4 버전으로 이름 짓기
	// ================================================
	
	@Test
	@DisplayName("testDependencyInjection")
	@Order(2)
	@Timeout(value=1000, unit = TimeUnit.MILLISECONDS)
	void testDependencyInjection() {
		
		log.trace("testDependencyInjection() invoked.");
		
		Objects.requireNonNull(this.hotel);
		log.info("\t + this.hotel : {}", this.hotel);
		// + this.hotel : Hotel(chef=Chef()) ( 호텔 객체가 들어왔음을 알 수 있다. )
		// + 호텔 객체 빈 안에도 세프 객체 빈이 필드로 주입된 것을 알 수 있다.
		
	} // testDependencyInjection
	
	// ================================================

} // end class
