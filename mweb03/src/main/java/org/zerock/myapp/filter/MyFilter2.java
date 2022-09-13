package org.zerock.myapp.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, servletNames = { "HelloServlet" }) // 특정한 서블릿에서만 작동
public class MyFilter2 extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;


	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		log.trace("doFilter(req, res, chain) invoked.");
    	
		// 1. place your Pre-processing code here
    	log.info("1. Pre-processing ......"); // 사전 처리

		// pass the request along the filter chain
		chain.doFilter(req, res);
		
		// 2. place your Post-processing code here
		log.info("2. Post-processing ......"); // 사후 처리
		
	} // doFilter(req, res, chain) : service와 같은 기능
	
	// MyFilter과 MyFilter2이 HelloServlet을 호출할때, 체인으로 실행되고 있다.
	// 다른 서블릿의 경우에는 MyFilter만 적용된다.

} // end class
