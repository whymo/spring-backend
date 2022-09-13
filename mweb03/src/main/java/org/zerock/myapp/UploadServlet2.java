package org.zerock.myapp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/Upload2")

// maxFileSize의 기본 단위는 바이트로 1024 * 1024 * 20는 최대파일크기가 20mb라는 이야기이다.
// maxRequestSize는 최대 Multipart의 수를 의미한다.
// location는 저장할 위치를 지정하게 된다.

@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
public class UploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		req.setCharacterEncoding("utf8");
		
//		============================================
		
		// 업로드할 파일이 여러개일 경우
		Collection<Part> multipart = req.getParts();
		
//		============================================
		
		// 현재 날짜를 얻어서 파일명에 붙이기
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fileDate = sdf.format(date);
		
//		============================================
		
		// 현재 경로에 현재 날짜 폴더 생성
		String uploadPath = "c:/Temp/upload/" + fileDate;
		File uploadDir = new File(uploadPath);
		
//		============================================
		
		// 해당 경로가 없으면 폴더를 생성
		if(!uploadDir.exists()) uploadDir.mkdir();
		
//		============================================
		
		// 1. enhanced for문 사용 버전
		
		for( Part part : multipart ) {
			
			int A = 1;
			
//			============================================
			
			log.info("{}. part : {}", A, part);
			log.info("{}. part Name : {}", A, part.getName());
			log.info("{}. part Header Name : {}", A, part.getHeaderNames());
			log.info("{}. part Type : {}", A, part.getContentType());
			log.info("{}. part FileName : {}", A, part.getSubmittedFileName());
			
//			============================================
			
			if( !part.getName().equals("uploadFile") ) {
				continue;
			} // if
			
//			============================================
			
			// 원래 파일명에서 확장자( .jpg / .png 등 )만 추출
			String fileName = part.getSubmittedFileName();
			String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			
//			============================================
			
			// UUID 생성 후 뒤에 추출한 확장자 연결
			UUID uuid = UUID.randomUUID(); // 랜덤한 UUID 생성
			String newFileName = uuid.toString() + extension; // UUID와 확장자 결합
			
			// 원래 파일명에 오늘 날짜를 붙여서 업로드
			part.write( uploadPath + "/" + fileDate + "_" + newFileName );
			
			// 임시파일 제거
			part.delete();
			
			A++;
			
		} // enhanced for
		
//		============================================
		
		// 2. stream 사용
		
//		multipart.stream().
//			filter(part -> part.getName().equals("uploadFile")).
//			forEach(part -> {
//				
//				try {
//					part.write(fileDate + "_" + part.getSubmittedFileName());
//					part.delete();
//				} catch (IOException e) { ;; } // try - catch
//				
//			});
		
	} // service

} // end class
