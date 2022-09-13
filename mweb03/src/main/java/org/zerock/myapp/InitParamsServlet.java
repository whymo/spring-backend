package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
//=========================================================
@WebServlet(
		urlPatterns = { "/InitParams" }, 
		initParams = { 
				@WebInitParam(name = "userid", value = "USER_ID_VALUE"), 
				@WebInitParam(name = "dirPath", value = "DIR_PATH_VALUE")
		})
//=========================================================
public class InitParamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//=========================================================
	
	// 롬복을 활용한 초기화 파라미터 설정

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
		
		// + 롬복을 사용해도 service에서 초기화 파라미터 값을 가지고 오는 것은 부담이 되기에, 불가능하다.
		
	} // service

} // end class
