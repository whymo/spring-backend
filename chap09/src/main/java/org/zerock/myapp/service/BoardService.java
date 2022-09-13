package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.ServiceException;

// + 롬복은 인터페이스에서 사용이 불가능하다.
// + 비지니스 로직은 Service에서 구현되어야 한다.
public interface BoardService {
	
	// + 1. 게시파 전체 목록을 조회하여 리스트로 반환해주는 메소드
	public abstract List <BoardVO> getAllList() throws ServiceException;
	
	// + 2. 게시판에서 특정 게시물 조회
	public abstract BoardVO get(Integer bno) throws ServiceException;
	
	// + 3. 게시판에서 게시물 삭제 요청 처리
	public abstract Integer remove(Integer bno) throws ServiceException;
	
	// + 4. 게시판에서 게시물 수정 요청 처리
	public abstract boolean update(BoardVO vo) throws ServiceException;
	
	// + 5. 게시판에서 새로운 게시물 추가 요청 처리
	public abstract Integer add(BoardVO vo) throws ServiceException;
	
	// + 6. 게시판에서 새로운 게시물 추가 요청 처리 2
	public abstract boolean addAuto(BoardVO vo) throws ServiceException;

} // end interface
