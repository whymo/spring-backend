package com.weet.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// ================================================================================
@RequestMapping("/sample/*")
// + /sample/*는 sample/ 뒤에 무엇이 오든 처리하겠다는 의미로 Base URI라고도 불린다.
// + ex. http://localhost:8080/sample/A( B, C, D도 가능 )
@Controller 
// + 컨트롤러로 만들어 주는 어노테이션
// + 컨트롤러가 빈으로 등록되어야 하기 때문에, 자바 빈즈 규약을 지켜야 한다.
//================================================================================
public class SampleController {
	
	// ============================================================================
	@RequestMapping(value="handleA", method=RequestMethod.GET)
	// + 이렇게 RequestMapping을 할 경우에는 위의 매핑과 합쳐져서
	// + http://localhost:8080/sample/handleA만 처리하게 된다.
	// + method를 통해 GET방식인지 POST방식인지 지정할 수 있다.
	// + GET 방식으로 지정하였는데, 만약 POST 방식으로 전송이 오면 405 오류가 뜬다.
	// + method를 생략하여, @RequestMapping(value="A")로만 작성해도 된다.
	// ============================================================================
	public void handleA() {
		log.trace(">>>>>>>> handleA() invoked.");
	} // handleA
	
	// ============================================================================
	@RequestMapping(path="A")
	// + path로 지정해 줘도, value로 지정해 줘도 된다.
	// ============================================================================
	public String doA() {
		log.trace(">>>>>>>> doA() invoked.");
		
		return "A"; 
		// + View의 이름 반환
		// + -> A.jsp View를 찾게 된다.
		// + View는 Views 폴더 내에서 생성하면 된다.
		
	} // doA
	
	// ============================================================================
	@RequestMapping("B")
	// + 이와 같이 value나 path로 지정하지 않고 지정해줘도 된다.
	// ============================================================================
	public String doB() {
		log.trace(">>>>>>>> doB() invoked.");
		
		return "B";
		// + /WEB-INF/views/B.jsp 파일을 불러오게 된다.
	} //doB
	
	// ============================================================================
	
	@RequestMapping(path="C", method=RequestMethod.GET)
	public String doC() {
		log.trace(">>>>>>>> doC() invoked.");
		return "ABC";
		// + /WEB-INF/views/ABC.jsp 파일을 불러오게 된다.
	} // doC
	
	// ============================================================================
	@RequestMapping(path="C", method=RequestMethod.POST)
	// + 이렇게 되면, 전송파라미터가 오는 방식에 따라서 달라지는데
	// + 동일한 http://localhost:8080/sample/C 경로로 요청이 들어와도
	// + 방식이 GET인지, POST인지에 따라서 doc가 발생하는지,
	// + doCWhenPost 발생하는지 달라지게 된다.
	// ============================================================================
	public String doCWhenPost() {
		log.trace(">>>>>>>> doCWhenPost() invoked.");
		
		return "ABC";
		// + /WEB-INF/views/ABC.jsp 파일을 불러오게 된다.
		// + servlet-context.xml 설정파일에 아래와 같은 설정이 있다 :
		// + <jsp prefix="/WEB-INF/views/" suffix=".jsp" />
		// + 최종 호출할 뷰의 경로는 아래와 같이 설정된다.
		// + " 접두사 + 반환된 뷰의 이름(return) + 접미사 " 
		// + => /WEB-INF/views/ + 반환된 뷰 이름 + .jsp
		// + => /WEB-INF/views/반환된 뷰 이름.jsp
		
	} // doC
	
	// ============================================================================
	@RequestMapping("WONDERWOMAN") // 상세 URI
	// + 만약, 전송방식(HTTP method)을 특정하지 않았을 경우에는 모든 전송방식을 수용한다는 의미다.
	// + 만일 이와 같이 타입선언부 위에 @RequestMapping(path)가 있다면, 최종 매핑URI는
	// + 기본 URI + 상세 URI가 된다. ( /sample + /WONDERWOMAN )
	// ============================================================================
	public String superHero() {
		log.trace(">>>>>>>> superHero() invoked.");
		
		// 비지니스 로직 생략 -> MODEL 데이터도 발생하지 X -> 뷰에 전달 할 것이 없음
		
		return "superHero";
	} // superHero

} // end class
