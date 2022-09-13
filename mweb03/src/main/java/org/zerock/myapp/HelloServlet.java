package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor // 기본 생성자 Constructor
@Log4j2

// @WebServlet는 Servlet을 자동으로 web.xml파일에 등록시켜 준다. ( 작성되지는 않는다. )

//@WebServlet("/Hello") // http://localhost:8080/Hello일때 실행
//@WebServlet({"/Hello", "/test/hello", "/test"}) // URL - Mapping : 디렉터리 방식

// 확장자 패턴방식으로 *.do을 지정하면, URL이 .do로 끝나기만 하면 해당 서블릿을 수행시켜 준다. (**)
// 확장자 패턴은 반드시 "*.확장자" 형태로 지정해 줘야 한다.
// 확장자의 종류로는 .do, .nhn, .go가 있다.
// *는 와일드카드로 all이라는 의미이기에, *에는 아무거나 들어가도 된다.

@WebServlet("*.nhn") // URL - Mapping : 확장자 패턴 방식
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//========================================================
	
	// 1. GET 방식

	
    // request는 요청 받는 것, response는 응답을 보내는 것
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		log.trace("doGet({}, {}) invoked.", req, res);
		
		PrintWriter out =  res.getWriter();
		out.println("<h1>World!!!</h1>");
		// 출력 스트림
		// WAS는 이처럼 동적으로 태그를 넣어서 문서를 만들 수 있다.
		
		out.flush();
		out.close();
		
	} // doGet : Get방식으로 가지고 올때 ( POST방식이면 응답을 보내지 않는다. )
	
	// + URL로 넣는 모든 요청은 GET방식으로 이루어진다.
	
	//========================================================
	
	// 2. POST 방식

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("doPost(req, res) invoked.");
		
		this.doGet(req, res);
		
	} // doPost : Post방식으로 가지고 올때 ( Get방식이면 응답을 보내지 않는다. )
	
	//========================================================

} // end class
