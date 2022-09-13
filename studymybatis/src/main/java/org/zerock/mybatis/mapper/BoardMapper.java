package org.zerock.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.mybatis.domain.BoardVO;


public interface BoardMapper {
	
	
	@Select("SELECT /*+ index_desc(tbl_board) */ * FROM tbl_board")
	public abstract List<BoardVO> selectAllBoards();
	
	@Select("SELECT * FROM tbl_board WHERE bno = #{TheBno}")
	public abstract BoardVO selectBoard(@Param("TheBno") int bno);

} // end interface
