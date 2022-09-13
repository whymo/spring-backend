package org.zerock.myapp.gson;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class GsonTests {
	
	@Test
	public void testGson() {
		
		log.trace("testGson() invoked.");
		
		// + JSON 문자열로 변환할 자바객체 Person객체 생성
		// + 변환할 객체의 필드를 모두 사용할 필요는 없기에, 필요 부분만 넣어도 된다.
		Person person = Person.builder().
										age(29).
										name("YOYOYO").
										height(180.2).
										weight(60.3).
										isMale(true).
										hobbies( new String [] { "Movie", "Music", "Sleeping" }).
										build();
		
		assertNotNull(person);
		
		// + 자바 객체 -> JSON으로 serialize(직렬화) 수행
		Gson gson = new Gson();
		String json = gson.toJson(person);
		
		log.info("\t + json : {}", json);
		// + json : {"name":"YOYOYO","age":29,"height":180.2,"weight":60.3,"isMale":true,"hobbies":["Movie","Music","Sleeping"]}
		// + JSON은 위와 같이 필드의 이름을 모두 문자열로 표시한다.
		// + 또한 배열이 {}에서 []로 바뀐다.
		
	} // testGson

} // end class


@Builder
class Person {
	
	private String name;
	private int age;
	private double height;
	private double weight;
	private boolean isMale;
	private String[] hobbies;
	
} // Person
