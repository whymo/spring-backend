package org.zerock.myapp.exception;


public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ControllerException( String message ) {
		super(message);
	} // constructor
	
	public ControllerException( Exception e ) {
		super(e);
	} // constructor

} // end class
