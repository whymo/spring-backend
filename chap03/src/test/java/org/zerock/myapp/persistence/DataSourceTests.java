package org.zerock.myapp.persistence;

import javax.sql.DataSource;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class DataSourceTests {
	
	// 테스트하는데 필요한 빈을 주입해 주세요! 라고 시그널을 Beans Container에 전송함.
	// 그런데, 우리 테스트에 필요한 빈이 뭔가요!? => `HikariDataSource` Bean이 필요함!!!
	@Autowired
	private DataSource dataSource;
	

} // end class
