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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.BoardVO;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlSessionTests2 {
	
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
	// 2. SQL을 실행할 메소드 생성 - selectAllBoards ( SELECT문 )
	// ======================================================
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("2. selectAllBoards")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void selectAllBoards() {
		
		log.trace("selectAllBoards() invoked.");
		
		// SqlSessionFactory 객체에서 SqlSession 얻기
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		// ======================================================
		// 2 - 1. SQL 문장 처리를 프레임워크에 의뢰
		// ======================================================
		try ( sqlSession; ) {
			
			// ====================================
			// 아래의 2가지가 많이 사용된다.
			
			// 1. 반환되는 결과가 1개일때
			// sqlSession.selectOne(sql);
			
			// 2. 반환되는 결과가 0개 이상일때
			// sqlSession.selectList(sql);
			// ====================================
			
			// ==================================================================
			// String sql = "SELECT * FROM tbl_board"; <--- Mapper.xml로 이동
			// 마이바틱스에서 실행시킬 sql문장은 Mapper.xml에서 보관한다.
			// ==================================================================
			// 마이바틱스가 요구하는 규칙 ( 수행시킬 SQL문장을 지정하는 방식 ) :
			// 1) 각 SQL Mapper XML 파일 안의 namespace 속성마다 고유한 값을 가져야 한다.
			// 2) 각 SQL Mapper XML 파일 안에 저장된 각 SQL 태그마다 고유한 id값을 가져야 한다.
			// + 주로 id값은 일반적으로 SQL을 수행할 메소드의 이름과 동일하게 작성한다.
			// + 하지만 필수요구사항은 아니기에 개발자가 원하는대로 id값을 작성해도 된다. ( 단, 고유해야 한다. )
			// ==================================================================
			
			// 이 규격을 지켜야 한다.
			String namespace ="BoardMapper";
			String sqlId = "selectAllBoards";
			String sql = namespace + "." + sqlId;	// Unique Identifier
			
			List<BoardVO> list = sqlSession.selectList(sql);
			// List<BoardVO> list = sqlSession.<BoardVO>selectList(sql);
			assertNotNull(list);
			
			// 마이바틱스에서 실행시킬 sql문장은 Mapper.xml에서 보관한다.
			list.forEach(log::info);
			
		} // try - with - resources
		
	} // selectAllBoards
	
	// ==================================================================
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("selectBoard")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void selectBoard() {
		
		log.trace("selectBoard() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try ( sqlSession; ){
			
			// String namespace = "BoardMapper";
			// String sqlId = "selectBoard";
			// String sql = namespace + sqlId;
			String sql = "BoardMapper.selectBoard"; // (***)
			
			// Mapper에서 작성한 #{bno}의 값을 지정해 준다.
			// #{}는 ?와 같은 기능을 한다.
			Integer bno = 7;
			
			// 하나만 반환할 때에는 selectOne을 사용한다.
			BoardVO vo = sqlSession.selectOne(sql, bno);
			// BoardVO vo = sqlSession.<BoardVO>selectOne(sql);
			assert vo != null;
			
			log.info("\t + vo : {}",vo);
			
		} // try - with - resources
		
	} //selectBoard

} // end class
