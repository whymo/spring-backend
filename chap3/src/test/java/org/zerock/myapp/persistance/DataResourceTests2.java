package org.zerock.myapp.persistance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
	
	// 테스트하는데 필요한 빈을 주입해 주세요! 라고 시그널을 Beans Container에 전송함.
	// 그런데, 우리 테스트에 필요한 빈이 뭔가요!? => `HikariDataSource` Bean이 필요함!!!
	@Autowired
	private DataSource datasource;
	
	
	// 테스트하는데 필요한 빈을 주입해 주세요!
	// 어느 빈 ? => HikariConfig빈이요!
	// 루트 컨텍스트에 등록한 HikariConfig빈을 얻은다.
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
		// + 2. JdbcUrl : jdbc:log4jdbc:oracle:thin:@db202204131245_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP
		
		log.info("\t + 3. Username : {}", this.config.getUsername() );
		// + 3. Username : HR
		
		log.info("\t + 4. IdleTimeout : {}", this.config.getIdleTimeout());
		// + 4. IdleTimeout : 10000
		
	} // testHikariConfig
	
	// =================================================================

} // end class
