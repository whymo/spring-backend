package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
@WebServlet(name = "Hello2", urlPatterns = { "/hello2" })
public class Hello2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		HttpSession sess = req.getSession();
		log.info("JSE Session ID : {}",sess.getId());
		// 세션 아이디 확인하는 방법
		
		PrintWriter out =  res.getWriter();
		out.println("<h1>World!!!</h1>");
		// WAS는 이처럼 동적으로 태그를 넣어서 문서를 만들 수 있다.
		
		out.flush();
		out.close();
		
	} // service
	
	// + service는 GET과 POST방식 모두 요청을 받을 수 있다.(***)

} // end class
