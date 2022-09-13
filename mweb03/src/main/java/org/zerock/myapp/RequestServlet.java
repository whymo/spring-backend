package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// MVC 패턴의 Controller의 역할을 한다.
@WebServlet("/Request")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 비지니스 로직 수행
		
		// 비지니스 데이터 ( == Model ) : request Scope에 공유 데이터 올리기
		req.setAttribute("name", "홍길동");
		req.setAttribute("address", "서울");
		
		// === 응답을 만들어낼 웹 컴포넌트( Servlet / JSP )에 요청을 위임 ( View ) ===
		
		// forward : 요청을 위임
		RequestDispatcher dis = req.getRequestDispatcher("/Response");
		dis.forward(req, res);
		
		log.info("Forward request to the /Response");
		// + /Response에 위임했을 지라도, URL은 /Request로 나온다. ( 응답을 처리하는 주체를 URL에서 알 수 없다. )
		
	} // service

} // end class