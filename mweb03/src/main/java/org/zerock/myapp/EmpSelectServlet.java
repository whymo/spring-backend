package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
@WebServlet("/EmpSelect")
public class EmpSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(rea, res) invoked.");
		
		// req.setCharacterEncoding("UTF-8"); // MyFilter가 대신 수행시켜 주고 있다.
		
		// 응답문서 준비
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
		
		// =============================================================================
		// 		JDBC 연동
		// =============================================================================
		
		String jdbcUrl = "jdbc:oracle:thin:@db202204131245_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP";
		String driverClass = "oracle.jdbc.OracleDriver";
		String user = "scott";
		String pass = "Oracle87761226";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			// WAS 안에는 JVM의 표준 클래스로더를 대체하는 Change-aware Classloader이 구현되어 있다.
			// 그렇기 때문에 JDBCDriver Class를 직접 로딩해줘야 하기에, Class.forName을 넣어줘야 한다. (***)
			Class.forName(driverClass); // 드라이버클래스의 객체를 얻어오고 있다.
			
			conn = DriverManager.getConnection(jdbcUrl,user,pass);					// Connection 생성
			log.debug("\t + conn : {}", conn);
			
			String sql = "SELECT empno, ename, sal, deptno FROM emp ";				// SQL 준비
			pstmt = conn.prepareStatement(sql);										// Prepared SQL 준비
			
			rs = pstmt.executeQuery();												// SQL 수행
			
			while(rs.next()) {														// 결과셋에서 데이터 추출
				
				String empno = rs.getString("empno");
				String ename = rs.getString("ename");
				int sal = rs.getInt("sal");
				String deptno = rs.getString("deptno");
				
				out.println(String.format("<p> %s, \t %s, \t %s, \t %s </p>", empno, ename, sal, deptno));
				
			} // while
			
			// 자원해제
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			out.println("</body></html>");
		} // try - catch
		
	} // service

} // end class
