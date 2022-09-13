package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/EmpSelect3")
public class EmpSelectServlet3 extends HttpServlet { // 커넥션 풀 사용 ver.
	private static final long serialVersionUID = 1L;
	
	// =============================================================================
	
	// JNDI LOOKup을 통해 CP 주소 획득 및 CP 객체의 주소를 변수에 저장
	@Resource(name="jdbc/OracleCloudATP")
	private DataSource dataSource;
	
	// =============================================================================
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(rea, res) invoked.");
		
		log.debug("this.dataSource : {}", this.dataSource);
		
		// 응답문서 준비
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
		
		// =============================================================================
		// 		JDBC 연동
		// =============================================================================
		
		try {
			
			Connection conn = this.dataSource.getConnection();
			log.debug("\t + conn : {}", conn);
			// 커낵션 풀에서 얻은 커넥션의 정보가 나오기에, URL이 우리가 지정한 jdbcUrl이 나온다.
			
			String sql = "SELECT empno, ename, sal, deptno FROM emp ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try ( conn; pstmt; rs; ){
				
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

} // end class
