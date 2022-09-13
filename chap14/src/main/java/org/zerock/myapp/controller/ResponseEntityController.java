package org.zerock.myapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.Sample2VO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@CrossOrigin

@RequestMapping("/entity/")
@RestController
public class ResponseEntityController {
	
	// ===================================================================
	// + params 속성에 지정한 이름은 바로 "반드시" 들어와야 할 전송파라미터의
	// + 이름을 지정하게 된다. 여기에 지정된 전송 파라미터가 하나라도 수신되지
	// + 않았다면, 예외가 발생하게 된다. (****)
	// ===================================================================
	
	@GetMapping( 
				path="/check", 
				params = {"weight", "height"},
				produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Sample2VO> check( Double weight, Double height ){
		
		log.trace("check({}, {}) invoked.", weight, height );
		
		Sample2VO vo = new Sample2VO(height, weight);
		log.info("\t + vo : {}", vo);
	
		ResponseEntity<Sample2VO> response = null;
		BodyBuilder bodyBuilder = null;
		
		// -----------------------------------------------------------------
		// + 상태 코드 지정
		// -----------------------------------------------------------------
		// + ResponseEntity는 정적 메소드이기에 response 대신 ResponseEntity를 사용하였다.
		// -----------------------------------------------------------------
		
		if ( height < 10 ) {
			bodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
		} else {
			bodyBuilder = ResponseEntity.status(HttpStatus.OK);
		} // if - else
		
		log.info("\t + bodyBuilder : {}", bodyBuilder);
		
		// -----------------------------------------------------------------
		// + 바디에 넣는다.
		// -----------------------------------------------------------------
		
		response = bodyBuilder.<Sample2VO>body(vo);
		log.info("\t + response : {}", response);
		
		return response;
		
	} // check

} // end class
