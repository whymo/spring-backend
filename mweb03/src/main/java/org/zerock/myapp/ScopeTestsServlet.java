package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
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

@WebServlet("/ScopeTests")
public class ScopeTestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		ServletContext appScope = req.getServletContext(); // App Scope 관리객체
		HttpSession sessionScope = req.getSession();	  // Session Scope 관리객체
		HttpServletRequest reqScope = req;				  // Req. Scope 관리 객체
		
		// ==============================================
		
		String sharedData = "__SHARED_DATA__";
		
		// ==============================================

		// 1. Application Scope : WAS가 구동되고 있다면, 브라우저를 껐다 켜도 URL로 이동하면 살아있다.
	
		 // appScope.setAttribute("APP_SCOPE", sharedData); // app Scope에 데이터를 올려 놓음 ( 데이터이름, 데이터객체 )
		 // String getSharedDataFromAppScope = (String) appScope.getAttribute("APP_SCOPE"); // 데이터를 변수에 넣었다.
		
		// 다른 Servlet에서도 사용이 가능하다.
		// 단, app Scope을 올려 놓은 WAS가 죽는다면, 그 값이 확실히 전달되지 않을 수 있다.
		
		// ==============================================
		
		// 2. Session Scope : 웹 브라우저 마다 생성이 되고 죽는다.
		
		// sessionScope.setAttribute("SESSION_SCOPE", sharedData); // session Scope에 데이터를 올려 놓음 ( 데이터이름, 데이터객체 )
		// String getSharedDataFromSessionScope = (String) sessionScope.getAttribute("SESSION_SCOPE");
		
		// 다른 Servlet에서도 사용이 가능하다.
		// 이때 브라우저는 단순히 웹 창이 아니라 크롬 창을 의미한다.
		// 즉, 이 서블렛이 런온서버 했을때 크롬창이 뜨면 크롬의 Session 영역에 데이터를 올려놓았음을 의미하며,
		// 이때 다른 서블릿에서 크롬이 아닌 다른 브라우저에서 열려고 하면 데이터가 그 브라우저에는 없기에 불가능하며,
		// 같은 크롬일지라도 올려놓은 크롬창을 껐다가 다른 서블릿에서 새로운 크롬창으로 열려고 해도 파괴되었기에 불가능하다.
		
		// ==============================================
		
		// 3. Request Scope : 응답이 완료되면 죽는다.
		sessionScope.setAttribute("REQUEST_SCOPE", sharedData); // Request Scope에 데이터를 올려 놓음 ( 데이터이름, 데이터객체 )
		String getSharedDataFromRequestScope = (String) reqScope.getAttribute("REQUEST_SCOPE");
		
		// Request Scope는 출력할때 null이 들어가게 되는데, 그 이유는 이미 응답이 완료되었기 때문에 죽었기 때문이다.
		
		// ==============================================
		
		// 응답문서 준비
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		// 각 공유영역에서 얻어낸 공유 데이터를 출력
		// String data1 = getSharedDataFromAppScope;			// from app scope
		// String data2 = getSharedDataFromSessionScope;		// from session scope
		String data3 = getSharedDataFromRequestScope;		// from request scope
		
		// out.println("<h1> 이것은 AppScope 공유데이터입니다. ->" + data1 + "</h1>");
		// out.println("<h1> 이것은 SessionScope 공유데이터입니다. ->" + data2 + "</h1>");
		out.println("<h1> 이것은 requestScope 공유데이터입니다. ->" + data3 + "</h1>");
		
		out.flush();
		
		// ==============================================
		
	} // service

} // end class
