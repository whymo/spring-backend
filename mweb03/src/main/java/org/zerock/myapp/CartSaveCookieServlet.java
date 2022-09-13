package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartSaveCookie")
public class CartSaveCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 1. 화면에서 전송한 장바구니에 저장할 품목(전송 파라미터)을 획득
		String product = req.getParameter("product");
		
		// 2. 요청 메세지의 헤더에 포함되어있는 모든 쿠키를 획득
		Cookie [] cookies = req.getCookies();
		// Cookie 타입의 객체를 원소로 하고 있는 객체배열 반환
		
		// 3. 우리가 직접 새로운 쿠키를 생성
		Cookie c = null;
		
		// 4. 2단계에서 얻은 쿠키배열이 무효하다면 / 유효하다면
		if ( cookies == null || cookies.length == 0 ) {
			c = new Cookie("product", product);
		} else {
			c = new Cookie("product" + (cookies.length + 1), product);
		} // if - else
		
		// 5. 우리가 생성한 쿠키의 만료시간을 지정 ( 초 단위이다. )
		// 현재는 1시간 동안 유지하도록 하였다.
		c.setMaxAge(60 * 60);
		
		// 6. 응답메세지 헤더에 우리가 3에서 생성한 쿠키객체를 저장
		// 응답문서에 새로운 쿠키를 추가하여 응답을 보낸다.
		res.addCookie(c);
		
		// 7. 응답문서 준비
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><body>");
		
		out.println("product 추가하기");
		out.println("<a href='/CartBasketCookie'>장바구니 보기</a>");
		
		out.println("</body></html>");
		
		out.flush();
		
	} // service

} // end class
