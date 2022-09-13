package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

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

@WebServlet("/ContextParamGet")
public class ContextParamGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// ========================================
		
		req.setCharacterEncoding("UTF-8");
		
		// 이전 서블릿이 Application Scope에 공유한 2개의 문자열을 얻어낸다.
		ServletContext sc = this.getServletContext();
		Objects.requireNonNull(sc);
		
		String jdbcDriver = (String) sc.getInitParameter("jdbcDriver");
		String savePath = (String) sc.getInitParameter("savePath");
		
		// ========================================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println(String.format("1. jdbcDriver : %s", jdbcDriver));
		log.info("\t + 1 ) jdbcDriver : {}", jdbcDriver);
		out.println(String.format("2. savePath : %s", savePath));
		log.info("\t + 2 ) savePath : {}", savePath);
		
		// 이와 같이 공유영역에 올려 놓은 문자열을 다른 Servlet에서 사용할 수도 있다. (***)
		
		out.flush();
		
	} // service

} // end class
