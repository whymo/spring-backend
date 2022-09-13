package org.zerock.myapp.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@ToString
@Getter(AccessLevel.PUBLIC)
@Log4j2
@NoArgsConstructor

@Component
// @Component는 빈 컨테이너에 들어가는 구성요소라는 의미이다.
// 사실 ()안에 지정해 주지 않고 @Component만 작성해도 된다.'
public class Hotel {
	
	// =====================================================================================
	// 자바빈즈 클래스가 되기 위해서는
	// -	1 ) 모든 필드는 private이어야 한다. ( OOP의 은닉화 / 캡슐화 성질 ) -> 필수
	// -	2 ) 각 필드에 대해서 Getter / Setter 메소드를 가져야 한다. -> 필수
	// -	3 ) Default Constructor(매개변수가 없는 기본 생성자)를 가져야 한다. -> 필수
	// -	4 ) java.io.Serializable 태그 인터페이스를 implements(구현)해야 한다. -> 선택
	// =====================================================================================
	// + 다음과 같은 4가지를 지켜야 하지만,
	// + 호텔 클래스의 경우 의존성 객체를 주입받을 필드가 private로 되어있으며,
	// + @Getter와 @Setter로 Getter / Setter 조건을 만족시켰고,
	// + @NoArgsConstructor로 Default Constructor를 자동 생성해주었기에 자바빈즈 클래스가 된다.
	// =====================================================================================
	
	@Setter(onMethod_ = {@Autowired}) // spring Context에게 의존성 주입 시그널 전송
	// + 이는 Setter메소드 위에 @Autowired를 만들어달라는 의미이다.
	private Chef chef;
	
	// =====================================================================================
	// 위에서 @Setter(onMethod_ = {@Autowired})한 것은 아래의 코드와 같다.
	// + Setter 메소드를 만든 후, 위에 @Autowired한 것이다.
	// =====================================================================================
	
	// @Autowired
	// public void setChef(Chef chef) {
	// 	 log.info("setChef({}) invoked.", chef);
	//	 this.chef = chef;
	// } // setChef

} // Hotel
