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

@WebServlet("/Lifecycle")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//================================================================
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		log.trace("init({}) inovked.", config);
		
	} // init : init은 매개변수가 있는 init과 매개변수가 없는 init이 있다.
	
	// + 하지만 init으로 초기화하기 위해서는 매개변수가 있는 init을 사용하는 것이 좋다.
	
	//================================================================

	@Override
	public void destroy() {
		log.trace("destory() invoked.");
	} // destroy : 쓰레드가 파괴되기 직전에 실행된다.
	
	//================================================================

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h1>LifecycleServlet</h1>");
		out.flush();
		
	} // service
	
	//================================================================

} // end class
