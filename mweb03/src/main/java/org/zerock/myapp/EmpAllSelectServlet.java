package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/EmpAllSelect")
public class EmpAllSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// ========================================
		
		// 이 안에서 DAO 객체를 생성하여, 영속성 계층에서 데이터 획득
		
		EmpDAO dao = new EmpDAO();
		// dao가 호출하는 것은 서블릿이다.
		
		@Cleanup("clear")
		List<EmpVO> emps = null;
		
		try {
			
			emps = dao.selectAll();
			// List는 자원객체이기 때문에, 닫아줘야 한다.
			// @Cleanup은 자원객체에 맞게 ( )에 지정하여 닫아줘야 한다.
			
			emps.forEach(log::info);
			// log.debug("emps : {}", emps);
			
		} catch (SQLException e) {
			throw new IOException(e);
		} // try - catch
		
		// ========================================
		//		응답문서 준비
		// ========================================
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
		
		out.println("<ol>");
		
		emps.forEach( vo -> {
			
			String tag = String.format("<li> %s, %s, %s, %s </li>", vo.getEmpno(), vo.getEname(), vo.getSal(), vo.getDeptno() );
			
			out.println(tag);
			
		}); // for each
		
		out.println("</ol>");
		
		out.println("</body></html>");
		
		out.flush();
		
	} // service

} // end class
