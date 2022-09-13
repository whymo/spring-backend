package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/CartSave") // 장바구니에 담기
public class CartSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// 웹 브라우저가 최초로 request message를 보내오면,
		// Servlet Container는 이 웹 브라우저에 이름(= 세션 ID)을 지어서
		// Response Message의 헤더에 담아서 전송한다.
		
		// 웹 브라우저는 응답헤더에서 자기의 이름(= 세션 ID)를 추출해서 메모리에 저장
		// 이후, 두번째 이상 요청 부터는 항상 자기의 이름을(= 세션 ID)을 함께 담아서 요청을 보낸다.
		
		// =======================================================
		
		// HttpSession 객체 => 추상적인 세션을 객체로 만든 것이다. 
		// request 객체로부터 획득이 가능하다.
		
		// =======================================================
		
		// 1. 장바구니에 저장할 상품항목을 전송 파라미터로 획득
		
		req.setCharacterEncoding("UTF-8");
		String product = req.getParameter("product");
		
		log.info("\t + product :{}",product);
		
		// =======================================================
		
		// 2. 장바구니 생성 및 수신된 상품을 장바구니에 추가
		
		HttpSession sess = req.getSession();			// 기존 세션이 있으면 돌려주고, 없으면 만들어달라
		// HttpSession sess1 = req.getSession(true);	// 기존 세션이 있으면 돌려주고, 없으면 만들어달라
		// HttpSession sess2 = req.getSession(false);	// 기존 세션이 있으면 돌려주고, 없으면 만들지 말라!! ( 잘 사용하지 X )
		
		// sess.getAttribute("장바구니 이름지정");
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) sess.getAttribute("basket");
		// @SuppressWarnings는 너무 강하게 체크하지 말라는 이야기이다.
		
		if( list != null ) { 					// 1. 장바구니가 있어 얻어냄
			
			list.add(product);					// 1-1. 장바구니가 있으니, 장바구니에 상품 추가
			
		} else {								// 2. 장바구니가 Session Scope에 없다.
			
			list = new ArrayList<>();			// 2-1. 장바구니가 없으니 장바구니를 만들고
			list.add(product);					// 2-2. 상품을 추가한 후에
			
			sess.setAttribute("basket", list);	// 2-3. Session Scope에 올린다.
			
		} // if - else
		
		log.info("\t + list : {}", list);
		
		// =======================================================
		
		// 3. 응답문서 준비
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h1>장바구니에 담기 성공</h1>");
		out.println("<a href='/CartSave'>장바구니 보기</a>");
		
		out.flush();
		
		// =======================================================
		
	} // service

} // end class
