package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.service.Service;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@WebServlet("/View")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req,res) invoked.");
		
		log.info(">>>> /View <<<<");
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		out.println("<html><head></head><body>");
		
		// 위의 각 command 별로 수행되는 Service 개체의 execute 메소드의 수행결과를 Request Scope에서
		// 얻어서, 응답문서를 만들어 준다!
		Object bizResult = req.getAttribute(Service.BIZ_RESULT);
		
		out.println("<p>" + bizResult + "</p>");
		
		out.println("</body></html>");
		out.flush();
		
	} // service

} // end class
