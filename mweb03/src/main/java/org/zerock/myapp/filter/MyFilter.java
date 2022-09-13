package org.zerock.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import lombok.extern.log4j.Log4j2;


@Log4j2
//@NoArgsConstructor

@WebFilter("/*") // *는 All의 의미로 모든 요청에 수행하겠다는 의미이다.
public class MyFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	// =========================================================

	public MyFilter() {
        super();
        
        log.trace("Default Constructor invoked.");
    } // default ConStructor
	
	// =========================================================

    @Override
	public void destroy() {
    	log.trace("destroy() invoked.");
	} // destroy
    
    // =========================================================

    @Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
    	log.trace("doFilter(req, res, chain) invoked.");
    	
		// 1. place your Pre-processing code here
    	log.info("1. Pre-processing ......"); // 사전 처리
    	req.setCharacterEncoding("UTF-8");

		// pass the request along the filter chain
		chain.doFilter(req, res);
		
		// 2. place your Post-processing code here
		log.info("2. Post-processing ......"); // 사후 처리
		res.setContentType("text/html; charset=utf8");
		
		// 한글이 깨지지 않도록, 필터에서 사전 사후 처리에 등록해 더 이상 반복코딩이 없도록 하였다.
		
	} // doFilter(req, res, chain) : service와 같은 기능
    
    // =========================================================

	public void init(FilterConfig fConfig) throws ServletException {
		
		log.trace("init(fConfig) invoked.");
		
	} // init(fConfig)
	
	// =========================================================

} // end class
