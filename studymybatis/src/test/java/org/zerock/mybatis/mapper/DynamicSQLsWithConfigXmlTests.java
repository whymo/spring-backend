package org.zerock.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.mybatis.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class DynamicSQLsWithConfigXmlTests {
		
	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	

	
	@BeforeAll
	void beforeAll() throws IOException {
		log.debug("beforeAll() invoked.");
		
		//-----------------------------------------------------//
		
		//--1st. method by using CLASSPATH.
		String mybatisConfigXml = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(mybatisConfigXml);
		
		//-----------------------------------------------------//
		
		//--2nd. method by using file path.
//		String mybatisConfigXml = "C:/temp/mybatis-config.xml";
//		File f = new File(mybatisConfigXml);
//		FileInputStream is = new FileInputStream(f);
		
		//-----------------------------------------------------//
		
		try (is) {
			this.sqlSessionFactory = builder.build(is);
			
			Objects.requireNonNull(this.sqlSessionFactory);
			log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
		} // try-with-resources
	} // setup
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("findBoardByBno")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void findBoardByBno() {
		log.debug("findBoardByBno() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			
			Integer bno = 172;
//			Integer bno = null;
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardByBno";
			
			BoardVO board = sqlSession.<BoardVO>selectOne(namespace+"."+sqlId, bno);
			log.info("\t+ board: " + board);
		} // try-with-resources
	} // findBoardByBno
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("findBoardsByTitle")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void findBoardsByTitle() {
		log.debug("findBoardsByTitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			
//			String title = null;
//			String title = "777";
			
			String title = "%777%";
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByTitle";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namespace+"."+sqlId, title);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // findBoardsByTitle
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("findBoardsByWriter")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void findBoardsByWriter() {
		log.debug("findBoardsByWriter() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
//			String writer = null;
//			String writer = "trinity";
			String writer = "%1000%";
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByWriter";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namespace+"."+sqlId, writer);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // findBoardsByWriter
	

//	@Disabled
	@Test
	@Order(4)
	@DisplayName("findBoardsByBnoAndTitle")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void findBoardsByBnoAndTitle() {
		log.debug("findBoardsByBnoAndTitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
//			Integer bno = null;
//			String title = null;
			
//			Integer bno = 172;
//			String title = null;
			
//			Integer bno = null;
//			String title = "%1000%";
			
			Integer bno = 313;
			String title = "%1000%";
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByBnoAndTitle";
			
			Map<String, Object> params = new HashMap<>();
			params.put("bno", bno);
			params.put("title", title);
			
			List<BoardVO> boards = sqlSession.selectList(namespace+"."+sqlId, params);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // findBoardsByBnoAndTitle
	

//	@Disabled
	@Test
	@Order(5)
	@DisplayName("findBoardsBySomeBnos")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	public void findBoardsBySomeBnos() {
		log.debug("findBoardsBySomeBnos() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
//			Integer[] bnoList = { 353, 354, 355, 356 };
//			log.info("\t+ bnoList: " + Arrays.toString(bnoList));
			
			List<Integer> bnoList = Arrays.asList(353, 354, 355, 356);
			log.info("\t+ bnoList: " + bnoList);
			
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsBySomeBnos";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namespace+"."+sqlId, bnoList);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // findBoardsBySomeBnos
	

//	@Disabled
	@Test
	@Order(6)
	@DisplayName("findBoardsByBnoOrTitle")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	public void findBoardsByBnoOrTitle() {
		log.debug("findBoardsByBnoOrTitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			
			Map<String, Object> params = new HashMap<>();
			
//			params.put("bno", 372);
//			params.put("title", "%1000%");
	
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardsByBnoOrTitle";
			
			List<BoardVO> boards = sqlSession.selectList(namespace+"."+sqlId, params);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // findBoardsByBnoOrTitle
	
	
	@AfterAll
	void afterAll() {
		log.debug("afterAll() invoked.");
		
	} // afterAll

} // end class
