package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

@WebServlet("/EmpSelect4")
public class EmpSelectServlet4 extends HttpServlet { // JNDI LOOKup 직접 하는 방법
	private static final long serialVersionUID = 1L;
	
	// =============================================================================
	
	// JNDI LOOKup을 통해 CP 주소 획득 및 CP 객체의 주소를 변수에 저장
	private DataSource dataSource;
	
	// =============================================================================
	
	@Override
	public void init() throws ServletException {
		log.trace("init() invoked.");
		
		try {
			
			// JNDI Lookup
			
			// 1. JNDI tree 뿌리(root)에 접근할 수 있게 해주는 객체를 얻어야 한다.
			// 방법 : InitialContext 타입의 객체를 생성해야 한다.
			Context ctx = new InitialContext();
			
			// 2. Context 객체를 얻고 나서, Context 인터페이스에 정의된 메소드로 JNDI Lookup 수행
			// 특이사항 : JNDI tree에 공유자원객체를 바인딩할 때는, 이름 앞에 아래와 같은 prefix를 붙여야 한다.
			// ex. Prefix : "java:comp/env/" + 공유객체의 name으로 바인딩해야 한다.(***)
			Object obj = ctx.lookup("java:comp/env/jdbc/OracleCloudATP");
			
			// 3. null인지 검증
			Objects.requireNonNull(obj);
			
			// 4. 원래 타입으로 강제형변환
			this.dataSource = (DataSource) obj;
			
			log.debug("\t + this.dataSource : {}", this.dataSource);
			
		}catch(NamingException | NullPointerException e) {
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
			
			Connection conn = this.dataSource.getConnection();
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
