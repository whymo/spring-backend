package org.zerock.myapp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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

@WebServlet("/Upload")

// maxFileSize의 기본 단위는 바이트로 1024 * 1024 * 20는 최대파일크기가 20mb라는 이야기이다.
// maxRequestSize는 최대 Multipart의 수를 의미한다.
// location는 저장할 위치를 지정하게 된다.

@MultipartConfig(maxFileSize = 1024 * 1024 * 20,location = "c:/Temp/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		req.setCharacterEncoding("utf8");
		
//		============================================
			// 1. 파일이 2개 이상일때
//		============================================
		
		// multiple 속성이 있는 <input type="file" name="uploadFile" multiple>태그로
		// 업로드된 복수 개의 파일을 저장하는 로직
		Collection<Part> multipart = req.getParts();
		multipart.forEach(log::info);
		
//		============================================
		
		int countPart = multipart.size();
		log.info("countPart : {}", countPart);
		
//		============================================
		
		Iterator<Part> iter = multipart.iterator();
		while(iter.hasNext()) {
			Part part = iter.next();
			
			// 지금 얻어낸 Part 객체가 파일 데이터를 포함하고 있는 Part인지
			// 아니면 정상적인 파라미터를 포함하고 있는 Part 인지 구별해야 한다.
			
			// 첨부파일만 있는 Part 필터링
			String name = part.getName();
			
			if("uploadFile".equals(name)) {
				
				log.info("\t + file size : {}", part.getSize());
				
				// 지정된 경로 디렉토리에 저장
				String sfn = part.getSubmittedFileName();
				part.write(sfn);	// 지정된 파일명으로 파일데이터 저장
				part.delete();		// 임시파일 삭제

			} else continue; // if
			
		} // while
		
//		============================================
		
		Part[] arr = new Part[multipart.size()];
		multipart.<Part>toArray(arr);
		
		log.info("arr : {}", Arrays.toString(arr));
		
//		============================================
			// 2. 파일이 1개 일때
//		============================================
		
//		// 1. 일반적인 전송파라미터의 값 획득
//		String writer = req.getParameter("writer");
//		log.info("\t + 1. writer :{}", writer);
//		
////		============================================
//		
//		// 2. 파일 데이터가 저장된 멀티파트의 개별파트마다
//		// @MultipartConfig 어노테이션의 location 속성에 지정된
//		// 경로에 첨부파일을 저장하자
//		
////		============================================
//		
//		// (1) 개별파트로부터 획득 : 파트 1개만 얻어낼 수 있다.
//		
//		Part part1 = req.getPart("uploadFile");
//		log.info("\t + 2. part1 :{}", part1);
//		
////		============================================
//		
//		// (2) 획득한 특정 파트의 각종 정보를 획득 및 출력
//		
//		String name = part1.getName();
//		long size = part1.getSize();
//		String contentType = part1.getContentType();
//		String submittedFileName = part1.getSubmittedFileName();
//		
//		log.info("\t + 3. name :{}", name);
//		log.info("\t + 4. size :{}", size);
//		log.info("\t + 5. contentType :{}", contentType);
//		log.info("\t + 6. submittedFileName :{}", submittedFileName);
//		
//		Collection<String> headerNames = part1.getHeaderNames();
//		
//		// headerNames.forEach(log::info);
//		
//		headerNames.forEach(s -> {
//			log.info("\t + 7. header Name : {}", s);
//			// 7번은 헤더의 이름이 나오게 된다.
//			
//			log.info("\t + 8. {} : {}", s, part1.getHeader(s));
//			// 8번은 헤더의 이름 : 해당 헤더의 값을 찍게 된다.
//		});
//		
////		============================================
//		
//		// (3) 파트에 저장된 파일 데이터를 디스크의 지정(@MultipartConfig)된 폴더에 저장
//		
//		part1.write(submittedFileName); // location=c:/Temp/upload/{submittedFileName}로 저장
//		part1.delete();					// 디스크에 파일 저장시 사용된 임시파일 삭제
//		
//		// + 이때 임시파일은 대용량 파일을 다운받을때 생기는 임시파일을 의미한다.
//		
////		============================================
		
	} // service

} // end class
