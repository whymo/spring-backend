package org.zerock.myapp.persistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
import org.zerock.myapp.domain.EmployeeVO;
import org.zerock.myapp.mapper.TimeMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//======================================================
// JUNIT5 - 테스트 메소드 수행시 Spring FrameWork를 구동시키는 어노테이션
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
//file: Spring 프로젝트 폴더를 의미한다. ( chap04를 의미 )
//file:에는 공백이 허용되지 않다.
//spring 폴더 -> root-context가 선택된다.
//와일드 카드도 사용이 가능하다.
//======================================================

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlSessionFactoryTests {
	
//	@Test만 필수이지, 그 외의 것들은 선택이다.
//	@BeforeAll
//	@BeforeEach
//	@Test
//	@AfterAll
//	@AfterEach
	
	// ======================================================
		// 1. 빈 컨테이너에 SqlSessionFactoryBean 요청
	// ======================================================
	
	// @Autowired
	// onMethod_에 1개 이상의 어노테이션을 지정할 수 있다.
	@Setter(onMethod_= { @Autowired }) // ( 추천 )
	private SqlSessionFactory sqlSessionFactory;
	
	// ======================================================
		// 2. sqlSessionFactoryBean 확인
	// ======================================================
	
	@BeforeAll
	void beforeAll() {
		
		log.trace("beforeAll() invoked.");
		
		// SqlSessionFactoryBean이 들어왔는지 확인
		// + 들어왔다면 null일리가 없다.
		Objects.requireNonNull(this.sqlSessionFactory);
		log.info("\t + this.sqlSessionFactory : {}", this.sqlSessionFactory);
		// + this.sqlSessionFactory : org.apache.ibatis.session.defaults.DefaultSqlSessionFactory@123456
		
	} // beforeAll
	
	// ======================================================
		// 3. Mapper 수행
	// ======================================================
	
	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. testSQLMapper1")
	@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
	void testSQLMapper1() {
		log.trace("\t + testSQLMapper1() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try ( sqlSession; ){
			
			// mappedStatement = namespace명 + sql 아이디
			String mappedStatement = "sql1mapper.DQL1";
			
			// 100은 #{employee_id} 값을 지정해 주는 것이다.
			// 1. SELECT * FROM employees WHERE employee_id > 100 sql문이 실행
			List <EmployeeVO> list = sqlSession.<EmployeeVO>selectList(mappedStatement, 100);
			list.forEach(log::info);
			
		} // try - with - resources
		
	} // testSQLMapper1
	
	// ======================================================
		// 4. Mapper2 수행
	// ======================================================
	
	@Disabled
	@Test
	@Order(1)
	@DisplayName("2. testSQLMapper2")
	@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
	void testSQLMapper2() {
		log.trace("\t + testSQLMapper2() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try ( sqlSession; ){
			
			// mappedStatement = namespace명 + sql 아이디
			String mappedStatement = "sql2mapper.DQL2";
			
			// 넘겨줄 map 객체 생성
			Map<String, Object> map = new HashMap<>();
			map.put("email", "A%");
			map.put("salary", 3000);
			
			// 바인딩 변수에 map 객체를 넘겨주면 getter를 통해 값을 받아간다.
			List <EmployeeVO> list = sqlSession.<EmployeeVO>selectList(mappedStatement, map);
			list.forEach(log::info);
			
		} // try - with - resources
		
	} // testSQLMapper2
	
	// ======================================================
		// 5. Interface Mapper 수행 (****)
	// ======================================================
	
	@Test
	@Order(1)
	@DisplayName("3. testgetCurrentTime1")
	@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
	void testgetCurrentTime1() {
		log.trace("\t + testgetCurrentTime1() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try ( sqlSession; ){
			
			TimeMapper mapper = sqlSession.getMapper(TimeMapper.class);
			Objects.requireNonNull(mapper);
			log.info("\t + mapper : {}", mapper);
			// + mapper : org.apache.ibatis.binding.MapperProxy@1658b239
			
			String now = mapper.getCurrentTime1();
			log.info("\t + now : {}", now);
			
		} // try - with - resources
		
	} // testgetCurrentTime1

} // end class
