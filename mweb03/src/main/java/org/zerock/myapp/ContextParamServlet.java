package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

@WebServlet("/ContextParam")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcDriver;
	private String savePath;

	// ==========================================================
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		log.info("init(config) invoked.");
		
		// ==========================================================
		// init은 1번만 호출되기 때문에, 여기서 컨텍스트 파라미터가 읽어져야 한다. (***)
		// ==========================================================
		
		ServletContext sc = config.getServletContext();
		log.info("\t + sc : " + sc);
		
		this.jdbcDriver = sc.getInitParameter("jdbcDriver");
		this.savePath = sc.getInitParameter("savePath");
		
		// ==========================================================
		
//		sc.setAttribute(name, value); : Application scope에 공유데이터(객체)를 올려 놓는다.
//		sc.getAttribute(name);		  : Application scope의 공유데이터를 지운다.
//		sc.removeAttribute(name);	  : Application scope의 공유데이터를 삭제
		
		// Application scope 이란 공유데이터 영역에 2개의 문자열을 올려 놓는다. ( 이때 데이터를 객체로 올려 놓는다. )
		sc.setAttribute("jdbcDriver", jdbcDriver);
		sc.setAttribute("savePath", savePath);
		
		// ==========================================================
		
		super.init(config); // 이걸 작성해야 본 기능인 초기화도 할 수 있다. (***)
		
	} // init
	
	// ==========================================================

	@Override
	public void destroy() {
		
		log.info("destroy() invoked.");
		
	} // destroy
	
	// ==========================================================

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.info("service(res, req) invoked.");
		
		// 컨텍스트 파라미터 얻기도 용량이 크기 때문에 init에서 다루어져야 한다. (***)
		
		// ==========================================================
		
//		req.setCharacterEncoding("UTF-8");

		log.info(this.getServletContext());
		log.info(this.getServletContext().getInitParameter("jdbcDriver"));
		log.info(this.getServletContext().getInitParameter("savePath"));
		
		// ==========================================================
		
		res.setContentType("text/html; charset=utf8");
		
		// 응답문서 준비
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("1. 드라이버명 : " + jdbcDriver + "<br>");
		out.print("2. 저장경로 : " + savePath + "<br>");
		out.print("</body></html>");
		
		out.flush();

	} // service
	
	// ==========================================================

} // end class
