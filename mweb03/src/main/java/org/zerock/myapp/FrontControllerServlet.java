package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.BizProcessException;
import org.zerock.myapp.service.DeleteService;
import org.zerock.myapp.service.InsertService;
import org.zerock.myapp.service.SelectService;
import org.zerock.myapp.service.Service;
import org.zerock.myapp.service.UnknownReqService;
import org.zerock.myapp.service.UpdateService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("*.do") // 다른 서블릿이 *.do로 매핑하고 있으면 오류발생할 수 있다.
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		// =======================================
			// 1. ContextPath와 URL/URI(요청)확인
		// =======================================
		
		String contextPath = req.getContextPath();
		String requestURI = req.getRequestURI();
		String requestURL = req.getRequestURL().toString();
		
		// =======================================
		
		log.info("\t + 0. contextPath : {}", contextPath); // /maven01 or /myapp
		
		// + contextPath가 그냥 /(루트)일 경우 빈문자열이기에, 출력되지 않는다.
		// + 즉 그냥 루트일 경우에는 substring할 필요 없이 URI를 사용하면 된다.
		
		// =======================================
		
		log.info("\t + 1. requestURI : {}", requestURI); // /*.do
		log.info("\t + 2. requestURL : {}", requestURL); // http://localhost:8080/*.do
		
		// 현재 모든 URL 중 .do로 끝나는 모든 요청을 여기에 집중시키고 있다.
		
		// =======================================
		
		// substring으로 URI에서 컨텍스트 페스를 제외한 문자열을 돌려준다.
		// String command = requestURI.substring(contextPath.length());
		
		String command = requestURI;
		log.info("\t + 3. command : {}", command); // /*.do
		
		// + command는 contextPath가 그냥 루트일 경우 URI와 동일하게 나온다.
		
		// =======================================
			// 2. 전송파라미터를 DTO객체로 수집 (*****) <----- 여기서부터 시작해도 된다.
		// =======================================
		
		// req.setCharacterEncoding("UTF-8"); 은 이미 필터에서 수행되었다.
		
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String sal = req.getParameter("sal");
		String deptno = req.getParameter("deptno");
		
		// 전송파라미터를 하나씩 계속 전달하면, 너무 비효율적이기에 DTO 객체에 저장해서 사용한다.
		// 수집된 각 전송 파라미터를 DTO 객체에 저장 ( 이제, 이 객체를 전달하면 된다. )
		// DTO 객체는 웹3계층(표현/비지니스/영속 계층)의 뒤로 전달되면서 사용된다.
		EmpDTO dto = new EmpDTO();
		
		// null이 아니고 빈문자열도 아니어야 한다.
		if( empno != null && !"".equals(empno) ) { dto.setEmpno(Integer.parseInt(empno)); } // if - empno
		
		if( ename != null && !"".equals(ename) ) { dto.setEname(ename); } // if - ename
		
		if( sal != null && !"".equals(sal) ) { dto.setSal(Double.parseDouble(sal)); } // if - sal
		
		if( deptno != null && !"".equals(deptno) ) { dto.setDeptno(Integer.parseInt(deptno)); } // if - deptno
		
		// + 전송파라미터는 String으로 들어오기에, 필요시 형변환을 해줘야 한다.
		
		// =======================================
			// 3. 요청 URI에 따라, command(요청유형) 결정
		// =======================================
		
		// Request Scope 공유 데이터 영역에 DTO 객체를 속성으로 바인딩
		// * 주의 * : 모든 Service 객체의 비지니스 로직 수행에 필요한 전송파라미터를
	    // 전달해주는 DTO 객체를, 또 다른 공유데이터 영역인 "Request Scope"을 통해 전달
	    // ( accessed by HttpServletRequest )

		// -> 비지니스 로직 수행에 필요한 데이터를 가지고 있는 것이 DTO 객체이기 때문에 이를 넘겨줘야 한다.
		
		req.setAttribute(Service.DTO, dto);
		
		// req.getAttributeNames();
		// 이를 통해서 공유된 데이터의 이름을 파악할 수 있다.
		
		// =======================================
		// 4. command 에 따라, 적합한 비지니스 서비스 객체 선택 및 비지니스 로직 수행
		// =======================================
		
		try {
			
			//  command(요청유형)에 따라, 각 요청을 처리하는 서비스 객체의 생성 및 비지니스 로직 수행(execute 메소드)
	        // * 주의 * : 비지니스 로직 수행 결과 데이터는, 공유데이터 영역인 "Application Scope"(accessed by ServletContext)에 바인딩
			
			switch( command ) {
			
			// 아래의 모든 service 객체에 필요한 전송파라미터를 전달해주는
			// DTO 객체는 3번째 공유데이터 영역인 Request Scope을 통해 전달된다.
			
				case "/insert.do":
					log.info("\t + 4. insert request.");
					new InsertService().execute(req, res); // 객체 생성 후 메소드 실행
					break;
					
				case "/update.do":
					log.info("\t + 4. update request.");
					new UpdateService().execute(req, res); // 객체 생성 후 메소드 실행
					break;
					
				case "/delete.do":
					log.info("\t + 4. delete request.");
					new DeleteService().execute(req, res); // 객체 생성 후 메소드 실행
					break;
					
				case "/select.do":
					log.info("\t + 4. select request.");
					new SelectService().execute(req, res); // 객체 생성 후 메소드 실행
					break;
					
				default : // == if-else
					log.info("\t + 4. unknown request.");
					new UnknownReqService().execute(req, res); // 객체 생성 후 메소드 실행
					break;
		
			} // switch
			
		} catch(BizProcessException e) {
			;;
		} // try - catch
		
		// =======================================
			// 응답 문서 준비 --> /View로 이동
		// =======================================
		
//		res.setContentType("text/html; charset=utf8");
//		
//		@Cleanup
//		PrintWriter out = res.getWriter();
//		out.println("<html><head></head><body>");
//		
//		// 위의 각 command 별로 수행되는 Service 개체의 execute 메소드의 수행결과를 Application에서
//		// 얻어서, 응답문서를 만들어 준다!
//		
//		ServletContext sc = req.getServletContext();
//		Object bizResult = sc.getAttribute(Service.BIZ_RESULT);
//		
//		out.println("<p>" + bizResult + "</p>");
//		
//		out.println("</body></html>");
//		out.flush();
//		
//		// 응답문서 생성에서 사용했던, Application Scope에 공유된 서비스 객체의
//		// 비지니스 수행결과 데이터를 삭제 ( 속성 unbinding )
//		sc.removeAttribute(Service.BIZ_RESULT);
		
		// =======================================
			// MVC 패턴 적용
		// =======================================
		
		RequestDispatcher dis = req.getRequestDispatcher("/View");
		dis.forward(req, res);
		
		log.info("Forwarded request into /View");
		
	} // service

} // end class
