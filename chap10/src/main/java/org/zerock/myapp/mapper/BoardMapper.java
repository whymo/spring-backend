package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.DAOException;

// + 이 자바 인터페이스가 영속성 계층의 DAO 역할을 할
// + 마이바티스의 Mapper Interface로서의 역할을 하도록 구현
// + Mapper에서 CRUD하게 해준다. (***)
public interface BoardMapper {
	
	// ====================================================================
	// + 1. 게시물 전체 목록 출력하기
	// ====================================================================
	// + /*+ index_desc( 테이블명 indexName ) */는 오라클 힌트로 원래는 indexName을 지정해줘야 하지만,
	// + 지정해주지 않으면 테이블(tbl_board)의 PK를 기준으로 desc 내림차순으로 정렬해준다.
	// + INDEX (INDEX_ASC) : 오름차순 정렬, INDEX_DESC : 내림차순 정렬
	// + 같은 기능으로 ORDER BY가 있지만, 시스템에 부담이 되는 기능이기에 사용을 지양해줘야 한다.
	// ====================================================================
	
	@Select("SELECT /*+ index_desc(tbl_board) */ * FROM tbl_board WHERE bno > 0")
	public abstract List<BoardVO> selectAllList() throws DAOException;
	
	
	// ====================================================================
	// + 2. 새로운 게시물 등록 -> Mapper XMl (***)
	// ====================================================================
	// + 게시물 등록에 필요한 데이터가 VO 객체로 들어왔기 때문에,
	// + @Param(바인드 변수명) 어노테이션과 @Insert(SQL문) 어노테이션으로 처리가 힘들다.
	// + 그렇기에 Mapper XML 파일에 SQL을 등록해서 처리하는 것이 좋다.
	// ====================================================================
	// + MyBatis 프레임 워크의 Mapper SQL의 자동실행규칙 :
	// ====================================================================
	// + 1 ) Mapper Interface가 소속된 패키지와 동일한 폴더구조를 CLASSPATH 아래에 만들어라
	// + CLASSPATH = src/main/java --> /WEB-INF/classes/ ( 이 폴더가 CLASSPATH의 시작점이다. )
	// + CLASSPATH = src/main/resources --> /WEB-INF/classes/ ( 이 폴더가 CLASSPATH의 시작점이다. )
	// + --> ex. src/main/resources/org/zerock/myapp/mapper
	// ====================================================================
	// + 2 ) 위에서 만든 폴더구조에서 Mapper Interface의 타입명과 동일한 이름으로 Mapper XML 파일 생성
	// + ex. 이 경우 BoardMapper라는 이름의 Mapper Interface이기에, BoardMapper.xml파일을 만들어야 한다.
	// ====================================================================
	// + 3 ) Mapper XML 파일에서 namespace의 이름을 Mapper Interface의 FQCN으로 지정한다.
	// + ex. 이 경우에는 namespace에 org.zerock.myapp.mapper.BoardMapper을 지정한다.
	// ====================================================================
	// + 4 ) SQL문장을 저장할 태그의 id(sqlId)값을 Mapper Interface 내에서 호출할 추상메소드의 이름과 동일하게 한다.
	// + ex. 이 경우에는 추상메소드의 이름이 insert이기에 id에 insert를 지정해 준다.
	// ====================================================================
	
	// @Insert("INSERT INTO tbl_board (bno, title, content, writer) VALUES ( ?, ?, ?, ? )")
	public abstract Integer insert(BoardDTO dto) throws DAOException; // insert
	
	public abstract Integer insertSelectKey(BoardDTO dto) throws DAOException;
	
	
	// ====================================================================
	// + 3. 게시물 삭제 -> @Delete + @Param ( #{ 바인드변수명 } )
	// ====================================================================
	// + Integer bno -> @Param("bno") -> #{bno}순으로 값이 전달된다.
	// ====================================================================
	
	@Delete("DELETE FROM tbl_board WHERE bno = #{bno}")
	public abstract Integer delete(@Param("bno")Integer bno) throws DAOException;
	
	
	// ====================================================================
	// + 4. 게시물 수정 -> Mapper XMl (***)
	// ====================================================================
	
	 public abstract Integer update(BoardDTO dto) throws DAOException;
	 
	 
	// ====================================================================
	// + 5. 특정한 게시물 출력 ( SELECT )
	// ====================================================================
	// + 칼럼의 순서는 VO 객체의 순서를 따라야 한다. (***)
	// + 그렇기에 VO는 테이블의 스키마를 보고 그대로 따라하는 것이 좋다. 
	// ====================================================================
	 
	 @Select("SELECT bno, title, content, writer FROM tbl_board WHERE bno = #{bno}")
	 public abstract BoardVO select(@Param("bno") Integer bno) throws DAOException;

	 
} // end interface
