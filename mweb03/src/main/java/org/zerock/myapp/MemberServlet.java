package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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

@WebServlet("/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		req.setCharacterEncoding("utf-8");
		
		Enumeration<String> paraNames = req.getParameterNames();
		
//		======================================================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		
		while( paraNames.hasMoreElements()) {
			String name = paraNames.nextElement();
			String val = req.getParameter(name);
			out.print("<h3> + name : " + name + ", value : " + val + "</h3><br>");
			// out.println(String.format("name : %s, value : %s", name, val));
		} // while
		
		out.print("</body></html>");
		
		out.flush();

	} // service

} // end class
