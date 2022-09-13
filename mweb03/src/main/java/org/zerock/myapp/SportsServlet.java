package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
@WebServlet("/Sports")
public class SportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		//=======================================================
		
		// 1. 전송 파라미터 수신하기
		
		// 수신받은 값의 한글이 깨지는 것을 방지
		req.setCharacterEncoding("utf-8");
		
		String[] sports = req.getParameterValues("sports");
		String sex = req.getParameter("sex");
		
		//=======================================================
		
		// 2. 응답문서 만들기
		
		res.setContentType("text/html; charset=utf8");
		
		//=======================================================
		
		// 2. 응답문서 만들기
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		
		for( String sport : sports ) {
			out.print("좋아하는 운동 : " + sport + "<br>");
			log.info("\t + 좋아하는 운동 : {}", sport);
		} // for : 값 하나씩 빼오기
		
		out.println(Arrays.toString(sports)); // Arrays.toString으로 배열의 원소 출력하기
		
		out.print("성별 : " + sex + "<br>");
		log.info("\t + 성별 : {}", sex);
		out.print("</body></html>");
		
		//=======================================================
		
	} // end service

} // end class
