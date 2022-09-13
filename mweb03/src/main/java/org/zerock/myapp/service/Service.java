package org.zerock.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.exception.BizProcessException;


public interface Service {
	
	public static final String BIZ_RESULT = "__RESULT__";
	
	public static final String DTO = "__DTO__";
	
	// + 서비스에 따라서 되돌려주는 값이 달라질 수 있기에 Object타입으로 만들었다.
	
	public abstract void execute (HttpServletRequest req, HttpServletResponse res) throws BizProcessException;

} // end interface
