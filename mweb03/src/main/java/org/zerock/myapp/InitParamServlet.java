package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(
		urlPatterns = { "/InitParamServlet" }, 
		initParams = { 
				@WebInitParam(name = "jdbcUrl", value = "jdbc:oracle:thin:@db202204131245_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP"), 
				@WebInitParam(name = "user", value = "hr"), 
				@WebInitParam(name = "pass", value = "Oracle87761226")
		})
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	
	//================================================================
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		log.trace("init({}) inovked.", config);
		
		String jdbcUrl = config.getInitParameter("jdbcUrl");
		String user = config.getInitParameter("user");
		String pass = config.getInitParameter("pass");
		
		log.trace("\t + jdbcUrl : {}, user : {}, pass : {}", jdbcUrl, user, pass);
		// jdbcUrl : jdbc:oracle:thin:@db202204131245_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP, user : hr, pass : Oracle87761226 출력
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			// JDBC Connection을 생성하여, 필드에 저장
			// => 이것이 우리가 init 메소드에서 할 초기화 작업이다.
			
			this.conn = DriverManager.getConnection(jdbcUrl, user, pass);
			log.info("\t + conn : {}", conn);
			
		}catch (Exception e) {
			throw new ServletException(e); // 예외가 발생하면 ServletException예외로 던져준다.
		} // try - catch
		
	} // init
	
	//================================================================

	@Override
	public void destroy() {
		log.trace("destory() invoked.");
		
		try { if ( this.conn != null ) this.conn.close(); }
		catch(Exception e) { ;; } // try - catch
		
	} // destroy
	
	//================================================================

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		try {
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT current_date FROM dual";
			ResultSet rs = stmt.executeQuery(sql);
			Timestamp ts = null;
			
			try ( stmt; rs; ) {
				rs.next();
				ts = rs.getTimestamp("current_date");
			} // try - with - resources
			
//			================================================
			
			// 1. 응답 데이터의 유형을 설정 (***)
			res.setContentType("text/html; charset=utf8");
			
			// 2. 문자기반
			@Cleanup
			PrintWriter out = res.getWriter();
			
			out.println("<h1>Init Timestamp</h1>");
			out.println("<p>Current time : " + ts + "</p>");
			out.flush();
			
		} catch(Exception e) {
			throw new IOException(e);
		} // try - catch
		
	} // service
	
	//================================================================

} // end class
