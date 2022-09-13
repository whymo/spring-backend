package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebServlet("/EmpSelect2")
public class EmpSelectServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcUrl;
	private String driverClass;
	private String user;
	private String pass;
	
	private Connection conn;
	
	// =============================================================================
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		log.trace("init(config) invoked.");
		
		super.init(config);
		
		this.jdbcUrl = config.getInitParameter("jdbcUrl");
		this.driverClass = config.getInitParameter("driverClass");
		this.user = config.getInitParameter("user");
		this.pass = config.getInitParameter("pass");
		
		log.trace("\t + jdbcUrl : {}", jdbcUrl);
		log.trace("\t + driverClass : {}", driverClass);
		log.trace("\t + user : {}", user);
		log.trace("\t + pass : {}", pass);
		
		try {
			Class.forName(driverClass); // 드라이버클래스의 객체를 얻어오고 있다.
			
			Connection conn = DriverManager.getConnection(jdbcUrl,user,pass);
			log.debug("\t + conn : {}", conn);
			
			this.conn = conn;
		} catch (Exception e) {
			throw new ServletException(e);
		} // try - catch
		
	} // init
	
	// =============================================================================
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(rea, res) invoked.");
		
		// 응답문서 준비
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
		
		// =============================================================================
		// 		JDBC 연동
		// =============================================================================
		
		try {
			
			String sql = "SELECT empno, ename, sal, deptno FROM emp ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			try ( pstmt; rs; ){
				
				while(rs.next()) {
					
					String empno = rs.getString("empno");
					String ename = rs.getString("ename");
					int sal = rs.getInt("sal");
					String deptno = rs.getString("deptno");
					
					out.println(String.format("<p> %s, \t %s, \t %s, \t %s </p>", empno, ename, sal, deptno));
					
				} // while
				
			} // try - with - resources
			
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			out.println("</body></html>");
		} // try - catch
		
		out.flush();
		
	} // service
	
	// =============================================================================

	@Override
	public void destroy() {
		
		log.trace("destroy() invoked.");
		
		try { if( conn != null && !conn.isClosed()) conn.close(); }
		catch(SQLException e) { ;; }
				
	} // destroy
	
	// =============================================================================

} // end class
