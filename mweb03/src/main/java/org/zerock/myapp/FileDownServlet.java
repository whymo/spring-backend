package org.zerock.myapp;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/FileDownload")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		req.setCharacterEncoding("UTF-8");
		
//		============================================
		
		// 1. 사용자로부터 다운로드 받을 전송파라미터로 획득
		String fileName = req.getParameter("fileName"); // 다운로드 요청 파일명
		log.info("\t + fileName : {}", fileName);
		
//		============================================
		
		// 2. 실제 파일 다운로드 처리를 수행
		
		// (1) 다운로드 파일의 절대경로를 생성
		final String uploadPath = "C:/temp/upload/";
		final String downloadPath = uploadPath + fileName;
		log.info("\t + downloadPath : {}", downloadPath);
		
//		============================================
		
		// (2) 다운받을 파일의 MIME type 획득
		ServletContext sc = this.getServletContext();
		String mimeType = sc.getMimeType(downloadPath);
		
		if( mimeType == null ) { // well - known extension이 아닐때를 대비
			mimeType = "application/octet-stream";
		} // if
		log.info("\t + mimeType : {}", mimeType);
		
//		============================================
		
		// (3) (2)에서 얻어낸 다운로드 파일의 MIME type을 응답문서의 컨텐츠 유형 지정
		res.setContentType(mimeType);
		
//		============================================
		
		// (4) 사용자가 지정한 다운로드 파일명에 다국어 문자 또는 whitespace 등이 포함되어 있는 경우
		// 다운로드 시에도 파일명이 깨지지 않고 정상적으로 출력되도록 인코딩 처리를 해줘야 한다.
		
		// 가장 마지막에 수행 (***)
		// Encoding ( 부호화, 예 : 전문 -> 모르스부호로 변환 )
		// 파일명 ->  ASCII 문자만 포함된 형태로 변환하지 않으면 다국어가 깨져버리기에 필요
		// ASCII 문자집합의 공식 명칭인 ISO-8859-1을 지정
		// 파일명을 바이트배열로 쪼개야 하기에 fileName.getBytes("utf8")로 작성
		String encodedFileName = new String(fileName.getBytes("utf8"), "ISO-8859-1");
		
//		============================================
		
		// (5) 응답문서의 헤더에 특수한 헤더를 추가해줘야 한다. ( 필수 ) (****)
		// 일반적인 HTTP 응답에서 Content-Disposition 헤더는 
		// 가. 컨텐츠가 브라우저에 inline 되어야 하는 웹페이지 자체이거나 웹페이지의 일부인지, 
		// 나. 아니면 attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지를 알려주는 헤더이다.
		
		res.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
		
//		============================================
		
		// (6) 다운로드 경로의 파일의 데이터를 읽어서, 응답메세지의 Body(Payload)에 넣어주자!
		
		// Java IO API 사용이 필요하다.
		@Cleanup
		FileInputStream fis = new FileInputStream(downloadPath);
		
		// 바가지 생성
		byte[] buf = new byte[100];
		
		// 응답 메세지의 Body에 출력할 바이트 기반의 출력 스트림
		@Cleanup
		ServletOutputStream sos = res.getOutputStream();
		
		// 읽어낸 바이트의 수를 반환
		int readBytes = fis.read(buf);
		
		while ( ( readBytes = fis.read(buf) ) != -1 ) { // EOF : 끝까지
			
			// 읽어낸만큼 출력
			sos.write(buf, 0, readBytes);
			
		} // while
		
		sos.flush();
		
//		============================================
		
	} // service

} // end class
