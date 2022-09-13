package org.zerock.myapp.gson;

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

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GsonTests {
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("1. testSerialize")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testSerialize() {
		
		log.trace("testSerialize() invoked.");
		
		// + Java 객체 -> JSON 문자열로 변환 ( Serialize )
		
		Bar obj = new Bar(100, "pyramid");
		log.info("\t + obj : {}", obj);
		// + obj : Foo(id=100, name=pyramid)
		
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		log.info("\t + Java - > json : {}",json);
		// + Java - > json : {"id":100,"name":"pyramid"}
		// + 이처럼 Java의 필드명이 키가 된다.
		
		// + Serialize는 gson.toJson(object)처럼 to로 시작하고
		// + deserialize는 fromJson처럼 from으로 시작한다.
		
	} // testSerialize
	
	// ===================================================
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("2. testDeserialize")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testDeSerialize() {
		
		log.trace("testDeSerialize() invoked.");
		
		// + JSON 문자열 -> Java 객체로 변환 ( Deserialize )
		
		String json = "{\"id\":100,\"name\":\"pyramid\"}"; // OK ( 자바는 ""만 문자열로 받기에, 이클립스에서 자동으로 변환 )
		// String json = "{ 'id' :100, 'name' : 'pyramid' }"; // OK ( ''로 문자열을 받아도 잘 역변환되고 있다. )
		
		Gson gson = new Gson();
		Foo obj = gson.<Foo>fromJson(json, Foo.class); // OK
		Bar obj2 = gson.<Bar>fromJson(json, Bar.class); // OK ( Bar도 Foo와 같은 필드를 가지고 있기에, OK! )
		// + .fromJson(String json, Clazz 객체)
		
		log.info("\t + obj : {}", obj);
		// + obj : Foo(id=100, name=pyramid)
		log.info("\t + obj.getId : {}", obj.getId());
		// + obj.getId : 100
		log.info("\t + obj.getName : {}", obj.getName());
		// + obj.getName : pyramid
		
	} // testDeSerialize
	
	// ===================================================

} // end class
