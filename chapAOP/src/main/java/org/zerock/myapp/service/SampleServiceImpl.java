package org.zerock.myapp.service;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service // + Service에는 @Service를 붙여야 한다!
public class SampleServiceImpl implements SampleService { // + Target

	@Override
	public Integer doAdd(String s1, String s2) throws Exception { // + JoinPoint
		
		log.trace("doAdd({}, {}) invoked.", s1, s2);
		
		return Integer.parseInt(s1) + Integer.parseInt(s2);
		// + Integer.parseInt는 int 타입으로 반환한다.
		
	} // doAdd

	@Override
	public String joinPoint(String s1, String s2) throws Exception {
		
		log.trace("joinPoint({}, {}) invoked.", s1, s2);
		
		return s1 + s2;
		
	} // joinPoint

} // end class
