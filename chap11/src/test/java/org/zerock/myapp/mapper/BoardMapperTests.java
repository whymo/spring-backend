package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Objects;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class BoardMapperTests {
	
	// =======================================================================
	
	// @Autowired
	@Setter(onMethod_= {@Autowired})
	private BoardMapper mapper;
	
	// =======================================================================
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.mapper);
		log.info("\t + 1. this.mapper : {}", this.mapper);
		// + 1. this.mapper : org.apache.ibatis.binding.MapperProxy@2616b618
		log.info("\t + 2. type : {}", this.mapper.getClass().getName());
		// + 2. type : com.sun.proxy.$Proxy48
		
	} // beforeAll
	
	// =======================================================================
	
	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. BoardMapper.getList() test")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void testGetList() throws DAOException {
		
		log.trace("testGetList() invoked.");
		
		List<BoardVO> list = this.mapper.selectAllList();
		// + 오라클 힌트로 내림차순하였기에, PK를 기준으로 내림차순 정렬된다.
		// + mapper에 있는 @Select가 실행된다.
		
		Objects.requireNonNull(list);
		list.forEach(log::info);
		
	} //testGetList
	
	// =======================================================================
	
	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. BoardMapper.testDelete() test")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void testDelete() throws DAOException {
			
		log.trace("testDelete() invoked.");
		
		int bno = 30;
		
		int affectedLines = this.mapper.delete(bno);
		log.info("\t + affectedLines : {}", affectedLines);
		assert affectedLines == 1;
		
	} //testDelete
	
	// =======================================================================
	
	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. BoardMapper.testSelect() test")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void testSelect() throws DAOException {
			
		log.trace("testSelect() invoked.");
		
		int bno = 45;
		
		BoardVO vo = this.mapper.select(bno);
		Objects.requireNonNull(vo);
		
		log.info("\t + vo : {}", vo);
		
	} //testSelect
	
	// =======================================================================
	
	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. BoardMapper.testInsert(BoardVO) test")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void testInsert() throws DAOException {
			
		log.trace("testInsert() invoked.");
		
		// 1. BoardVO 객체 생성 : SQL 바인드 변수들에게 넘겨줄 파라미터
		BoardDTO dto = new BoardDTO( 555, "TITLE_555","CONTENT_555","WRITER_555" );
		log.info("\t + 1. dto : {}", dto);
		
		// 2. Mapper Interface의 추상 메소드 호출
		int affectedLined = this.mapper.insert(dto);		
		log.info("\t + 2. affectedLined : {}", affectedLined);
		
		// 3. 입력 검증
		assert affectedLined == 1;
		
	} // testInsert
	
	// =======================================================================
	
	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. BoardMapper.testInsertSelectKey(BoardVO) test")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void testInsertSelectKey() throws DAOException {
			
		log.trace("testInsertSelectKey() invoked.");
		
		// 1. BoardVO 객체 생성 : SQL 바인드 변수들에게 넘겨줄 파라미터
		// + tbl_board 테이블에서 bno는 null이거나 값을 넣지 않으면 시퀀스를 통해서 자동생성되고 있는데,
		// + 이때 시퀀스에서 값을 얻어내서 bno로 대입하기 위해서는
		// + BoradMapper.xml파일에서 selectkey 태그 내에서 시퀀스를 지정해줘서 값을 미리 얻어내야 한다.(***)
		BoardDTO vo = new BoardDTO ( null, "TITLE_555","CONTENT_555","WRITER_555");
		log.info("\t + 1. vo : {}", vo);
		
		// 2. Mapper Interface의 추상 메소드 호출
		int affectedLined = this.mapper.insertSelectKey(vo);		
		log.info("\t + 2. affectedLined : {}", affectedLined);
		log.info("\t + 3. after method invoked vo : {}", vo);
		
		// 3. 입력 검증
		assert affectedLined == 1;
		
	} // testInsertSelectKey
	
	// =======================================================================
	
	// @Disabled
	@Test
	@Order(6)
	@DisplayName("6. BoardMapper.testupdate(BoardVO) test")
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void testupdate() throws DAOException {
			
		log.trace("testupdate() invoked.");
		
		// 1. BoardVO 객체 생성 : SQL 바인드 변수들에게 넘겨줄 파라미터
		BoardDTO vo = new BoardDTO(555, "TITLE_MODIFIED","CONTENT_MODIFIED","WRITER_MODIFIED");
		log.info("\t + 1. vo : {}", vo);
		
		// 2. Mapper Interface의 추상 메소드 호출
		int affectedLined = this.mapper.update(vo);		
		log.info("\t + 2. affectedLined : {}", affectedLined);
		
		// 3. 입력 검증
		assert affectedLined == 1;
		
	} // testInsert

} // end class
