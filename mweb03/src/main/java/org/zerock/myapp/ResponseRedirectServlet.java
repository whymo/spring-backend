package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
@WebServlet("/ResponseRedirect")
public class ResponseRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 1. 공유데이터 영역( Request Scope )에서 Model 데이터 얻어내기
		// String name = (String) req.getAttribute("name");
		// String address = (String) req.getAttribute("address");
		
		// 1 - 2. 공유데이터 영역( Session Scope )에서 Model 데이터 얻어내기
		// HttpSession sess = req.getSession();
		// String name = (String) sess.getAttribute("name");
		// String address = (String) sess.getAttribute("address");
		
		// 1 - 3. 전송파라미터로 얻기
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		log.info("Model - name : {}, address : {}", name, address);
		
		// ======================================================
		
		// 2. Model을 이용한 응답문서의 생성 및 전송
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
		out.println("<h1> /ResponseRedirect </h1>");
		out.println("<hr>");
		
		out.println("<h2> 1. name 값 : " + name + "</h2>");
		out.println("<h2> 2. address 값 : " + address + "</h2>");
		
		out.println("</body></html>");
		out.flush();
		
		// ======================================================
		
		// Redirect는 name과 address가 모두 null로 나오는데,
		// 그 이유는 redirect는 재요청을 응답으로 보내버리기 때문에
		// Request Scope에 올려 놓았던 name과 address가 없어져 버린다.
		// 그렇기에 출력하기 위해서는 다른 방법을 사용해야 한다.
		
		// ======================================================
				
	} // service

} // end class
