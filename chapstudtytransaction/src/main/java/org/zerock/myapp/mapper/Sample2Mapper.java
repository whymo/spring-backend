package org.zerock.myapp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.zerock.myapp.exception.DAOException;

public interface Sample2Mapper {
	
	// ==========================================
	// 2. tbl_sample 테이블에 insert 수행 메소드
	// ==========================================
	@Insert("INSERT INTO tbl_sample2 (col) VALUES ( #{col} )")
	public abstract int insertCol( @Param("col") String data ) throws DAOException;

} // end interface
