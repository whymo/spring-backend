package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.mapper.BoardMapper;
import org.zerock.myapp.mapper.UserMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlSessionTests5 { // 인터페이스 방식
	
	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	
	// ======================================================
	// 1. 사전처리로 마이바틱스 설정을 기반으로 SqlSessionFactory 객체 생성 @BeforeAll
	// ======================================================
	@BeforeAll
	void beforeAll() throws IOException {
		
		log.trace("beforeAll() invoked.");
		
		// 마이바틱스 설정 파일
		String myBatisConfigXml = "mybatis-config.xml";
		
		@Cleanup
		InputStream is = Resources.getResourceAsStream(myBatisConfigXml);
		Objects.requireNonNull(is);
		
		this.sqlSessionFactory = builder.build(is);
		
	} // beforeAll
	
	// ======================================================
	// 2. SQL을 실행할 메소드 생성 - selectAllBoardsByMapperInterface ( SELECT 문 )
	// ======================================================
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("selectAllBoardsByMapperInterface")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void selectAllBoardsByMapperInterface() {
		
		log.trace("1. selectAllBoardsByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		// Mapper Interface 방식을 이용한 SQL문장 처리
		try ( sqlSession; ){
			
			// =====================================================================
				// 1단계 : 설정파일에 등록된 Mapper Interface의 구현 객체를 획득
			// =====================================================================
			BoardMapper mapper = sqlSession.<BoardMapper>getMapper(BoardMapper.class);
			assertNotNull(mapper);
			
			log.info("\t + 1. mapper : {}", mapper); 
			//  + mapper : org.apache.ibatis.binding.MapperProxy@123456
			// Proxy는 대리인이라는 의미로, 이 경우 @Select()에 있는 sql문을 대신 실행시켜준다는 의미이다.
			
			// =====================================================================
				// 2단계 : 동적 Proxy 객체를 통해 인터 페이스에 선언된 메소드 호출
			// =====================================================================
			List<BoardVO> list = mapper.selectAllBoards();
			assertNotNull(list);
			
			list.forEach(log::info);	
			
		} // try - with - resources
		
	} // selectAllBoardsByMapperInterface 
	
	// ======================================================
		// 3. SQL을 실행할 메소드 생성 - selectBoardByMapperInterface ( SELECT 문 - #{} )
	// ======================================================
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("selectBoardByMapperInterface")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void selectBoardByMapperInterface() {
		
		log.trace("2. selectBoardByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		// Mapper Interface 방식을 이용한 SQL문장 처리
		try ( sqlSession; ){
			
			// =====================================================================
				// 1단계 : 설정파일에 등록된 Mapper Interface의 구현 객체를 획득
			// =====================================================================
			BoardMapper mapper = sqlSession.<BoardMapper>getMapper(BoardMapper.class);
			assertNotNull(mapper);
			
			log.info("\t + 2. mapper : {}", mapper); 
			//  + mapper : org.apache.ibatis.binding.MapperProxy@123456
			// Proxy는 대리인이라는 의미로, 이 경우 @Select()에 있는 sql문을 대신 실행시켜준다는 의미이다.
			
			// =====================================================================
				// 2단계 : 동적 Proxy 객체를 통해 인터 페이스에 선언된 메소드 호출
			// =====================================================================
			BoardVO vo = mapper.selectBoard(33);
			assertNotNull(vo);
			log.info("\t + 2. vo : {}", vo);
			
		} // try - with - resources
		
	} // selectBoardByMapperInterface
	
	// ======================================================
		// 4. SQL을 실행할 메소드 생성 - selectUsers ( SELECT 문 - 0개 이상의 #{} )
	// ======================================================
	
	// @Disabled
	@Test
	@Order(3)
	@DisplayName("selectUsers")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void selectUsers() {
		
		log.trace("3. selectUsers() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try ( sqlSession; ){
			
			UserMapper mapper = sqlSession.<UserMapper>getMapper(UserMapper.class);
			assert mapper != null;
			log.info("\t + mapper : {}", mapper);
			
			List<UserVO> list = mapper.selectUsers("USER_3", "NAME_4");
			Objects.requireNonNull(list);
			
			list.forEach(log::info);
			
		} // try - with - resources
		
	} // selectUsers
	
} // end class
