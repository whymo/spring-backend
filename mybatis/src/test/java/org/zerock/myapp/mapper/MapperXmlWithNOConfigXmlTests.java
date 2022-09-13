package org.zerock.myapp.mapper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
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
import org.zerock.myapp.domain.BoardVO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapperXmlWithNOConfigXmlTests {
	
	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	
//	=========================================================================================
	
	@BeforeAll
	void beforeAll() throws IOException {
		
		log.debug("beforeAll() invoked.");
		
		// ==============================================================
			// 1단계 : HikariCP의 설정 객체를 생성 및 풀에 대한 설정 수행
		// ==============================================================
		
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@db202204131245_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		
		hikariConfig.setUsername("SCOTT");
		hikariConfig.setPassword("Oracle12345678");
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setConnectionTimeout(2000); // 밀리세컨드 단위
		hikariConfig.setDataSourceJNDI("jdbc/HikariCP");
		
		// ==============================================================
			// 2단계 : HikariCP 데이터소스 객체 생성 with 설정 객체
		// ==============================================================
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		Objects.requireNonNull(hikariDataSource);
		log.info("\t + 1. hikariDataSource : {}", hikariDataSource);
		
		// ==============================================================
			// 3단계 : TX 관리자 생성을 위한 객체 생성
		// ==============================================================
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		log.info("\t + 2. transactionFactory : {}", transactionFactory);
		
		// ==============================================================
			// 4단계 : 마이바틱스 실행환경(Environment) 객체 생성 
		// ==============================================================
		// 매개변수 1 : 실행환경 식별자값 ( id )
		// 매개변수 2 : TX 공장
		// 매개변수 3 : 데이터 소스
		Environment env = new Environment("development", transactionFactory, hikariDataSource);
		log.info("\t + 3. env : {}", env);
		
		// ==============================================================
			// 5단계 : 자바객체 기반의 마이바티스 설정객체 생성
		// ==============================================================
		// 생성자 매개변수 : 위에서 생성한 실행환경 제공
		Configuration mybatisConfig = new Configuration(env);
		log.info("\t + 4. mybatisConfig : {}", mybatisConfig);
		
		// ==============================================================
			// 6단계 : Mapper 인터페이스 등록
		// ==============================================================
		mybatisConfig.addMapper(BoardMapper.class);
		mybatisConfig.addMapper(UserMapper.class);
		
		// ==============================================================
			// 7단계 : 위에서 생성한 설정객체 기반의 SqlSessionFactory 생성
		// ==============================================================
		this.sqlSessionFactory = builder.build(mybatisConfig);
		Objects.requireNonNull(mybatisConfig);
		
		log.info("\t+ 5. sqlSessionFactory: " + this.sqlSessionFactory);
		
	} // beforeAll()
	
//	=========================================================================================
	
	// 1. Select All Boards ( 전체 출력 )
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("selectAllBoards")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void selectAllBoards() {
		
		log.info("selectAllBoards() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + sqlSession : {}", sqlSession);
		
		try( sqlSession; ){
			
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t + mapper : {}", mapper);
			
			Objects.requireNonNull(mapper);
			
			List<BoardVO> boards = mapper.selectAllBoards();
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // selectAllBoards
	
//	=========================================================================================
	
	// 2. Select Board ( 하나만 출력 )
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("selectBoard")
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void selectBoard() {
		
		log.info("selectBoard() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + sqlSession : {}", sqlSession);
		
		try ( sqlSession; ) {
			
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t + mapper : {}", mapper);
			
			Objects.requireNonNull(mapper);
			
			BoardVO board = mapper.selectBoard(176);
			log.info("\t + board : {}", board);
			
		} // try - with - resources
		
	} // selectBoard
	
//	=========================================================================================
	
	@AfterAll
	void afterAll() {
		log.debug("afterAll() invoked.");
	} // afterAll

} // end class
