package org.zerock.myapp.servlet;

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

@WebServlet("/A")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		log.info("A Servlet - 요청 잘 받았습니다.");
		log.info("A Servlet - 저는 여기까지만 처리합니다.");
		
		// B서블릿으로 위임시킨다.
		req.getRequestDispatcher("/B").forward(req, res);
		
	} // service

} // end class
