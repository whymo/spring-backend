package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl implements BoardService, InitializingBean, DisposableBean {
// + InitializingBean는 빈 객체가 만들어진 직후에, DisposableBean는 빈이 파괴되기 직전에
	
	// ===========================================================
	
	// @Setter(onMethod_= {@Autowired})
	@Autowired
	private BoardMapper mapper;
	
	// ===========================================================
	// + 전체 게시글 조회
	// ===========================================================

	@Override
	public List<BoardVO> getAllList() throws ServiceException {
		// + 핵심 비지니스 로직 : DB 게시판 테이블을 조회하여, 게시글 전체목록을 얻어내 Model로 반환
		// + 하지만, Model은 Controller에서만 Model을 생성할 수 있다.
		// + 그러기 위해서는 mapper을 빈으로 가져와야 한다.
		
		log.trace("getAllList() invoked.");
		
		try {
			
			Objects.requireNonNull(this.mapper);
			return this.mapper.selectAllList();
			
		} catch ( DAOException e ) {
			throw new ServiceException(e);
		} // try - catch

	} // getAllList
	
	// ===========================================================
	// + 특정 게시글 조회
	// ===========================================================
	
	@Override
	public BoardVO get(BoardDTO dto) throws ServiceException {
		
		log.trace("get() invoked.");
		
		try {
			return this.mapper.select(dto.getBno());
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try - catch
		
	} // get
	
	// ===========================================================
	// + 특정 게시글 삭제
	// ===========================================================
	
	@Override
	public Integer remove(BoardDTO dto) throws ServiceException {
		
		log.trace("getAllList() invoked.");
		
		try {
			return this.mapper.delete(dto.getBno());
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try - catch
		
	} // remove
	
	// ===========================================================
	// + 게시물 추가 ( bno도 지정하는 버전 )
	// ===========================================================
	
	@Override
	public Integer add(BoardDTO dto) throws ServiceException {
		
		log.trace("getAllList() invoked.");
		
		try {
			return this.mapper.insert(dto);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try - catch
		
	} // add
	
	// ===========================================================
	// + 게시물 추가 2 ( bno를 시퀀스가 해주는 버전 ) (***)
	// ===========================================================

	@Override
	public boolean addAuto(BoardDTO dto) throws ServiceException {
		
		log.trace("getAllList() invoked.");

		try {
			return this.mapper.insertSelectKey(dto) == 1;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try - catch
		
	} // assAuto
	
	// ===========================================================
	// + 게시물 수정
	// ===========================================================
	
	@Override
	public boolean update(BoardDTO dto) throws ServiceException {
		
		log.trace("update() invoked.");
		
		try {
			return this.mapper.update(dto) == 1;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try - catch
		
	} // update
	
	// ===========================================================
	// + destroy : 후처리
	// ===========================================================

	@Override
	public void destroy() throws Exception {
		log.trace("destroy() invoked.");
		
	} // DisposableBean : destroy ( 후처리 작업 )
	
	// ===========================================================
	// + afterPropertiesSet : 필드의 의존성 주입이 정삭적인지 체크
	// ===========================================================

	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet() invoked.");
		
		// + 의존성 주입 체크
		Objects.requireNonNull(this.mapper);
		log.trace("\t + this.mapper : {}", this.mapper);
		
	} // InitializingBean : afterPropertiesSet ( 전처리 작업)
	
	// ===========================================================

} // end class
