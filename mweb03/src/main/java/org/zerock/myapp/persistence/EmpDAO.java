package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class EmpDAO { // POJO : Plain Old Java Object
	
	// 서블릿이 아니면, 자동으로 JNDI Lookup할 수 없다. -> 수동으로 해야 한다.
	// @Resource(name="jdbc/OracleCloudATPWithDriverSpy")
	
	// + Servlet 클래스의 내부에서는 위의 어노테이션으로 자원객체를 JNDI tree로부터
	// + 얻어낼 수 있지만, 그 외의 클래스에서는 불가능하다. (***)
	
	// DAO 정리 :
	// 데이터베이스의 data에 접근하기 위한 객체이다.
	// 데이터베이스로 접근하기 위한 로직과 비지니스 로직을 분리하기 위해 사용한다.
	
	// ====================================
	
	private static DataSource dataSource;
	
	// ====================================
	
	static { // JNDI Lookup을 해서, DataSource 객체의 주소를 획득 ( 수동 )
		
		try {
			
			Context ctx = new InitialContext();
			
			Object obj = ctx.lookup("java:comp/env/jdbc/OracleCloudATPWithDriverSpy");
			dataSource = (DataSource) obj;
			
		} catch (NamingException e) { ;; } // try - catch
		
	} // static initializer : 원래는 생성자 안에서 해야 한다.
	
	// ====================================
	
	public List<EmpVO> selectAll() throws SQLException {
		
		// selectAll() : 해당 테이터를 모두 선택해서 반환
		log.trace("select() invoked.");
		
		log.debug("\t + this.dataSource : {}", dataSource);
		
		List<EmpVO> list = new ArrayList<>();
		// EmpVO 객테타입을 보관하는 리스트(ArrayList)를 생성
		
		try {
			
			Connection conn = dataSource.getConnection();
			
			String sql = "SELECT empno, ename, sal, deptno FROM emp";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			try( conn; pstmt; rs; ) {
				
				while ( rs.next() ) {
					
					Integer empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					Double sal = rs.getDouble("sal");
					Integer deptno = rs.getInt("deptno");
					
					// VO(값 객체)는 1개의 테이블의 1개의 레코드를 읽기 전용으로 보관하는 객체이다.
					
					EmpVO vo = new EmpVO(empno, ename, sal, deptno);
					// EmpVO 객체로 하나의 레코드의 값을 가지고 있는 객체를 생성한다. 
					
					list.add(vo);
					// EmpVO 객체를 리스트에 저장한다.
					
				} // while : 레코드 하나씩 빼오기
				
			} // try - with - resources
			
		} catch(Exception e) {
			throw new SQLException(e);
		} // try - catch
		
		return list;
		// EmpVO 객체를 보관하고 있는 리스트를 반환한다.
		
	} // selectAll - 모든 레코드를 반환
	
	// ====================================
	
	public int insert(EmpDTO dto) throws SQLException { 
		
		log.trace("insert({}) invoked.", dto);
		
		try {
			
			Connection conn = dataSource.getConnection();
			
			String sql = "INSERT INTO emp ( empno, ename, sal, deptno ) VALUES ( ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setDouble(3, dto.getSal());
			pstmt.setInt(4, dto.getDeptno());
			
			
			// insert문은 영향을 받은 행의 수를 반환한다.
			int affectedLines = pstmt.executeUpdate();
			
			try( conn; pstmt; ) {
				
				return affectedLines;
				
			} // try - with - resources
			
		} catch(Exception e) {
			throw new SQLException(e);
		} // try - catch
		
	} // insert : 삽입문의 경우 삽입된 행의 수를 출력한다.
	
	// ====================================
	
	public int update(EmpDTO dto) throws SQLException { 
		
		log.trace("update({}) invoked.", dto);
		
		try {
			
			Connection conn = dataSource.getConnection();
			
			String sql = "UPDATE emp SET ename = ?, sal = ?, deptno = ? WHERE empno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getEname());
			pstmt.setDouble(2, dto.getSal());
			pstmt.setInt(3, dto.getDeptno());
			pstmt.setInt(4, dto.getEmpno());
			
			// insert문은 영향을 받은 행의 수를 반환한다.
			int affectedLines = pstmt.executeUpdate();
			
			try( conn; pstmt; ) {
				
				return affectedLines;
				
			} // try - with - resources
			
		} catch(Exception e) {
			throw new SQLException(e);
		} // try - catch
		
	} // update : 수정문의 경우 수정된 행의 수를 출력한다.
	
	// ====================================
	
	public int delete(EmpDTO dto) throws SQLException { 
		
		log.trace("delete({}) invoked.", dto);
		
		try {
			
			Connection conn = dataSource.getConnection();
			
			String sql = "DELETE FROM emp WHERE empno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getEmpno());
			
			// insert문은 영향을 받은 행의 수를 반환한다.
			int affectedLines = pstmt.executeUpdate();
			
			try( conn; pstmt; ) {
				
				return affectedLines;
				
			} // try - with - resources
			
		} catch(Exception e) {
			throw new SQLException(e);
		} // try - catch
		
	} // delete : 삭제문의 경우 삭제된 행의 수를 출력한다.
	
	// ====================================

} // end class
