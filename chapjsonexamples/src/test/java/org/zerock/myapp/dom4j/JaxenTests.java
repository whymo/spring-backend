package org.zerock.myapp.dom4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JaxenTests {
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("Step 1. readXML")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void readXML() throws DocumentException {
		
		log.trace("readXML() invoked.");
		
		// ========================================
		// 1. XML SAX Parser 생성 
		// ========================================
		
		SAXReader reader = new SAXReader();
		
		// ====================================================
		// 2. XML 파일 읽어오기 
		// + SAX Parser를 통해 파싱 수행 -> DOM tree 생성
		// ====================================================
		
		reader.setEncoding("utf8");
		// + 최근에는 기본으로 utf8로 지정되어있으나, 혹시 모르니 지정해줘야 한다.
		
		Document doc = reader.read("src/main/resources/Covid19DailyState.xml");
		// + 파일의 경로( = systemId )를 url형태로 작성해 준다.
		
		// ===================================================
		// 3. XPath로 우리가 얻고자 하는 노드들 한번에 선택
		// ===================================================
		
		String xpath = "/response/body/items/item";
		// + 노드를 경로와 같이 지정해 준다.
		
		@Cleanup("clear")
		List <Node> list = doc.selectNodes(xpath);
		log.info("\t + list size : {}", list.size() );
		
		// ==============================================================
		// 4. DOM의 순회 및 데이터 획득
		// + 찾고자 하는 데이터가 들어가 있는 노드를 지정한다.
		// ==============================================================
		
		for ( Node node : list ) {
			
			Element e = (Element) node;
			// + Element는 Node의 자식 타입이기에 형변환이 가능하다.(**)
			
			// + item 태그에서 필요한 데이터 추출 + String 형태로 변환 ( 메소드 체이닝 )
			String createDt = e.element("createDt").getStringValue();
			String deathCnt = e.element("deathCnt").getStringValue();
			String decideCnt = e.element("decideCnt").getStringValue();
			String seq = e.element("seq").getStringValue();
			String stateDt = e.element("stateDt").getStringValue();
			String stateTime = e.element("stateTime").getStringValue();
			String updateDt = e.element("updateDt").getStringValue();
			
			// + item에 attr 속성을 넣어서 출력할 수도 있다.
			String attr = e.attributeValue("attr");
			
			log.info("[{}]. createDt : {}, deathCnt : {}, decideCnt : {}, stateDt : {}, stateTime : {}, updateDt : {}, attr : {}",
					seq, createDt, deathCnt, decideCnt, stateDt, stateTime, updateDt, attr );
			
		} // for : 리스트에서 넣은 것을 출력
		
	} // readXML

} // end class
