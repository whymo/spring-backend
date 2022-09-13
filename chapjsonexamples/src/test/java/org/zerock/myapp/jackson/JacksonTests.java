package org.zerock.myapp.jackson;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.Bar;
import org.zerock.myapp.domain.Foo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JacksonTests {
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("1. testSerialize")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testSerialize() throws JsonProcessingException {
		
		log.trace("testSerialize() invoked.");
		
		// + 자바 객체를 JSON으로 변환
		
		Foo obj = new Foo();
		obj.setId(300);
		obj.setName("momomom");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json  = mapper.writeValueAsString(obj);
		// + Jackson에서는 writeValueAsString으로 Serialize한다.
		
		log.info("\t + 1. json : {}", json);
		// + 1. json : {"id":300,"name":"momomom"}
		
	} // testSerialize
	
	// ===================================================
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("2. testDeSerialize")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testDeSerialize() throws JsonMappingException, JsonProcessingException {
		
		log.trace("testDeSerialize() invoked.");
		
		// + JSON 문자열 -> Java 객체로 변환 ( Deserialize )
		
		String json = "{\"id\":300,\"name\":\"momomom\"}";
		// + GSON 라이브러리 외에서는 위와 같이 모두 탈출문자를 사용하여 표현해야 한다.
		
		log.info("\t + 2. json : {}", json);
		// + 2. json : {"id":300,"name":"momomom"}
		
		ObjectMapper mapper = new ObjectMapper();
		
		Foo obj = mapper.readValue(json, Foo.class);		// OK
		// Bar obj2 = mapper.readValue(json, Bar.class);	// xx : @Data가 아니어서 예외발생
		
		log.info("\t + 3. obj : {}", obj);
		// + 3. obj : Foo(id=300, name=momomom)
		
		log.info("\t + 4. obj.getId : {}", obj.getId());
		// + 4. obj.getId : 300
		
		log.info("\t + 5. obj.getName : {}", obj.getName());
		//  + 5. obj.getName : momomom
		
	} // testDeSerialize
	
	// ===================================================

} // end class
