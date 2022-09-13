package org.zerock.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.SampleVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RestController
@RequestMapping("/sample/")
//+ String / XML / JSON 형식으로 돌려주는 컨트롤러
public class StringXMLJSONController {
	
	// ===================================================================
	// 1. 순수한 데이터로 문자열( String )을 반환하는 헨들러 메소드 작성
	// ===================================================================
	
	// 1 ) 일반 text
	
	@GetMapping(path="/sendText", produces = { "text/plain; charset=utf8" })
	// + produces는 헨들러가 발생시키는 미디어 타입( Content type )을 지정한다.
	// + text/plain; charset=utf8는 utf8로 구성된 평범한 text라는 의미이다.
	public String sendText() {
		
		log.info("sendText() invoked.");
		
		return "안녕하세요!! 반가워요!";
		// + 이제는 더 이상 View의 이름을 반환하지 않고, 순수한 데이터를 반환한다. (***)
		// + http://localhost:8080/sample/sendText로 접속하면 반환된 순수 데이터가 나타난다.
		
	} // sendText
	
	// ===================================================================
	
	// 2 ) html
	
	@GetMapping(path="/sendText2", produces = { "text/html; charset=utf8" })
	// + text/html; charset=utf8로 하면, html 파일로 인식하여 태그를 적용시켜 준다. (**)
	public String sendText2() {
		
		log.info("sendText2() invoked.");
		
		return "<h1>안녕하세요!! 반가워요!</h1>";
		// + 이제는 더 이상 View의 이름을 반환하지 않고, 순수한 데이터를 반환한다. (***)
		// + http://localhost:8080/sample/sendText로 접속하면 반환된 순수 데이터가 나타난다.
		
	} // sendText2
	
	// ===================================================================
	
	// 3 ) MediaType. 적용 ver
	
	@GetMapping(path="/sendText3", produces = { MediaType.TEXT_HTML_VALUE + "; charset=utf8" })
	// + MediaType을 통해 ContentType을 지정해 줄 수는 있으나, charset이 적용되지 않기에 따로 적용해 줘야 하며
	// + MediaType 사용 시에는 반드시 뒤에 VALUE가 있는 버전으로 사용해야 한다.
	public String sendText3() {
		
		log.info("sendText3() invoked.");
		
		return "<h1>안녕하세요!! 반가워요!</h1>";
		// + 이제는 더 이상 View의 이름을 반환하지 않고, 순수한 데이터를 반환한다. (***)
		// + http://localhost:8080/sample/sendText로 접속하면 반환된 순수 데이터가 나타난다.
		
	} // sendText3
	
	// ===================================================================
	// 2. 순수한 데이터로 XML 문서를 반환하는 헨들러 메소드 작성
	// ===================================================================
	
	@GetMapping(path="/sendXML", produces = MediaType.TEXT_XML_VALUE + "; charset=utf8" )
	public SampleVO sendXML() {
		
		log.info("sendXML() invoked.");
		
		SampleVO vo = new SampleVO("JOJOJOJO", 30);
		log.info("\t + vo : {}", vo);
		
		return vo;
		
//		+ URI로 접속하면 아래와 같이 출력된다.
//		<SampleVO>
//			<name>JOJOJOJO</name>
//			<age>30</age>
//		</SampleVO>
		
	} // sendXML
	
	// ===================================================================
	
	// + 배열 버전
	
	@GetMapping(path="/sendXML2", produces = MediaType.TEXT_XML_VALUE + "; charset=utf8")
	public SampleVO[] sendXML2() {
		
		log.info("sendXML2() invoked.");
		
		SampleVO[] vo = { 
							new SampleVO("AAAAAAAA", 10),
							new SampleVO("BBBBBBBB", 20),
							new SampleVO("CCCCCCCC", 30) };
		
		log.info("\t + vo : {}",Arrays.toString(vo));
		
		return vo;
		
//		+ URI로 접속하면 아래와 같이 출력된다.
//		<SampleVOs>
//			<item>
//				<name>AAAAAAAA</name>
//				<age>10</age>
//			</item>
//			<item>
//				<name>BBBBBBBB</name>
//				<age>20</age>
//			</item>
//			<item>
//				<name>CCCCCCCC</name>
//				<age>30</age>
//			</item>
//		</SampleVOs>
		
	} // sendXML2
	
	// ===================================================================
	
	// + list 버전
	
	@GetMapping(path="/sendXML3", produces = MediaType.TEXT_XML_VALUE + "; charset=utf8")
	public List<SampleVO> sendXML3() {
		
		log.info("sendXML3() invoked.");
		
		List <SampleVO> vo = new ArrayList<>();
		
		vo.add(new SampleVO("AAAAAAAA", 50));
		vo.add(new SampleVO("BBBBBBBB", 60));
		vo.add(new SampleVO("CCCCCCCC", 70));
		
		log.info("\t + vo : {}", vo);
		return vo;
		
//		+ URI로 접속하면 아래와 같이 출력된다.
//		<List>
//			<item>
//				<name>AAAAAAAA</name>
//				<age>50</age>
//			</item>
//			<item>
//				<name>BBBBBBBB</name>
//				<age>60</age>
//			</item>
//			<item>
//				<name>CCCCCCCC</name>
//				<age>70</age>
//			</item>
//		</List>
		
	} // sendXML3
	
	// ===================================================================
	// 3. 순수한 데이터로 JSON 문서를 반환하는 헨들러 메소드
	// ===================================================================
	
	@GetMapping(path="/sendJSON", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf8")
	public SampleVO sendJSON() {
		
		log.info("sendJSON() invoked.");
		
		SampleVO vo = new SampleVO("JOJOJOJO", 30);
		log.info("\t + vo : {}", vo);
		
		return vo;
		
//		+ URI로 접속하면 아래와 같이 출력된다.
//		{
//		  "name": "JOJOJOJO",
//		  "age": 30
//		}
		
	} // sendJSON
	
	// ===================================================================
	// 4. 순수한 데이터로 XML or JSON 문서를 반환하는 헨들러 메소드
	// ===================================================================
	// + XML보다는 JSON이 더 가볍고 다루기 편하기에, JSON을 주로 사용한다.
	// + 2개를 동시에 하는 것을 불가능하다.
	// ===================================================================
	
	@GetMapping(
				path="/sendXMLOrJSON", 
				produces = { 
						MediaType.APPLICATION_JSON_VALUE + "; charset=utf8", 
						MediaType.APPLICATION_XML_VALUE + "; charset=utf8" })
	public SampleVO sendXMLOrJSON() {
		
		log.info("sendXMLOrJSON() invoked.");
		
		SampleVO vo = new SampleVO("JOJOJOJO", 30);
		log.info("\t + vo : {}", vo);
		
		return vo;
		
//		+ URI로 접속하면 아래와 같이 출력된다.
		
	} // sendXMLOrJSON
	
	// ===================================================================

} // end class
