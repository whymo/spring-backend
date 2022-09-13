package org.zerock.myapp.service;



public interface SampleService { // + Target
	
	// ==================================================================================
	// + 이 인터페이스를 구현할 클래스가 곧 Target 객체를 생성할 클래스가 된다. (***)
	// + -> SampleService 인터페이스가 Target이라는 의미이다.
	// + -> SampleService 내의 메소드인 doAdd가 JoinPoint라는 의미이다.
	// ==================================================================================
	
	// + JoinPoint
	public abstract Integer doAdd(String s1, String s2) throws Exception;
	
	public abstract String joinPoint(String s1, String s2) throws Exception;

} // end interface
