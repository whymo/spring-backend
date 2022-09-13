package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

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

@WebServlet("/CartBasketCookie")
public class CartBasketCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 1. 나의 모든 장바구니 항목을 담고 있는 쿠키 목록을 웹 브라우저가
		// request message의 헤더에 담아서 보내게 되어 있다. ( 웹 브라우저의 기본동작 )
		Cookie [] baskets = req.getCookies();
		log.info("* basket : {}", Arrays.toString(baskets));
		
		// 2. 응답문서 생성
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		if( baskets == null ) {
			out.println("장바구니가 비어있습니다.<br>");
		}else {
			
			for( Cookie cookie : baskets ) {
				String name = cookie.getName();
				String value = cookie.getValue();
				
				out.print("<h2>" + name + " : " + value + "</h2>");
			} // enhanced for
			
		} // if - else
		
		out.print("<a href='product.html'>상품선택 페이지</a><br>");
		out.print("<a href='/CartDeleteCookie'>장바구니 비우기 페이지</a><br>");
		
		out.flush();
		
	} // service

} // end class
