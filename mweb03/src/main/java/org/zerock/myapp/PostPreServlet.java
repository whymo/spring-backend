package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

@WebServlet("/PostPre")
public class PostPreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ===========================================

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) inovked.", config);
	} // init
	
	// ===========================================

	@Override
	public void destroy() {
		log.trace("destory() invoked.");
	} // destroy
	
	// ===========================================
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
	} // postConstruct : init과 같은 기능
	
	@PreDestroy
	void preDesrtoy() {
		log.trace("preDesrtoy() invoked.");
	} // preDesrtoy : Destroy와 같은 기능
	
	// ===========================================

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		// ===========================================
		
		// 1. 응답 데이터의 유형을 설정 (***)
		res.setContentType("text/html; charset=utf8");
		
		// 2. 문자기반
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h1>PostpreServlet</h1>");
		out.flush();
		
		// ===========================================
		
	} // service
	
	// ===========================================
	// 실행 순서
	
	// 1. postConstruct() invoked.
	// 2. init() invoked.
	// 3. service(req, res) invoked.
	// 4. destroy() invoked.
	// 5. predesrtoy() invoked.
	// ===========================================

} // end class
