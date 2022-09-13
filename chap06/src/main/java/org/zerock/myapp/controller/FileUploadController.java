package org.zerock.myapp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.uuid.UUIDGenerator;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/fileupload/*") // 기본 URI
@Controller
public class FileUploadController {
	
	// ================================================
	// 1. 파일 업로드 화면 요청 핸들러 메소드
	// ================================================
	
	@GetMapping("page") // 상세 URI
	public void page() {
		
		log.trace("page() invoked.");
		// + void의 경우에는 view의 이름이 기본 + 상세 URI로 자동호출된다.
		// + 이와 같이 단순하게 view를 띄우는 것은 servlet-context.xml파일에서 지정할 수도 있으나
		// + 그렇게 처리할 경우 @RequestMapping에서 Mapping table로 관리가 불가능하기에 지양해야 한다.
		
	} // page
	
	// ================================================
	// 2. 파일 업로드 처리용 핸들러 메소드 ( 비추 )
	// ================================================
	
	@PostMapping("/notdoit")
	// + 매개변수로 들어오는 files가 비어있는지 확인
	// + assert로 nonnull을 확인하는 방법은 테스트에서만 사용하는 것이 좋다.
	public void notdoit( @NonNull List <MultipartFile> files ) {
		
		log.trace("notdoit() invoked.");
		files.forEach(log::info);
		
		// + 업로드할 타켓 폴더 지정
		String tergetDir = "C:/temp/upload/";
		
		// + MultipartFile 객체가 제공하는 정보 4가지 출력
		// + 람다식으로 처리하는 것은, 파일 저장시 예외가 발생하면,
		// + 무조건 예외를 알아서 처리해야 하며, 위로 던질 수 없기에 추천하지 않는다.
		files.forEach(f -> {
			log.info("1. ContentType : {}",f.getContentType());
			log.info("2. 전송파라미터의 Name : {}", f.getName());
			log.info("3. 원본 파일명 : {}", f.getOriginalFilename());
			log.info("4. size : {}", f.getSize());
			log.info("5. Resource : {}", f.getResource());
			log.info("==============================================");
			
			// + 업로드된 파일을 target 디렉토리에 저장
			if ( f.getSize() > 0 ) {
				
				// + C:/temp/upload/ + 원본 파일명 -> C:/temp/upload/ 밑에 파일로 저장한다는 의미
				File targetFile = new File(tergetDir + f.getOriginalFilename());
				
				try {
					f.transferTo(targetFile);
				} catch ( IllegalStateException | IOException e ) {
					e.printStackTrace();
				} // try - catch
				
			} // if : 파일 업로드 된 것만 저장
			
		}); // files.forEach
		
	} // notdoit
	
	// ================================================
	// 3. 파일 업로드 처리용 핸들러 메소드 ( 추천 ) (***)
	// ================================================
	
	@PostMapping("/doit")
	// + 매개변수로 들어오는 files가 비어있는지 확인
	public void doit( @NonNull List <MultipartFile> files, Model model ) {
		
		log.trace("doit() invoked.");
		files.forEach(log::info);
		
		// + 업로드할 타켓 폴더 지정
		String tergetDir = "C:/Temp/upload/";
		
		// ================================================
		// + UUID 사용 ver 2
		// + tergetDir 경로 밑에 현재 날짜 폴더를 만든 후, 그 밑에 업로드 파일을 저장해라
		
		// + Date 객체 생성
		Date date = new Date();
		
		// + Date 객체를 yyyymmdd로 포멧팅한다.
		// + /를 넣어서 경로 구분자를 만들어 줘야 한다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/");
		String dateFile = sdf.format(date);
		
		// + tergetDir에 현재 날짜를 붙여서 파일 생성
		tergetDir += dateFile;
		File uploadDir = new File(tergetDir);
		
		// + 파일이 없으면 생성
		if ( !uploadDir.exists() ) {
			uploadDir.mkdir();
		} // if
		// ================================================
		
		// + MultipartFile 객체가 제공하는 정보 4가지 출력
		for ( MultipartFile f : files ) {
			log.info("1. ContentType : {}",f.getContentType());
			log.info("2. 전송파라미터의 Name : {}", f.getName());
			log.info("3. 원본 파일명 : {}", f.getOriginalFilename());
			log.info("4. size : {}", f.getSize());
			log.info("5. Resource : {}", f.getResource());
			log.info("==============================================");
			
			// + 업로드된 파일을 target 디렉토리에 저장
			if ( f.getSize() > 0 ) {
				
				// + 대용량 파일 업로드도 고려하여, 성능 보조스트림을 이용한 입/출력 처리를 해야 한다.
				// + C:/temp/upload/ + 원본 파일명 -> C:/temp/upload/ 밑에 파일로 저장한다는 의미
				// + 원본 파일명 사용한 ver
				// String targetFile = tergetDir + f.getOriginalFilename();
				
				try {
					
					// ================================================
					// + UUID 사용 ver
					// + C:/temp/upload/ + UUID -> C:/temp/upload/ 밑에 파일로 저장한다는 의미
					// String targetFile = tergetDir + UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
					// ================================================
					// + UUID 사용 ver 2
					// + tergetDir 경로 밑에 현재 날짜 폴더를 만든 후, 그 밑에 업로드 파일을 저장해라
					
					String targetFile = tergetDir + UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
					// ================================================
					
					InputStream is = f.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);
					
					FileOutputStream fos = new FileOutputStream(targetFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					
					// + 자원 해제
					// + @Cleanup을 통해 닫아줄 수도 있으나,
					// + @Cleanup은 닫는 순서를 지켜주지 않는다.
					try ( is; bis; fos; bos; ) {
						
						// + 바가지 생성
						byte [] buf = new byte[300];
						
						int readBytes = 0;
						while ( ( readBytes = bis.read(buf) ) != -1 ) { // EOF
							bos.write(buf, 0, readBytes);
						} // while
						
						bos.flush();
						
					} // try - with - resources
					
					// + Model 객체에 성공의 유무를 확인하는 데이터 바인딩
					model.addAttribute("__RESULT__", "SUCCESS");
					
				} catch (IOException | NoSuchAlgorithmException e ) {
					e.printStackTrace();
					
					// + Model 객체에 성공의 유무를 확인하는 데이터 바인딩
					model.addAttribute("__RESULT__", "partially Failed.");
				} // try - catch
				
			} // if : 파일 업로드 된 것만 저장
						
		} // enhanced for
		
	} // doit
	

} // end class
