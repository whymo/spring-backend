package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 1. 전송 파라미터 수신하기 ( 폼의 name과 동일하게 해야 한다. )
		String userid = req.getParameter("userid");
		String passwd = req.getParameter("password");
		
		log.info("\t + userid:{} , passwd : {}", userid, passwd);
		
		//=======================================================
		
		// 2. 응답 문서 만들기
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("아이디 값 : " + userid + "<br>");
		out.print("비밀번호 값 : " + passwd + "<br>");
		out.print("</body></html>");
		
		// + 로그인폼에서 입력한 값을 화면에 보여준다.
		
		out.flush();
		
	} // service

} // end class
