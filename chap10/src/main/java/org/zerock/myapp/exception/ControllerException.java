package org.zerock.myapp.exception;


// + 프레젠테이션 계층의 모든 종류의 Controller 구현에서 발생하는 예외를 의미
public class ControllerException extends Exception {
	private static final long serialVersionUID = 1L;

	public ControllerException(String message) {
		super(message);
	} // constructor
	
	public ControllerException(Exception e) {
		super(e);
	} //constructor

} // end class
