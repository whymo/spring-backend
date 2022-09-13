package org.zerock.myapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlSessionTests4 { // insert
	
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
	// 2. SQL을 실행할 메소드 생성 - insertNewUser ( INSERT문 )
	// ======================================================
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("insertNewUser")
	@Timeout(value=10, unit=TimeUnit.SECONDS)
	void insertNewUser() {
		
		log.trace("insertNewUser() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession(false);
		// false로 지정하면 오토 커밋을 false로 지정해 준다.
		
		try ( sqlSession; ){
			
			String namespace = "mappers.UserMapper";
			String sqlId = "insertNewUser";
			String sql = namespace + "." + sqlId; // (***)
			
			// 바인드 변수에 전달할 파라미터 값을 생성하면서,
			// 한번에 여러개의 레코드를 INSERT 한다. ( TX는 수동관리 )
			for( int i = 2; i <= 10; i++ ) {
				
				Map<String, Object> params = new HashMap<>();
				
				params.put("userid", "USER_" + i);
				params.put("userpw", "PASS_" + i);
				params.put("username", "NAME_"+ i);
				
				// Map 객체를 파라미터로 전달하면서 Mapped Statement 수행 ( DML )
				int insertRows = sqlSession.insert(sql, params);
				log.info("\t + insertRows : {}", insertRows);
				
			} // for
			
			sqlSession.commit(); // TCL : ALL or Nothing
			log.info("\t + commit");
			
		} catch (Exception e) {
			sqlSession.rollback(); // TCL : ALL or Nothing
			log.info("\t + Rolled Back");
			
			throw e;
		} // try - catch
		
	} // insertNewUser
	
} // end class
