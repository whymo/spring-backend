package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardVO;

public interface BoardMapper {
	
	@Select("SELECT * FROM tbl_board")
	public abstract List<BoardVO> selectAllBoards();
	
	@Select("SELECT bno, title, content, writer, insert_ts, update_ts FROM tbl_board WHERE bno = #{bno}")
	public abstract BoardVO selectBoard(@Param("bno") int bno);
	
	// @Param은 int bno에 들어간 값을 #{bno}에 넣어준다는 의미이다. (*****)

} // end class
