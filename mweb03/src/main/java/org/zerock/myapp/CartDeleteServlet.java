package org.zerock.myapp;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartDelete")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 장바구니 비우기 : 현재 브라우저의 세션 아이디(이름)로 식별되는 Session Scope 공유영역 파괴
		
		HttpSession sess = req.getSession(false);
		// false로 준 이유는, 세션이 없다면 장바구니가 없다는 의미이기에 수행될 필요가 없기 때문이다.
		
		try {
			
			Objects.requireNonNull(sess);
			// 세션이 유효하다면,
			
			sess.invalidate();
			// Session Id 무효화 + Session Scope 영역 파괴
			
			// ===========================================
			
			// 마지막 응답 페이지는 요청 포워딩을 통해 ( Request Forwarding)
			// JSP에서 생성
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/cartdelete.jsp");
			dispatcher.forward(req, res);
			
		} catch(Exception e) {
			throw new ServletException(e);
		} // try - catch
		
	}// service

} // end class
