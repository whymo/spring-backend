package org.zerock.myapp.gson;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.zerock.myapp.domain.Ticket;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class GsonTests2 {
	
	// ========================================================================================================
	
	@Test
	public void testGson() {
		log.trace("testGson() invoked.");
		
		List<Person> list = new ArrayList<>();
		
		for ( int i = 0; i < 3; i++ ) {
			// + JSON 문자열로 변환할 자바객체 Person객체 생성
			Person person = Person.builder().
											age(29).
											name("YOYOYO").
											height(180.2).
											weight(60.3).
											isMale(true).
											hobbies( new String [] { "Movie", "Music", "Sleeping" }).
											build();
			list.add(person);
		} // for
		
		assertNotNull(list);
		
		// + 자바 객체 -> JSON으로 serialize(직렬화) 수행
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		log.info("\t + json : {}", json);
		// + json : [
		//			{"name":"YOYOYO","age":29,"height":180.2,"weight":60.3,"isMale":true,"hobbies":["Movie","Music","Sleeping"]},
		//			{"name":"YOYOYO","age":29,"height":180.2,"weight":60.3,"isMale":true,"hobbies":["Movie","Music","Sleeping"]},
		//			{"name":"YOYOYO","age":29,"height":180.2,"weight":60.3,"isMale":true,"hobbies":["Movie","Music","Sleeping"]}]
		
	} // testGson
	
	// ========================================================================================================
	
	@Test
	public void testTicket() {
		
		log.trace("testTicket() invoked.");
		
		Ticket ticket = new Ticket();
		ticket.setTno(1008);
		ticket.setGrade("A");
		ticket.setPrice(15300.0);
		
		log.info("\t + 1. ticket : {}", ticket);
		
		// + Ticket -> JSON으로 변환
		Gson gson = new Gson();
		
		String json = gson.toJson(ticket);
		log.info("\t + 2. json : {}", json);
		
	} // testTicket

} // end class
