package org.zerock.myapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlSessionTests3 {
	
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
		// #{}가 1개일때
	// ======================================================
	
	// @Disabled
	@Test
	@Order(1)
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
	
	// ==================================================================
		// #{}가 1개 이상일때
	// ==================================================================
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("selectBoardsByTwoCondition")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void selectBoardsByTwoCondition() {
		
		log.trace("selectBoardsByTwoCondition() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try ( sqlSession; ){
			
			String sql = "BoardMapper.selectBoardsByTwoCondition"; // (***)
			
			// ======================================================
			// Mapper에서 작성한 #{bno}와 #{title}의 값을 지정해 준다.
			// #{}는 ?와 같은 기능을 한다.
			Map<String, Object> params = new HashMap<>();
			
			params.put("bno", 5 );
			params.put("title", "TITLE_2");
			// ======================================================
			
			List<BoardVO> volist = sqlSession.<BoardVO>selectList(sql, params);
			assert volist != null;
			
			volist.forEach(log::info);
			
		} // try - with - resources
		
	} // selectBoardsByTwoCondition
		
	// ==================================================================

} // end class
