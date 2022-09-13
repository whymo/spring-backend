package org.zerock.myapp.exception;


// + 비지니스 계층의 모든 종류의 Service 구현에서 발생하는 예외를 의미
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	} // constructor
	
	public ServiceException(Exception e) {
		super(e);
	} //constructor

} // end class
