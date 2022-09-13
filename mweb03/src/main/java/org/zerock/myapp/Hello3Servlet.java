package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@WebServlet( name = "Hello3",  // + urlPatterns는 String[] ( 스트링타입의 배열 )이다.
		urlPatterns = { "/xxx", "/yyy" } ) // mapping된 url이 2개이다.
public class Hello3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		//================================================================
		// HTTP Request 메시지로부터, 전송 파라미터들을 얻어내기 위해서는 다음과 같이 한다.
		//================================================================
		
		// 전송값이 1개일때 사용하는 방법
		String name = req.getParameter("name"); // name이란 이름의 전송파라미터의 값을 추출
		String age = req.getParameter("age"); // age이란 이름의 전송파라미터의 값을 추출
		
		//================================================================
		
		// 값이 여러개일때 사용하는 방법
//		String hobby = req.getParameter("hobby"); // hobby이란 이름의 전송파라미터의 값을 추출
		String[] hobby = req.getParameterValues("hobby");
		
		//================================================================
		
		// 값 확인
		log.info("\t + name : {}, age : {} ", name, age); // + name : hehehehehe, age : 1999999 
		log.info("\t + hobby : {}", Arrays.toString(hobby)); // + hobby : [1, 3]
		
		//================================================================
		
		// 전송 파라미터의 이름 출력
		Enumeration<String> parameterNames = req.getParameterNames();
		log.info("\t + paraNames : {}", parameterNames);
		
		while(parameterNames.hasMoreElements()) {
			String names = parameterNames.nextElement();
			log.info("\t + name : {}", names);
		} // while
		
		// + name : name, + name : age, + name : hobby로 출력
		
		//================================================================
		
		// Map형태로 출력하기
		
//		@Cleanup("clear") // 이 Map 객체는 읽기 전용이기때문에, clear시키면 안된다!!
		Map<String, String[]> map = req.getParameterMap(); // Map<String, String[]>로 틀이 잡혀있다.
		log.info("\t + map : {}", map);
		
		// + map : {name=[hehehehehe], age=[1999999], hobby=[1, 3]}로 출력
		
		//================================================================
		
		// Map 객체 중 hobby라는 키를 가지고 있는 값을 출력
		log.info("\t + map : {}, hobby : {} ", map, map.get("hobby"));
		
		// + map : {name=[hehehehehe], age=[1999999], hobby=[1, 3]}, hobby : [1, 3] 로 출력
		
		//================================================================
		
		// 문자 기반의 출력 스트림
		PrintWriter out =  res.getWriter();
		out.println("<h1>World!!!</h1>");
		out.println("<h3>name : " + name + "</h3>");
		out.println("<h3>age : " + age + "</h3>");
		
		//================================================================
		
		// 값이 여러개일때
		out.println("<h3>hobby : " + Arrays.toString(hobby) + "</h3>");
		
		// + 값이 없을 때에는 null값이 들어가게 된다.
		// + 값이 1개가 아닌 여러개일 경우에는 getParameter를 사용하면, 하나만 들어가고 나머지는 들어가지 않는다.
		// + 값이 여러개일 경우에는 getParameterValues를 사용해야 한다.
		
		//================================================================
		
		// 자원해제
		out.flush();
		out.close();
		
		//================================================================
		
	} // service

} // end class
