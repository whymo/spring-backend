package org.zerock.myapp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// JUNIT 5 버전
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/**/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardServiceTests {
	
	// =====================================================
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	// =====================================================
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		Objects.requireNonNull(this.service);
		log.trace("\t + this.service : {}", this.service);
	} // beforeAll
	
	// =====================================================
	// + 1. 게시물 전체 조회
	// =====================================================
	
	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. test getAllList")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testgetAllList() throws ServiceException {
		
		log.trace("testgetAllList() invoked.");
		
		this.service.getAllList().forEach(log::info);
		
	} // testgetAllList
	
	// =====================================================
	// + 2. 특정 게시글 조회
	// =====================================================
	
	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. test Get")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testGet() throws ServiceException {
		
		log.trace("testGet() invoked.");
		
		int bno = 77;
		
		BoardVO vo = this.service.get(bno);
		assertNotNull(vo);
		
		log.info("\t + vo : {}", vo);
		
	} // testGet
	
	// =====================================================
	// + 3. 특정 게시물 삭제
	// =====================================================
	
	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. test Remove")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testRemove() throws ServiceException {
		
		log.trace("testRemove() invoked.");
		
		int bno = 7;
		int affectedLine = this.service.remove(bno);
		
		log.info("\t + affectedLine : {}", affectedLine);
		
	} // testRemove
	
	// =====================================================
	// + 4. 게시글 추가 1
	// =====================================================
	
	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. test add")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testAdd() throws ServiceException {
		
		log.trace("testAdd() invoked.");
		
		BoardVO vo = new BoardVO ( 678, "WOW", "WOWOWOW", "WOWW");
		
		int affectedLine = this.service.add(vo);
		log.info("\t + affectedLine : {}", affectedLine);
		
	} // testAdd
	
	// =====================================================
	// + 5. 게시글 추가 2 (****)
	// =====================================================
	
	// @Disabled
	@Test
	@Order(5)
	@DisplayName("5. test AddAuto")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testAddAuto() throws ServiceException {
		
		log.trace("testAddAuto() invoked.");
		
		BoardVO vo = new BoardVO ( null, "AAAAAA", "BBBBB", "CCCC");
		
		if ( this.service.addAuto(vo) ) {
			log.info("\t + New Board Registered.");
			log.info("\t + vo : {}", vo);
		} else {
			log.info("\t + NO Board Registered.");
		} // if - else
		
	} // testAddAuto
	
	// =====================================================
	// + 6. 게시글 수정 (****)
	// =====================================================
	
	// @Disabled
	@Test
	@Order(6)
	@DisplayName("6. test Update")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testUpdate() throws ServiceException {
		
		log.trace("testUpdate() invoked.");
		
		BoardVO vo = new BoardVO ( 10, "AAAAAA", "BBBBB", "CCCC");
		
		if ( this.service.update(vo) ) {
			log.info("\t + Board Update!!!");
			log.info("\t + vo : {}", vo);
		} else {
			log.info("\t + NO Update.");
		} // if - else
		
	} // testUpdate

} // end class
