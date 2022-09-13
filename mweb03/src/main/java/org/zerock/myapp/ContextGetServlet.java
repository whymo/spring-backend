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

@WebServlet("/ContextGet")
public class ContextGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// =============================================
		
		ServletContext sc = this.getServletContext();
		ServletConfig config = this.getServletConfig();
		
		log.info("\t + sc : {},     config : {}", sc, config);
		
		// =============================================
		
		// ServletClass의 FQCN이 나온다. ( 클래스의 이름이 출력 -> 사용X ) 
		String servletName = this.getServletName();
		String servletInfo = this.getServletInfo();
		
		// Application Scope에 공유된 2개의 속성값을 획득 (*****)
		String name = (String) sc.getAttribute("name");
		int age = (int) sc.getAttribute("age");
		
		// =============================================
		
		// Application Scope에 공유된 2개의 속성값을 삭제 - 속성 언바인딩 (*****)
		sc.removeAttribute("name");
		sc.removeAttribute("age");
		
		// 삭제하지 않고 WAS를 내리게 되면, 메모리 누수가 일어날 수 있다. (*)
		
		// =============================================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h3> servletName : " + servletName + "</h3>");
		out.println("<h3> servletInfo : " + servletInfo + "</h3>");
		
		out.println("<h3> name : " + name + "</h3>");
		out.println("<h3> age : " + age + "</h3>");
		
		out.flush();
		
	} // service

} // end class
