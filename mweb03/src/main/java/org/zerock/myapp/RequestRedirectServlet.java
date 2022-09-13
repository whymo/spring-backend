package org.zerock.myapp;

import java.io.IOException;
import java.net.URLEncoder;

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
@WebServlet("/RequestRedirect")
public class RequestRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 비지니스 로직 수행
		
		// 1. 비지니스 데이터 ( == Model ) : request Scope에 속성 바인딩
		// req.setAttribute("name", "홍만만");
		// req.setAttribute("address", "주소");
		
		// 1 - 2. 섹션 영역으로 공유 데이터 올려 놓기
		// HttpSession sess = req.getSession();
		// sess.setAttribute("name", "난섹션이다.");
		// sess.setAttribute("address", "주소");
		
		// 2. Redirect 응답 전송
		String queryStr = "?name="+URLEncoder.encode("홍길동","utf8")+"&address="+URLEncoder.encode("서울","utf8");
		// URLEncoder.encode("문자열","utf8"); ( 값부분만 바꿔주면 된다. )
		// 이 방법을 사용할 경우 URL에 name과 address가 보여진다.
		// 왜 URL 전체를 한번에 인코딩하지 않은 이유는, 그렇게 되면 특수문자까지도 인코딩으로 바뀌기때문이다.
		// 그렇기 때문에 값부분만 인코딩해줘야 한다.(***)
		
		res.sendRedirect("/ResponseRedirect" + queryStr); // (***)
		
		// + 이때 queryStr는 한글을 허용하지 않는 언어이기에, utf8로 URLEncoding을 해야 한다.
		
		log.info("Succeed to send a Redirection to the web browser");
		// Redirect의 경우 URL이 위임한 곳으로 변경된다.
		
	} // service

} // end class
