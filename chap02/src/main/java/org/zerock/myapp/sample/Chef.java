package org.zerock.myapp.sample;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@ToString
@Log4j2
@NoArgsConstructor

@Component("chef")
public class Chef implements InitializingBean, DisposableBean {
		
	// =====================================================================================
	// 자바빈즈 클래스가 되기 위해서는
	// -	1 ) 모든 필드는 private이어야 한다. ( OOP의 은닉화 / 캡슐화 성질 ) -> 필수
	// -	2 ) 각 필드에 대해서 Getter / Setter 메소드를 가져야 한다. -> 필수
	// -	3 ) Default Constructor(매개변수가 없는 기본 생성자)를 가져야 한다. -> 필수
	// -	4 ) java.io.Serializable 태그 인터페이스를 implements(구현)해야 한다. -> 선택
	// =====================================================================================
	// + 다음과 같은 4가지를 지켜야 하지만,
	// + 세프 클래스의 경우 필드가 존재하지 않기에 1번과 2번이 자동으로 통과되고
	// + @NoArgsConstructor로 Default Constructor를 자동 생성해주었기에 자바빈즈 클래스가 된다.
	// =====================================================================================
	
	@Override
	public void destroy() throws Exception {
		
		log.trace("destroy() invoked.");
		
	} // destroy : 파괴되기 직전에 수행

	@Override
	public void afterPropertiesSet() throws Exception {
		
		log.trace("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet : 빈 객체가 생성된 직후에 수행

} // Chef
