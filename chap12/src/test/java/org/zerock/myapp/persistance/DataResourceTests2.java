package org.zerock.myapp.persistance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariConfig;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataResourceTests2 {
	
	// @Autowired
	// private DataSource datasource;
	// + 테스트하는데 필요한 빈을 주입해 주세요! 라고 시그널을 Beans Container에 전송함.
	// + this.datasource : org.apache.ibatis.datasource.pooled.PooledDataSource@3e53c4ad
	// + datasource로 들어올 수 있는 것이 히카리와 PooledDataSource로 2개가 있는데,
	// + 현재 root-context.xml파일에서 PooledDataSource가 primary true이고, 히카리가 flase여서 생긴 문제이다.
	
	@Resource(type=com.zaxxer.hikari.HikariDataSource.class)
	private DataSource datasource;
	// + 위와 같은 문제가 있을 때에는 @Reaource로 DataSource를 지정해서 문제를 해결할 수 있다.
	
	@Setter(onMethod_= {@Autowired})
	private HikariConfig config;
	
	// =================================================================
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invokd.");
		
		Objects.nonNull(this.datasource);
		log.trace("\t + this.datasource : {}", this.datasource);
		
		assertNotNull(this.config);
		log.trace("\t + this.config : {}", this.config);
		
	} // beforeAll
	
	// =================================================================
	
	@Test
	@Order(1)
	@DisplayName("testHikariCP1")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testHikariCP1() throws SQLException {
		
		log.trace("testHikariCP1() invoked.");
		
		Connection conn = datasource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
		
		try ( conn; stmt; rs; ){
			
			log.info("\t + conn : {}", conn);
			log.info("\t + stmt : {}", stmt);
			log.info("\t + rs : {}", rs);
			
			while( rs.next() ) {
				
				String employee_id = rs.getString("EMPLOYEE_ID");
				String first_name = rs.getString("FIRST_NAME");
				String last_name = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				String phone_number = rs.getString("PHONE_NUMBER");
				String hire_date = rs.getString("HIRE_DATE");
				String job_id = rs.getString("JOB_ID");
				String salary = rs.getString("SALARY");
				String commission_pct = rs.getString("COMMISSION_PCT");
				String department_id = rs.getString("DEPARTMENT_ID");
				
				String employee = String.format(
						"%S, %S, %S, %S, %S, %S, %S, %S, %S, %S",
						employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, department_id);
				
				log.info(employee);
						
			} // while
			
		} // try - with - resources
		
	} // testHikariCP1
	
	// =================================================================
	
	@Test
	@Order(2)
	@DisplayName("testHikariConfig")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testHikariConfig() throws SQLException {
		
		log.trace("testHikariConfig() invoked.");
		
		// =================================================
		// 루트 컨텍스트에 등록한 HikariConfig의 속성을 활용
		// =================================================
		
		log.info("\t + 1. DriverClass : {}", this.config.getDriverClassName());
		// + 1. DriverClass : net.sf.log4jdbc.sql.jdbcapi.DriverSpy
		
		log.info("\t + 2. JdbcUrl : {}", this.config.getJdbcUrl() );
		// + 2. JdbcUrl : jdbc:log4jdbc:oracle:thin:@db000000_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP
		
		log.info("\t + 3. Username : {}", this.config.getUsername() );
		// + 3. Username : HR
		
		log.info("\t + 4. IdleTimeout : {}", this.config.getIdleTimeout());
		// + 4. IdleTimeout : 10000
		
	} // testHikariConfig
	
	// =================================================================

} // end class
