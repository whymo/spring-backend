package org.zerock.myapp.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.BizProcessException;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
// select.do 요청을 실제 처리할 비지니스 로직
public class SelectService implements Service {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws BizProcessException {
		
		log.trace("execute(req, res) invoked.");
		
		// =======================================
		// 실제 로직
		// =======================================
		
		try {
			
			// 영속계층에서 데이터를 가지고 오는 DAO객체 생성
			EmpDAO dao = new EmpDAO();
			
			// + dao의 selectAll메소드를 한 결과를 반환한다.
			// + 즉, sql문대로 실행한 결과를 List<EmpVO>타입으로 반환한다.
			
			// 비지니스 수행결과 데이터 발생
			List<EmpVO> list = dao.selectAll();
			
			// 공유데이터 영역에 비지니스 제이터를 속성 바인딩시킨다.
			// ServletContext sc = req.getServletContext();	// Application scope 객체
			// sc.setAttribute(Service.BIZ_RESULT, list); // 인터페이스에서 정적객체 가지고 오기
			// 위는 Application Scope에 올려 놓은 것이다.
			
			req.setAttribute(Service.BIZ_RESULT, list); // Request Scope에 올려 놓았다.
			
		} catch(SQLException e) {
			throw new BizProcessException(e);
		} // try - catch 
		
	} // execute

} // end class