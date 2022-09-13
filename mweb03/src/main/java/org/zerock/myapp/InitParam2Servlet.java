package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebServlet("/InitParam2")
public class InitParam2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//=========================================================
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init(config) invoked.");
		
		// 수동으로 web.xml파일에 작성한 초기화 파라미터 값 가지고 오기
		String dirPath = config.getInitParameter("dirPath");
		String userid = config.getInitParameter("userid");
		
		log.info("1. dirPath : {}, userid : {}", dirPath, userid);
		
	} // init
	
	//=========================================================

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		req.setCharacterEncoding("UTF-8");
		
		String dirPath = this.getInitParameter("dirPath"); // xx
		String userid = this.getInitParameter("userid"); // xx
		
		log.info("\t + 2. dirPath : {}, userid : {}", dirPath, userid); // xx
		
		// + service에서는 초기화 파라미터 값을 얻어낼 수 없다. ( 위의 코드는 작성하지 말아야 한다. )
		// + 초기화 파라미터의 값이 궁금할 경우에는 Init에서 해야 한다. (***)
		
	} // service
	
	//=========================================================

} // end class
