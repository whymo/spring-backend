package org.zerock.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.exception.BizProcessException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// 잘못된 요청(command)에 대한 처리를 수행하는 서비스 객체
public class UnknownReqService implements Service {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws BizProcessException {

		log.trace("execute(req, res) invoked.");
		
		// ServletContext sc = req.getServletContext();	// Application scoe 객체
		// sc.setAttribute(Service.BIZ_RESULT, "Bad Request"); // 인터페이스에서 정적객체 가지고 오기
		// 위는 Application Scope에 올려 놓은 것이다.
		
		req.setAttribute(Service.BIZ_RESULT, "Bad Request"); // Request Scope에 올려 놓았다.
		
	} // execute

} // UnknownReqService
