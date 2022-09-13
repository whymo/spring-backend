package org.zerock.mybatis.mapper;

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
import org.zerock.mybatis.domain.BoardVO;
import org.zerock.mybatis.domain.MemberVO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapperInterfacesWithNoConfigXmlTests {

	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@BeforeAll()
	void beforeAll() {
		log.debug("beforeAll() invoked.");
		
		//-----------------------------------------------------//
		
		HikariConfig hikariConfig = new HikariConfig();
		
//		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		
//		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@atp20191201_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP");
		
		hikariConfig.setUsername("ADMIN");
		hikariConfig.setPassword("Oracle12345678");
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setConnectionTimeout(1000);
		hikariConfig.setDataSourceJNDI("jdbc/HikariCP");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		Objects.requireNonNull(hikariDataSource);
		log.info("\t+ dataSource: " + hikariDataSource);
		
		//-----------------------------------------------------//
		
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		log.info("\t+ transactionFactory: " + transactionFactory);
		
		//-----------------------------------------------------//
		
		Environment env = new Environment("development", transactionFactory, hikariDataSource);
		log.info("\t+ env: " + env);
		
		//-----------------------------------------------------//
		
		Configuration mybatisConfig = new Configuration(env);
		log.info("\t+ mybatisConfig: " + mybatisConfig);
		
		mybatisConfig.addMapper(BoardMapper.class);
		mybatisConfig.addMapper(MemberMapper.class);
		
		//-----------------------------------------------------//
		
		this.sqlSessionFactory = builder.build(mybatisConfig);
		Objects.requireNonNull(this.sqlSessionFactory);
		
		log.info("\t+ sqlSessionFactory: " + this.sqlSessionFactory);
	} // setup
	

//	@Disabled
	@Test
	@Order(1)
	@DisplayName("selectAllBoards")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void selectAllBoards() {
		log.debug("selectAllBoards() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try (sqlSession) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			List<BoardVO> boards = mapper.selectAllBoards();
			boards.forEach(log::info);
		} // try-with-resources
	} // selectAllBoards
	

//	@Disabled
	@Test
	@Order(2)
	@DisplayName("selectBoard")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void selectBoard() {
		log.debug("selectBoard() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			BoardVO board = mapper.selectBoard(176);
			log.info("\t+ board: " + board);
		} // try-with-resources
	} // selectBoard
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("selectAllMembers")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void selectAllMembers() {
		log.debug("selectAllMembers() invoked.");
		
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
	} // selectAllMembers
	

//	@Disabled
	@Test
	@Order(4)
	@DisplayName("selectMember")
	@Timeout(value=2000, unit=TimeUnit.MILLISECONDS)
	public void selectMember() {
		log.debug("selectMember() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t+ sqlSession: " + sqlSession);
		
		try(sqlSession) {
			MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
			log.info("\t+ mapper: " + mapper);
			
			Objects.requireNonNull(mapper);
			
			MemberVO member = mapper.selectMember("user9");
			log.info("\t+ member: " + member);
		} // try-with-resources
	} // selectMember
	
	
	@AfterAll
	public void afterAll() {
		log.debug("afterAll() invoked.");
		
	} // tearDown

} // end class
