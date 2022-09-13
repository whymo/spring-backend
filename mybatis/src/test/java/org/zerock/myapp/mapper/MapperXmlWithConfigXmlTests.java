package org.zerock.myapp.mapper;

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

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapperXmlWithConfigXmlTests {
	
	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	
//	=========================================================================================
	
	@BeforeAll
	void beforeAll() throws IOException {
		
		log.debug("beforeAll() invoked.");
		
		// =================================================
		// 1st. method : CLASSPATH 사용!
		// String mybatisConfigXml = "mybatis-config.xml";
		// log.info("\t + url : " + Resources.getResourceURL(mybatisConfigXml));
		
		// InputStream is = Resources.getResourceAsStream(mybatisConfigXml);
		// InputStream is = Resources.getUrlAsStream(Resources.getResourceURL(mybatisConfigXml).getPath() );
		// =================================================
		// 2nd. method : file path 사용!
		String mybatisConfigXml = "mybatis-config.xml";
		log.info("\t+ url: " + Resources.getResourceURL(mybatisConfigXml));
		
		File f = new File( Resources.getResourceURL(mybatisConfigXml).getPath() );
		FileInputStream is = new FileInputStream(f);
		// =================================================
		
		try( is; ){
			this.sqlSessionFactory = builder.build(is);
			
			Objects.requireNonNull(this.sqlSessionFactory);
			log.info( "\t + sqlSessionFactory : {}", this.sqlSessionFactory );
		} // try - with - resources
		
	} // beforeAll()
	
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("selectAllBoards")
	@Timeout(value=5000, unit=TimeUnit.MILLISECONDS)
	public void selectAllBoards() {
		
		log.info("selectAllBoards() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + sqlSession : {}", sqlSession);
		
		try ( sqlSession; ){
			
			String namespace = "BoardMapper";
			String sqlId = "selectAllBoards";
			
			List <BoardVO> boards = sqlSession.selectList(namespace+"."+sqlId);
			
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // selectAllBoards
	
//	=========================================================================================
	

} // end class
