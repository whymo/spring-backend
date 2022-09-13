package org.zerock.myapp.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/sample3/") // 기본 URI ( base URI )
@Controller 
public class SampleController3 { // 예외처리 ver
	
	// =======================================================
		// 1. IllegalStateException
	// =======================================================
	
	@RequestMapping("")
	// + 이 경우에는 http://localhost:8080/sample3/로 요청을 보내야 한다.
	public String basic() {
		log.trace("basic() invoked.");
		
		throw new IllegalStateException("TEST");
		// + 예외를 던짐으로써 예외가 발생하도록 만들고 있다.
		// + 이 프로젝트의 경우, web.xml 파일에서 예외를 다루고 있기에
		// + 만약 Spring MVC에서 예외를 다루지 않을 경우, web.xml 파일에 등록한대로 진행된다.
		
	} // basic
	
	// =======================================================
		// 2. SQLException + throws
	// =======================================================
	
	@RequestMapping("sql")
	public String basicSql() throws SQLException {
		log.trace("basicSql() invoked.");
		
		throw new SQLException("TEST");
		
	} // basicSql
   

} // end class
