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

@WebServlet("/TTT")
public class TTT extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// ==  쿠키 생성  ==================================
		
		// Cookie 변수명 = Cookie(String name, String value)
		Cookie name = new Cookie("name","wowwwowwwowwwowwwoww");
		Cookie age = new Cookie ("age","23");
		
		name.setMaxAge(1 * 60 * 60); // 1시간의 만료기간 설정
		age.setMaxAge(1 * 60 * 60); // 1시간의 만료기간 설정
		
		// ==  쿠키 삭제  ==================================
		
		name.setMaxAge(1); // 1시간의 만료기간 설정
		age.setMaxAge(1); // 1시간의 만료기간 설정
		
		res.addCookie(name);
		res.addCookie(age);
		
		// ==  응답문서 생성  ==================================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		// out.print("<p> 2개의 쿠키를 보냈으니, 잘 저장하세요</p>");
		out.print("<p> 2개의 쿠키를 보냈으니, 잘 삭제하세요</p>");
		
		out.flush();
		
	} // service

} // end class
