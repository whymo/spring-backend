package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
@WebServlet("/Test0708")
public class Test0708Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//		req.setCharacterEncoding("UTF-8");
//		
//		// =================================
//		// 1. 전송파라미터를 DTO객체로 수집
//		// =================================
//		
//		 Board b = new Board();
//		 b.setBoardTitle(req.getParameter("boardTitle"));
//		 b.setBoardContent(req.getParameter("boardContent"));
//		 b.setBoardWriter(req.getParameter("boardWriter"));
//		 
//		// =================================
//		 // 2. DB 테이블에 새로운 게시글 등록
//		// =================================
//		 
//		 if (new BoardService().insertBoard(b) > 0) {  // 성공했을 때에는
//
//		        res.sendRedirect("/board/boardList"); // 게시판 목록으로 이동한다.
//
//		 } else {    // 만약 실패했을 경우
//			 
//		        view = req.getRequestDispatcher("views/board/boardError.jsp"); // 오류 페이지로 이동한다.
//
//		        req.setAttribute("message", "게시글 등록 실패!");
//		        view.forward(req, res);
//		        
//		 } // if-else 
//
//	} // service

} // end class
