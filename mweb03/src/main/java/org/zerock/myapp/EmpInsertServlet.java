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

@WebServlet("/EmpInsert")
public class EmpInsertServlet extends HttpServlet { // 커넥션 풀 사용 ver.
	private static final long serialVersionUID = 1L;
	
	// =============================================================================
	
	// JNDI LOOKup을 통해 CP 주소 획득 및 CP 객체의 주소를 변수에 저장
	@Resource(name="jdbc/OracleCloudATPWithDriverSpy")
	private DataSource dataSource;
	
	// =============================================================================
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(rea, res) invoked.");
		
		// =============================================================================
		// 		전송파라미터( Request Parameters ) 얻기 - 문자열로 받아와진다.
		// =============================================================================
		
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String sal = req.getParameter("sal");
		String deptno = req.getParameter("deptno");
		
		// =============================================================================
		// 		응답화면 생성 및 전송
		// =============================================================================
		
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
			
			// 데이터베이스에 값을 넣는 sql문이다. ( DML )
			String sql = "INSERT INTO emp ( empno, ename, sal, deptno ) VALUES( ?, ?, ?, ? )";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// ?에 값 바인딩하기
			pstmt.setInt(1, Integer.parseInt(empno)); // ?의 경우 배열이 아니기에, 1번부터 시작한다.
			pstmt.setString(2, ename);
			pstmt.setDouble(3, Double.parseDouble(sal));
			pstmt.setInt(4, Integer.parseInt(deptno));
			
			// 쿼리를 실행해서 총 몇개가 영향을 받았는지 알려준다.
			int affectedLines = pstmt.executeUpdate();
			
			log.info("\t + affectedLines : {}", affectedLines);
			
			try ( conn; pstmt; ){
				
				// 응답문서로 출력
				out.println( String.format("<p>affectedLines : %s</p>", affectedLines));
				
			} // try - with - resources
			
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			out.println("</body></html>");
			out.flush();
		} // try - catch
		
	} // service
	
	// =============================================================================

} // end class
