package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)

// @TestMethodOrder로 테스트 메소드 실행순서를 어떻게 할 것인지 정하는데,
// 주로 OrderAnnotation을 지정하여 @Order로 순서를 지정하게 한다.
@TestMethodOrder(OrderAnnotation.class)
public class SqlSessionFactoryTests {
	
	// sqlSessionFactory를 건설하는 건설사
	private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	
	// ==============================================
	
	// @BeforeAll는 사전처리로 단 1번만 수행된다.
	@BeforeAll
	void beforeAll() throws IOException {
		
		log.trace("beforeAll() invoked.");
		
		// mybatis의 설정파일 경로
		String config = "mybatis-config.xml";
		
		@Cleanup
		InputStream is = Resources.getResourceAsStream(config);
		
		// sqlSessionFactory 생성
		this.sqlSessionFactory = this.sqlSessionFactoryBuilder.build(is);
		assertNotNull(this.sqlSessionFactory);
		log.info("\t + this.sqlSessionFactory : {}", this.sqlSessionFactory );
		
		// sqlSessionFactoryBuilder 생성
		// this.sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		// Objects.requireNonNull(this.sqlSessionFactoryBuilder);
		// log.info("\t + this.sqlSessionFactoryBuilder : {}", this.sqlSessionFactoryBuilder);
		
	} // beforeAll
	
	// ==============================================
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("1. getSqlSession")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	void getSqlSession() throws Exception {
		
		log.trace("getSqlSession() invoked.");
		
		// ==============================================
		
		// sqlSessionFactory 객체로부터 SqlSession을 얻어내자!!
		// sqlSession 객체를 다 사용하고 나면, 반드시 자원해제를 해줘야 한다.
		
		// @Cleanup // 자원해제
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		Objects.requireNonNull(sqlSession);
		log.info("\t + sqlSession : {}", sqlSession);
		
		// SQL 문장 처리를 프레임워크에 의뢰
		try ( sqlSession ){
			;;
		} // try - with - resources
		
		// ==============================================
		
		// SQL 연결을 얻어낸다.
		// Connection conn = sqlSession.getConnection();
		// assert conn != null;
		// log.info("\t + conn : {}", conn);
		
		// ==============================================
		
	} // contextLoads
	
} // end class
