package org.zerock.myapp.exception;


public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ServiceException( String message ) {
		super(message);
	} // constructor
	
	public ServiceException( Exception e ) {
		super(e);
	} // constructor

} // end class
