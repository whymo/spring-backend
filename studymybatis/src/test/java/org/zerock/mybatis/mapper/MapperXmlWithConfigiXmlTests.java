package org.zerock.mybatis.mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
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
import org.zerock.mybatis.domain.MemberVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class MapperXmlWithConfigiXmlTests {
		
	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	


	@BeforeAll
	void beforeAll() throws IOException {
		log.debug("beforeAll() invoked.");
		
		//-----------------------------------------------------//
		
		//--1st. method by using CLASSPATH.
//		String mybatisConfigXml = "mybatis-config.xml";
//		log.info("\t+ url: " + Resources.getResourceURL(mybatisConfigXml));
		
//		InputStream is = Resources.getResourceAsStream(mybatisConfigXml);
//		InputStream is = Resources.getUrlAsStream( Resources.getResourceURL(mybatisConfigXml).getPath() );
		
		//-----------------------------------------------------//
		
		//--2nd. method by using file path.
		String mybatisConfigXml = "mybatis-config.xml";
		log.info("\t+ url: " + Resources.getResourceURL(mybatisConfigXml));
		
		File f = new File( Resources.getResourceURL(mybatisConfigXml).getPath() );
		FileInputStream is = new FileInputStream(f);
		
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
	@DisplayName("selectAllBoards")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectAllBoards() {
		log.debug("selectAllBoards() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "mappers.BoardMapper";
			String sqlId = "selectAllBoards";
			
			List<BoardVO> boards = sqlSession.selectList(namespace+"."+sqlId);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // selectAllBoards
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("selectAllBoardsByMapperInterface")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectAllBoardsByMapperInterface() {
		log.debug("selectAllBoardsByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			List<BoardVO> boards = mapper.selectAllBoards();
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
		} // try-with-resources
	} // selectAllBoardsByMapperInterface
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("selectBoard")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectBoard() {
		log.debug("selectBoard() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "mappers.BoardMapper";
			String sqlId = "selectBoard";
			
			BoardVO board = sqlSession.selectOne(namespace+"."+sqlId, 191);
			log.info("\t+ board: " + board);
		} // try-with-resources
	} // selectBoard
	

//	@Disabled
	@Test
	@Order(4)
	@DisplayName("selectBoardByMapperInterface")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectBoardByMapperInterface() {
		log.debug("selectBoardByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			 BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			 log.info("\t+ mapper: " + mapper);
			 
			 Objects.requireNonNull(mapper);
			 
			 BoardVO board = mapper.selectBoard(100);
			 log.info("\t+ board: " + board);
		} // try-with-resources
	} // selectBoardByMapperInterface
	

//	@Disabled
	@Test
	@Order(5)
	@DisplayName("selectAllMembers")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectAllMembers() {
		log.debug("selectAllMembers() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "MemberMapper";
			String sqlId = "selectAllMembers";
			
			List<MemberVO> members = sqlSession.selectList(namespace+"."+sqlId);
			
			Objects.requireNonNull(members);
			members.forEach(log::info);
		} // try-with-resources
	} // selectAllMembers
	

//	@Disabled
	@Test
	@Order(6)
	@DisplayName("selectAllMembersByMapperInterface")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectAllMembersByMapperInterface() {
		log.debug("selectAllMembersByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			List<MemberVO> members = mapper.selectAllMembers();
			Objects.requireNonNull(members);
			
			members.forEach(log::info);
		} // try-with-resources
	} // selectAllMembersByMapperInterface
	

//	@Disabled
	@Test
	@Order(7)
	@DisplayName("selectMember")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectMember() {
		log.debug("selectMember() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			String namespace = "MemberMapper";
			String sqlId = "selectMember";
			
			MemberVO member = sqlSession.selectOne(namespace+"."+sqlId, "user7");
			log.info("\t+ member: " + member);
		} // try-with-resources
	} // selectMember
	

//	@Disabled
	@Test
	@Order(8)
	@DisplayName("selectMemberByMapperInterface")
	@Timeout(value=3000, unit=TimeUnit.MILLISECONDS)
	public void selectMemberByMapperInterface() {
		log.debug("selectMemberByMapperInterface() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			MemberVO member = mapper.selectMember("user3");
			log.info("\t+ member: " + member);
		} // try-with-resources
	} // selectMemberByMapperInterface
	

	@AfterAll
	void afterAll() {
		log.debug("afterAll() invoked.");
		
	} // afterAll

} // end class
