package org.zerock.myapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@CrossOrigin
// + @CrossOrigin을 붙여 놓으면 동일출처 원칙을 위반해도 데이터를 보내준다.
// + 비동기 호출로도 가능하게 만들어 준다.

@RestController
@RequestMapping("/product/")
public class PathVariableController {
	
	// + Restful 방식의 컨트롤러 헨들러 메소드의 Request Mapping은 아래의 2가지를 잘 조합해야 한다.
	// + (1) Http Method : PUT(C), GET(R), POST(U), DELETE(D) ( 4가지 )
	// + (2) Mapping URI : CRUD 대상 자원을 지정하는 형태로 매핑
	
	// + 아래의 예와 같이, 요청 비지니스 로직을 표현할 수 있는 형태로 전송방식(HTTP method)과
	// + 요청URI(Request URI)를 조합해서 매핑을 표현하는 방식이 바로 "Restful" 방식이다.
	
	// ===================================================================
	// + Request URI 안에 지정된 경로 변수( Path Variable, 기호 : {경로변수명} ) 값 얻는 법
	// + : 스프링에서는 @PathVariable이란 어노테이션을 제공하는데, 이를 통해서 한다.
	// ===================================================================
	
	// ===================================================================
	// Case 1. 신규상품 등록( CREATE ) -> PUT
	// ===================================================================
	// + {}는 경로 구분자이다.
	// + {} 안에는 가변적인 경로가 들어오게 된다.
	// ===================================================================
	
	@PutMapping(path="/{category}/new", produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf8")
	public String createNewProduct(@PathVariable("category") String productCategory) {
		
		// ===================================================================
		// + Request URI로부터, 경로 변수의 값 얻어내기
		// + : 얻어낸 경로변수 값을 매개변수의 타입에 맞게 형변환까지 해서 넣어준다.
		// + : 주의. 매개변수의 이름은 경로변수명과 직접적인 연관성은 없기에, 자유롭게 작성이 가능하다.
		// ===================================================================
		
		log.trace("createNewProduct() invoked.");
		log.trace("\t + productCategory : {}",productCategory);
		
		return "SUCCESS";
		
	} // createNewProduct
	
	// ===================================================================
	// Case 2. 기존 상품 삭제( DELETE ) -> DELETE
	// ===================================================================
	
	@DeleteMapping(path="/{category}/{id}", produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf8")
	public String removeProduct(
									@PathVariable("category") String productCategory,
									@PathVariable("id") Integer productId ) {
		
		log.trace("removeProduct() invoked.");
		log.trace("\t + productCategory : {}", productCategory );
		log.trace("\t + productId : {}", productId );
		
		return "SUCCESS";
		
	} // removeProduct
	
	// ===================================================================
	// Case 3. 기존 상품 수정( UPDATE ) -> POST
	// ===================================================================
	
	@PostMapping(path="/{category}/{id}", produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf8")
	public String updateProduct(@PathVariable("category") String productCategory, @PathVariable("id") Integer productId) {
		
		log.trace("updateProduct() invoked.");
		log.trace("\t + productCategory : {}", productCategory );
		log.trace("\t + productId : {}", productId );
		
		return "SUCCESS";
		
	} // updateProduct
	
	// ===================================================================
	// Case 4. 기존 상품 조회( READ ) -> GET
	// ===================================================================
	
	@GetMapping(path="/{category}/{id}", produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf8")
	public String readProduct(@PathVariable("category") String productCategory, @PathVariable("id") Integer productId) {
		
		log.trace("readProduct() invoked.");
		log.trace("\t + productCategory : {}", productCategory );
		log.trace("\t + productId : {}", productId );
		
		return "SUCCESS";
		
	} // readProduct
	
} // end class
