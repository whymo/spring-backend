package org.zerock.myapp.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.BizProcessException;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class InsertService implements Service {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws BizProcessException {
		
		log.trace("execute(req, res) invoked.");
		
		try {
			
			EmpDTO dto = (EmpDTO) req.getAttribute(Service.DTO);
			// Request Scope에서 "__DTO__"라는 이름을 가진 값을 EmpDTO 객체에 넣는다.
			// FontController에서 req.setAttribute(Service.DTO, dto)로 "__DTO__"라는 이름으로 공유데이터를 올려 놓았다.
			
			EmpDAO dao = new EmpDAO();
			int insertRows = dao.insert(dto);
			
			// req.getServletContext().setAttribute(Service.BIZ_RESULT, insertRows);
			// 위는 Application Scope에 올려 놓은 것이다.
			
			req.setAttribute(Service.BIZ_RESULT, insertRows); // Request Scope에 올려 놓았다.
			
			log.info("\t + insertRows : {}", insertRows);
			
		} catch(SQLException e) {
			throw new BizProcessException(e);
		} // try - catch 

	} // execute

} // end class
