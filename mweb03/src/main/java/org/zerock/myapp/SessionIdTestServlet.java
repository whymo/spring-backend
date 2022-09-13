package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/SessionIdTest")
public class SessionIdTestServlet extends HttpServlet {
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
		
		// 1. Session 생성
		
		HttpSession sess = req.getSession();			// 기존 세션이 있으면 돌려주고, 없으면 만들어달라
		// HttpSession sess1 = req.getSession(true);	// 기존 세션이 있으면 돌려주고, 없으면 만들어달라
		// HttpSession sess2 = req.getSession(false);	// 기존 세션이 있으면 돌려주고, 없으면 만들지 말라!! ( 잘 사용하지 X )
		
		// =======================================================
		
		// 2. Session Id 얻어내기
		
		String sessionId = sess.getId();
		log.info("\t + 1. sessionId :{}", sessionId);
		// sessionId는 유니크한 전역적인 Id로 생성된다.
		
		// =======================================================
		
		// 3. Session Scope 파괴
		
		sess.invalidate(); // 이 세션Id로 식별되는 Session Scope 공유데이터 영역 파괴
		log.info("\t + Session Scope Destroyed Definitely.");
		
		// 파괴되었기에, 새로고침을 하면 새로운 Session Id가 생성되게 된다.
		// 파괴되지 않았다면, 새로고침을 해도 원래 기존의 Session Id가 나온다.
		
		// =======================================================
		
		// 4. Session Scope 파괴 후 다시 Session Id 생성
		
		sess = req.getSession();
		sessionId = sess.getId();
		log.info("\t + 2. sessionId :{}", sessionId);
		
		// 서로 다른 Session Id가 출력되고 있음을 알 수 있다.
		
		// =======================================================
		
	} // service

} // end class
