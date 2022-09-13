package org.zerock.myapp.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Data

@Component
// @Component는 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 Annotation이다.
// @Component는 빈 컨테이너에 들어가는 구성요소라는 의미이다.
// @Component가 붙으면 빈 객체로 빈 컨테이너에 들어가게 된다.
// 이후에 root-context에 빈 객체를 등록해 줘야 한다. (***)

// 사실 ()안에 지정해 주지 않고 @Component만 작성해도 된다.
// () 안에 이름을 작성해 주면 논리적인 이름을 지정해주게 된다.
// 주로 관례에 따라서 클래스의 첫문자를 소문자로 바꿔 지정해주게 된다.
public class Restaurant {
	
	// =====================================================================================
	// 자바빈즈 클래스가 되기 위해서는
	// -	1 ) 모든 필드는 private이어야 한다. ( OOP의 은닉화 / 캡슐화 성질 ) -> 필수
	// -	2 ) 각 필드에 대해서 Getter / Setter 메소드를 가져야 한다. -> 필수
	// -	3 ) Default Constructor(매개변수가 없는 기본 생성자)를 가져야 한다. -> 필수
	// -	4 ) java.io.Serializable 태그 인터페이스를 implements(구현)해야 한다. -> 선택
	// =====================================================================================
	// + 다음과 같은 4가지를 지켜야 하지만,
	// + 레스토랑의 경우 의존성 객체를 주입받을 필드가 private로 되어있으며,
	// + @Data로 Getter / Setter 조건을 만족시켰고,
	// + @NoArgsConstructor로 Default Constructor를 자동 생성해주었기에 자바빈즈 클래스가 된다.
	// =====================================================================================
	
	// 의존성 객체를 주입받을 필드
	@Autowired
	private Chef chef; // spring Context에게 의존성 주입 시그널 전송

} // Restaurant
