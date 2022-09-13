package com.weet.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weet.app.domain.SampleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/return/*")
public class ReturnTypesController {
	
	// ===================================================================
	// Case 1. return type : String ( View의 이름 )
	// ===================================================================
	
	@GetMapping("/String")
	public String returnString() {
		log.info("returnString");
		return "return/String"; // => /WEB-INF/views/ + return/String + .jsp
	} // returnString
	
	// ===================================================================
	// Case 2. return type : void ( View이름 자동 지정 )
	// ===================================================================
	
	@GetMapping("/void")
	public void returnVoid() {
		log.info("returnVoid() invoked.");
		
		// + 리턴 타입이 void면, 뷰의 이름은 자동으로 호출 URI가 된다. ( 기본 URI + 상세 URI )
		// + void => /WEB-INF/views/ + return/void + .jsp
		
	} // returnVoid
	
	// ===================================================================
	// Case 3. return type : String + using "redirect:" Keyword 
	// ===================================================================
	
	@PostMapping("/redirect")
	public String returnRedirect() {
		log.info("returnRedirect() invoked.");
		
		return "redirect:/return/void";				// OK!!
		// return "redirect:void";					// OK!! ( 상세 URI가 void인 것을 찾는다. )
		
	} // returnRedirect
	
	// ===================================================================
	// Case 4. return type : String + using "forward:" Keyword
	// ===================================================================
	
	@GetMapping("/forward")
	public String returnForward() {
		log.info("returnForward() invoked.");
		
		// + 지정한 경로의 JSP로 Request Forwarding
		return "forward:/WEB-INF/views/return/void.jsp";		// OK!!
		
		// + 지정된 호출 URI와 GET 방식으로 매핑된 Controller의 Handler 메소드로 포워딩 수행
		// return "forward:/return/void";						// OK!!
		
		// + 기본 URI를 생략하면, 현재 컨트롤러에 지정된 Base URI를 자동으로 붙여서 포워딩을 수행
		// + 그렇기에 외부 URL로 포워딩을 수행하려고 해도, 자동으로 Base URI(/return)이 붙어 오류가 발생한다.
		// + ( redirect:는 자동으로 Base URI(/return)를 붙이지 않기에 외부 URL로도 이동이 가능하다. ) (***)
		// return "forward:void";
		
	} // returnForward
	
	// ===================================================================
	// Case 5. return type : java 객체 + using @ResponseBody (***)
	// ===================================================================
	// + @ResponseBody (****) : 
	// + 아래의 메소드가 리턴한 값을 응답 메세지의 Body에 넣어달라!!
	// ===================================================================
	// + 이 경우 pom.xml에 Java객체를 xml로 변환해주는 디펜더시를 추가하였기에
	// + SampleDTO이라는 Java 객체를 xml 파일로 변환해서 반환해준다.
	// ===================================================================
	
	// ===================================================================
	// 1 ) 자바 객체 -> XML 전송
	// ===================================================================
	
	@PostMapping("/ResponseBody")
	public @ResponseBody SampleDTO returnResponseBody(
			// + @NonNull : 이 매개변수의 값은 null이 되어서는 안된다는 의미이다. 
			// + ( final 키워드와 동일한 역할 수행 )
			@NonNull @RequestParam("name") String NAME,
			@NonNull @RequestParam("age") Integer AGE ) {
		
		log.info("returnResponseBody(NAME, AGE) invoked.");
		log.info("\t + NAME :{}, AGE : {}", NAME, AGE);
		
		SampleDTO dto = new SampleDTO();
		dto.setName(NAME);
		dto.setAge(AGE);
		
		log.info("\t + dto : {}", dto);
		
		// + Java 객체를 반환하면, 응답 메세지의 바디에 넣기 전에 변환 라이브러리를 통해서
		// + JSON이나 XML 문서로 변환해서 넣어준다.
		// + 결과적으로, 응답 메세지의 바디에 Java객체가 JSON이나 XML 문서로 변환되어 들어간다.
		// + JSON으로 갈 경우에는, JS의 연관배열과 같이 { "name" : "hoho", "age" : 777 }로 반환된다.
		return dto;
		
	} // returnResponseBody
	
	// ===================================================================
	// 2 ) String 전송
	// ===================================================================
	
	// + produces에는 무엇을 생성하는지 작성해줘야 한다.
	@PostMapping(path="/ResponseBody2", produces = {"text/plain; charset=utf8"} )
	@ResponseBody
	public String returnResponseBody2(
			@NonNull @RequestParam("name") String NAME,
			@NonNull @RequestParam("age") Integer AGE ) {
		
		log.info("returnResponseBody2(NAME, AGE) invoked.");
		log.info("\t + NAME :{}, AGE : {}", NAME, AGE);
		
		// + String 객체를 응답으로 전송
		String string = "안녕하세요!";
		
		// + 문자열 역시 JSON이나 XML 처럼, 순수한 데이터이기 때문에 변환없이 전송이 가능하다.
		return string;
		
	} // returnResponseBody2
	
	// ===================================================================
	// 3 ) 컬렉션 데이터 -> XML / JSON 전송
	// ===================================================================
	
	@PostMapping(path="/ResponseBody3")
	@ResponseBody
	public List<String> returnResponseBody3(
			@NonNull @RequestParam("name") String NAME,
			@NonNull @RequestParam("age") Integer AGE ) {
		
		log.info("returnResponseBody3(NAME, AGE) invoked.");
		log.info("\t + NAME :{}, AGE : {}", NAME, AGE);
		
		// + ArrayList 객체를 응답으로 전송
		List<String> list = new ArrayList<>();
		list.add("NAME_1");
		list.add("NAME_2");
		list.add("NAME_3");
		
		// + 컬렉션 데이터 역시 JSON이나 XML로 변환되어 전송이 가능하다.
		return list;
		
	} // returnResponseBody3
	
	// ===================================================================
	// 4 ) 사용자 정의 객체
	// ===================================================================
	
	@AllArgsConstructor
	@Data
	class Person {
		private String name;
		private int age;
	} // Person
	
	@PostMapping(path="/ResponseBody4")
	@ResponseBody
	public Person returnResponseBody4(
			@NonNull @RequestParam("name") String NAME,
			@NonNull @RequestParam("age") Integer AGE ) {
		
		log.info("returnResponseBody4(NAME, AGE) invoked.");
		log.info("\t + NAME :{}, AGE : {}", NAME, AGE);
		
		// + 사용자 정의 객체 또한 XML이나 JSON으로 변환되어 전송이 가능하다.
		return new Person("Yoyoyo", 29);
		
	} // returnResponseBody4

	// ===================================================================
	// Case 6. return type : Object + using "ResponseEntity"
	// ===================================================================
	// + "ResponseEntity" : 응답 메세지의 응답행, 헤더, 바디 모든 부분을
	// + 개발자 마음대로 만들어 낼 수 있게 해주는 스프링의 클래스
	// ===================================================================
	
	@PostMapping("/returnResponseEntity")
	// public ResponseEntity<String> returnResponseEntity() {
	public ResponseEntity<Person> returnResponseEntity() {
		
		log.info("returnResponseEntity() invoked.");
		
		// 1. JSON 문자열 준비 -> 응답 메세지의 바디에 넣을 데이터
		// String json = "{ 'name' : 'YOyoyoyo', 'age' : 29 }"; // 하드 코딩
		Person person = new Person("Yoyoyoyo", 29);
		
		// 2. 응답 메세지의 헤더를 구성
		HttpHeaders headers = new HttpHeaders();
		// + 컨텐츠 타입 지정 (****) : json으로 타입을 지정하고 있다.
		headers.add("Content-Type", "application/json; charset=utf8");
		
		// 3. 응답 메세지의 (1) 상태 코드 (2) 헤더 (3) 바디 모두 설정하여, 생성 및 변환
		// + 생성자 매개변수 선언부 : (String body, MultiValueMap<String, String> headers, HttpStatus status)
		// return new ResponseEntity<>(json, headers, HttpStatus.OK);
		return new ResponseEntity<>(person, headers, HttpStatus.OK);
		
	} // returnResponseEntity

} // end class
