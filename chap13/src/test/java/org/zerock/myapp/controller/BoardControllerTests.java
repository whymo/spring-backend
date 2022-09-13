package org.zerock.myapp.controller;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// JUNIT 5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { 
						"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// + Spring MVC Controller를 테스트 하기 위해서는
// + 반드시 아래의 어노테이션을 붙여야 한다.
@WebAppConfiguration // (****)
public class BoardControllerTests {
	
	@Autowired
	private WebApplicationContext ctx; 
	// + WebApplicationContext는 스프링 빈즈 컨테이너의 구현 객체이다. (***)
	// + 컨트롤러를 테스트 하기 위해서는 스프링 빈즈 컨테이너를 주입 받아야 한다.
	
	// ===========================================================================
	
	@BeforeAll
	public void beforeAll() {
		log.trace("beforeAll() invoked.");
	} // beforeAll
	
	// ===========================================================================
	// 1. 게시물 목록 전체 조회
	// ===========================================================================
	
	@Disabled
	@Test
	@Order(1)
	@DisplayName("1.testList")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	public void testList() throws Exception {
		log.trace("testList() invoked.");
		
		// ===========================================================================
		// + 표현계층의 사용자 정의 Controller에 대한 테스트를 수행 시, 핵심은 WAS 구동 없이 테스트 필요 :
		// + (1) @WebAppConfiguration 어노테이션
		// + (2) MockMVC 클래스( 가상의 웹 브라우저 ) 사용법 :
		// +	가. Spring Bean Container( WebApplicationContext를 주입받아야 한다. )
		// + 	나. '가'의 주입받은 객체를 이용해서, MockMvcBuilder 객체를 우선 생성
		// + 	다. '나'의 MockMvcBuilders란 Helper Class의 정적 메소드를 이용하여, MockMvcBuilder 객체를 얻는다.
		// + 	라. '다'에서 얻은 MockMvcBuilder.build() 메소드를 통해 MockMvc 객체를 얻는다.
		// + 	마. 'MockMvcBuilders' Helper Class의 정적 메소드를 이용해서, MockMvcRequestBuilder 객체를 얻는다.
		// + 	바. 'MockMvc'와 '마'에서 얻어낸, 'MockMvcRequestBuilder' 2개의 객체를 가지고 실제 가상의 요청(request)을 전송
		// +		- MockMvc.perform(MockMvcRequestBuilder) 메소드 호출로 수행
		// + 		- 테스트 대상인 Controller의 핸들러 메소드가 수행되면서,
		// + 		- 그 결과로 2가지의 정보를 얻게 된다. ( 1. Model 2. 뷰이름 )
		// + 	사. '바'에서 얻은 2가지 정보 중 필요로 하는 정보를 얻어내서 사용
		// ===========================================================================
		// + MockMvcBuilders는 MockMvcBuilder의 헬퍼 클래스이다.
		// + MockMvcBuilder는 MockMvc를 만들어 주는 건설사이다.
		// + MockMvcRequestBuilders.post로 하면 전송방식을 post라고 지정해줄 수 있다.
		// ===========================================================================
		
		// ===========================================================================
		// + 단계별로 연습 : 
		
		// 1 단계 : 스프링 Beans Container의 주소로 얻은 MockMvcBuilder의 정적메소드로 'MockMvcBuilder' 객체 획득
		MockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(ctx);
		
		// 2 단계 : 'MockMvcBuilder' 객체로부터 build해서, 지금 반드시 필요한 핵심객체인 'MockMvc' 객체를 획득
		MockMvc mock = builder.build();
		
		// 3 단계 : 실제 'MockMvc' 객체로 요청( request )을 만들어내려면, 'RequestBuilder' 객체가 우선 필요하다.
		// + 		'MockMvcRequestBuilders' Helper Class의 메소드를 이용하여 'RequestBuilder' 객체 획득
		// + 		( 전송방식 : GET , Request Mapping URI : /board/list인 요청을 만들어내는 RequestBuilder 객체 획득 )
		MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders.get("/board/list");
		
		// 4 단계 : 위 2단계에서 얻어낸 'MockMvc' 객체와 3단계에서 얻어낸 'RequestBuilder'객체, 2개를 이용하여 실제 요청 전송
		ResultActions resultActions = mock.perform(builder2);
		
		// 5 단계 : 호출된 Controller의 Handler 메소드에서 반환된 2개의 데이터 ( 뷰이름 + Model )를 추출
		// +		Controller Handler 메소드가 실행종료되어 2개의 데이터를 반환할 때까지 기다린다.
		MvcResult mvcResult = resultActions.andReturn();
		
		// 6 단계 : 'MvcResult'가 가지고 있는 2가지 데이터를 한꺼번에 'ModelAndView' 객체로 얻어냄
		ModelAndView resultData = mvcResult.getModelAndView();
		
		// 7 단계 : 'ModelAndView'에서 각각 'Model'과 'View' 객체를 얻어낼 수 있다.
		String viewName = resultData.getViewName();
		ModelMap model = resultData.getModelMap();
		HttpStatus statusCode = resultData.getStatus();
		
		// 8 단계 : 확인하기 ( 상태 코드는 아직 지정해주지 않았기에 null이다. )
		log.info("\t + viewName : {}", viewName);		// + viewName : board/list
		log.info("\t + model : {}", model);				// + model : {__LIST__=[BoardVO(....)]}
		log.info("\t + statusCode : {}", statusCode);	// + statusCode : null
		
		// ===========================================================================
		
		// + 축약형 ( 메소드 체이닝 ) (******)
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		// + 요청 ( 전송방식 : GET , Request Mapping URI : /board/list )
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/list");
		
		// + ModalMap의 실제 타입 => LinkedHashMap<String, ArrayList> (****)
		ModelMap modelMap = mockMvc.
									perform(reqBuilder).			// + 요청을 보내고
									andReturn().					// + 리턴을 기다리겠다. ( MvcResult )
									getModelAndView().				// + 모델과 뷰를 얻어내겠다. ( ModelAndView )
									getModelMap();					// + 그 중에서 모델만 달라 ( ModelMap )
		
		// -------------------------------------------------------------------------------------------
		
		modelMap.forEach((t, u) -> {
			log.info("\t + t : {}", t);
			log.info("\t + u : {}", u);
		}); // forEach
		
		// + 자원 해제 : Map Clear + Map객체 저장 참조변수 값을 null로 지정 -> 빠른 GC가 가능
		modelMap.clear();
		modelMap = null;
		
	} // testList
	
	// ===========================================================================
	// 2. 새로운 게시물 등록
	// ===========================================================================
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("2.testRegister")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	public void testRegister() throws Exception {
		
		log.trace("testRegister() invoked.");
		
		MockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = builder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/register");
		
		// + 필요한 전송파라미터를 설정 ( Lvalue 타입으로 구현타입을 지정해야 한다. )
		reqBuilder.param("bno", "54321");
		reqBuilder.param("title", "TITLE_NEW!!!!!!!!");
		reqBuilder.param("content", "CONTENT_NEW!!!!");
		reqBuilder.param("writer", "WRITER_NEW!!!!!");
		
		String viewName = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getViewName();
		
		log.info("\t + viewName : {}", viewName);
				
	} // testRegister
	
	// ===========================================================================
	// 3. 게시물 삭제 하기
	// ===========================================================================
	
	// @Disabled
	@Test
	@Order(3)
	@DisplayName("3.testDelete")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	public void testDelete() throws Exception {
		
		log.trace("testDelete() invoked.");
		
		MockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = builder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/remove");
		
		// + 필요한 전송파라미터를 설정 ( Lvalue 타입으로 구현타입을 지정해야 한다. )
		reqBuilder.param("bno", "54321");
		
		String viewName = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getViewName();
		
		log.info("\t + viewName : {}", viewName);
				
	} // testDelete
	
	// ===========================================================================
	// 4. 게시물 수정하기
	// ===========================================================================
	
	// @Disabled
	@Test
	@Order(4)
	@DisplayName("4. testUpdate")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	public void testUpdate() throws Exception {
		
		log.trace("testUpdate() invoked.");
		
		MockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = builder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.patch("/board/update");
		
		reqBuilder.param("bno", "898989");
		reqBuilder.param("title", "TITLE_NEW!!!!!!!!");
		reqBuilder.param("content", "CONTENT_NEW!!!!");
		reqBuilder.param("writer", "WRITER_NEW!!!!!");
		
		String viewName = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getViewName();
		
		log.info("\t + viewName : {}", viewName);
		
	} // testUpdate
	
	// ===========================================================================
	// 5. 페이징 처리 (****)
	// ===========================================================================
	
	// @Disabled
	@Test
	@Order(5)
	@DisplayName("5.testListPerPage")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	public void testListPerPage() throws Exception {
		
		log.trace("testRegister() invoked.");
		
		MockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = builder.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/list");
		
		// + 필요한 전송파라미터를 설정 ( Lvalue 타입으로 구현타입을 지정해야 한다. )
		reqBuilder.param("currPage", "7");
		reqBuilder.param("amount", "10");
		reqBuilder.param("pagesPerPage", "5");
		
		ModelAndView modelAndView = mockMvc.perform(reqBuilder).andReturn().getModelAndView();
		log.info("\t + 1. modelAndView : {}", modelAndView);
		
		String viewName = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getViewName();
		log.info("\t + 2. viewName : {}", viewName);
				
	} // testListPerPage

} // end class
