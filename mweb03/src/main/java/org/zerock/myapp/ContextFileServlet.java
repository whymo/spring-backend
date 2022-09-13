package org.zerock.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
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

@WebServlet("/ContextFile")
public class ContextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// =============================================
		
		req.setCharacterEncoding("UTF-8");
		
		// webapp파일 밑에 있는 텍스트 파일 읽기
		
		String readFile = "/WEB-INF/testFile.txt"; // 텍스트 파일의 위치 지정
		
		ServletContext sc = this.getServletContext();
		
		@Cleanup
		InputStream is = sc.getResourceAsStream(readFile);
		
		@Cleanup
		BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 버퍼 + 바이트기반을 문자기반으로
		
		// =============================================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<p>");
		// =============================================
		String line = null;
		
		while ( ( line = br.readLine() ) != null ) {
			out.println(line);
		} // while : line의 값을 br.readline에서 가져오기 때문에 빈문장이 들어오기 전까지 무한반복을 실행한다.
		// =============================================
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
		
		out.flush();
		
	} // service

} // end class
