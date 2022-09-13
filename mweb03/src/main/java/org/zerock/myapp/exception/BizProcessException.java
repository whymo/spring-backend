package org.zerock.myapp.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BizProcessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BizProcessException(String msg) {
		
		super(msg); // 부모에게 메세지 날린다.
		
	} // constructor : 메세지
	
	public BizProcessException(Exception e) {
		
		super(e); // 예외를 부모에게 넘겨준다.
		
	} // constructor2 : 예외

} // end class
