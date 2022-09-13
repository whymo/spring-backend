package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/CartBasket")
public class CartBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		List<String> list = null;
		
		try {
			
			HttpSession sess = req.getSession(false);
			log.info("\t + sess : {}", sess);
			// 세션이 죽지 않았기에, 그대로 세션Id가 동일하게 나온다.
			// 기존 세션이 없으면 장바구니도 없기에, false를 주어 출력하지 않게 하였다.
			
			Objects.requireNonNull(sess);
			// Null인지 체크
			
			// =====================================
			
			list = (List<String>) sess.getAttribute("basket");
			// Session Scope에 올려져 있기에 sess에서 얻어야 한다.
			
			Objects.requireNonNull(list);
			// Null인지 체크
			
			list.forEach(log::info);
			// 널이 아니라면 하나씩 찍어라
			
			// =====================================
			
		} catch(Exception e) {
			throw new ServletException(e);
		} // try - catch
		
		// =====================================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h1>장바구니에 내용</h1>");
		out.println("<ol>");
		
		list.forEach(s -> {
			out.println("\t <li>" + s + "</li>");
		}); // for each
		
		out.println("</ol>");
		
		out.println("<a href='/CartDelete'>장바구니 비우기</a>");
		
		out.flush();
		
	} // service

} // end class
