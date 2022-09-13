package org.zerock.myapp.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DynamicSQLWithConfigXmlTests {
	
	private SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	private SqlSessionFactory sqlSessionFactory;
	
//	=========================================================================================
	
	@BeforeAll
	void beforeAll() throws IOException {
		
		log.debug("beforeAll() invoked.");
		
		String mybatisConfigXml = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(mybatisConfigXml);
		
		try( is; ){
			this.sqlSessionFactory = builder.build(is);
			
			Objects.requireNonNull(this.sqlSessionFactory);
			log.info( "\t + sqlSessionFactory : {}", this.sqlSessionFactory );
		} // try - with - resources
		
	} // beforeAll()
	
//	=========================================================================================
//	<!-- 1. 게시판 검색어 조건에 맞게, 특정 게시글 번호로 검색해서 반환 1 ( 안전하지 않음 )  -->
//	 <select id="findBoardByBno" resultType="org.zerock.myapp.domain.BoardVO">
//	     SELECT bno, title, content, writer, insert_ts, update_ts 
//	     FROM tbl_board 
//	     WHERE 
//	
//	     <if test="bno != null">
//	         bno = #{bno}
//	     </if>
//     </select>
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(1)
	@DisplayName("1. findBoardByBno")
	@Timeout(value=5000, unit=TimeUnit.MILLISECONDS)
	public void findBoardByBno() {
		
		log.info("findBoardByBno() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + 1. sqlSession : {}", sqlSession);
		
		try ( sqlSession; ){
			
			Integer bno = 172;
			
			String namespace = "BoardMapper";
			String sqlId = "findBoardByBno";
			
			 BoardVO board = sqlSession.<BoardVO>selectOne(namespace+"."+sqlId, bno);
			 Objects.requireNonNull(board);
			 log.info("\t + 1. board : {}", board);
			
			// ============================================================================
				// List도 가능
			// List <BoardVO> boards = sqlSession.<BoardVO>selectList(namespace+"."+sqlId, bno);
			// Objects.requireNonNull(boards);
			// boards.forEach(log::info);
			// ============================================================================
			
		} // try - with - resources
		
	} // selectAllBoards
	
//	=========================================================================================
//	<!-- 2. 게시판 검색어 조건에 맞게, 특정 제목으로 검색해서 반환 2 (*****) -->
//    <select id="findBoardByTitle" resultType="org.zerock.myapp.domain.BoardVO">
//	
//        SELECT bno, title, content, writer, insert_ts, update_ts 
//        FROM tbl_board 
//
//        <where>
//
//            <if test="title != null">
//                title LIKE '%'||#{title}||'%'
//                <!-- 와일드 카드로 title로 검색 -->
//            </if>
//
//        </where>
//
//    </select>
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(2)
	@DisplayName("2. findBoardByTitle")
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void findBoardByTitle() {
		
		log.info("findBoardByTitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + 2. sqlSession : {}", sqlSession);
		
		try ( sqlSession; ) {
			
			// '%'||#{title}||'%'로 제목에 7이 들어간 것을 출력하게 지정
			String title = "7";
			
			// String title = null;
			// null을 넣으면 where절이 만들어지지 않기에 
			// SELECT bno, title, content, writer, insert_ts, update_ts FROM tbl_board만 수행되어
			// 모든 칼럼이 출력된다.
			
			String namesapce = "BoardMapper";
			String sqlId = "findBoardByTitle";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namesapce + "." + sqlId, title);
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // findBoardByTitle
	
	
//	=========================================================================================
//	<!-- 5 - 2. 게시판 검색어 조건에 맞게, 특정 제목으로 검색해서 반환 2 (****) -->
//    <select id="findBoardByTitle2" resultType="org.zerock.myapp.domain.BoardVO">
//        SELECT bno, title, content, writer, insert_ts, update_ts 
//        FROM tbl_board 
//
//        <where>
//
//            <if test="title != null">
//                title LIKE #{title}
//            </if>
//
//        </where>
//
//    </select>
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(3)
	@DisplayName("3. findBoardByTitle2")
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void findBoardByTitle2() {
		
		log.info("findBoardByTitle2() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + 3. sqlSession : {}", sqlSession);
		
		try ( sqlSession; ) {
			
			// 정확히 제목이 TITLE_7인 칼럼을 출력하게 지정
			// String title에 7을 넣으면, 제목이 7인 칼럼은 없기에 출력되지 않는다.
			String title = "TITLE_7";
			
			// String title = null;
			// null을 넣으면 where절이 만들어지지 않기에 
			// SELECT bno, title, content, writer, insert_ts, update_ts FROM tbl_board만 수행되어
			// 모든 칼럼이 출력된다.
			
			String namesapce = "BoardMapper";
			String sqlId = "findBoardByTitle2";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namesapce + "." + sqlId, title);
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // findBoardByTitle2
	
	
//	=========================================================================================
//	<!-- 6. 게시판 검색어 조건에 맞게, 특정 작가로 검색해서 반환 3  -->
//    <select id="findBoardByWriter" resultType="org.zerock.myapp.domain.BoardVO">
//        SELECT bno, title, content, writer, insert_ts, update_ts 
//        FROM tbl_board 
//
//        <trim prefix="WHERE" prefixOverrides="AND | OR">
//
//            <if test="writer != null">
//                writer LIKE '%'||#{witer}||'%'
//                <!-- 와일드 카드로 writer 검색 -->
//            </if>
//	
//        </trim>
//    </select>
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(4)
	@DisplayName("4. findBoardByWriter")
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void findBoardByWriter() {
		
		log.info("findBoardByWriter() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + 4. sqlSession : {}", sqlSession);
		
		try ( sqlSession; ) {
			
			// 작가 이름 중에 17이 포함되어있는 칼럼을 출력
			String writer = "17";
						
			String namesapce = "BoardMapper";
			String sqlId = "findBoardByWriter";
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namesapce + "." + sqlId, writer);
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // findBoardByWriter
	
//	=========================================================================================
//	 <!-- 7. 게시판 검색어 조건에 맞게, 특정 게시글 번호와 제목을 검색해서 반환 4  -->
//    <select id="findBoardByBnoAndWriter" resultType="org.zerock.myapp.domain.BoardVO">
//        SELECT bno, title, content, writer, insert_ts, update_ts 
//        FROM tbl_board 
//
//        <!-- 다중 조건식( 체크 조건이 여러개인 경우 )의 처리 -->
//        <!-- prefix는 실행될 쿼리의 <trim> 태그 안에 쿼리 가장 앞에 붙여준다. -->
//        <!-- prefixOverrides는 조건식이 2개 이상일 때 사용한다. -->
//        <!-- prefixOverrides는 실행될 쿼리의 <trim> 문 안에 쿼리 가장 앞에 해당하는 문자들이 있으면 자동으로 지워준다. -->
//        <trim prefix="WHERE" prefixOverrides="AND | OR">
//
//            <if test="bno != null">
//                bno = #{bno}
//            </if>
//
//            <if test="title != null">
//                AND title LIKE '%'||#{title}||'%'
//            </if>
//
//        </trim>
//
//    </select>
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(5)
	@DisplayName("5. findBoardByBnoAndtitle")
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void findBoardByBnoAndtitle() {
		
		log.info("findBoardByBnoAndtitle() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + 5. sqlSession : {}", sqlSession);
		
		try ( sqlSession; ) {
			
			Integer bno = 33;
			String title = "17";
						
			String namesapce = "BoardMapper";
			String sqlId = "findBoardByBnoAndtitle";
			
			Map<String, Object> params = new HashMap<>();
			params.put("bno", bno);
			params.put("title", title);
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namesapce + "." + sqlId, params);
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // findBoardByBnoAndWriter
	
//	=========================================================================================
//	 <!-- 8. 게시판 검색어 조건에 맞게, 특정 게시글 번호 또는 제목을 검색해서 반환 5 (*******)  -->
//    <select id="findBoardByBnoOrWriter" resultType="org.zerock.myapp.domain.BoardVO">
//        SELECT bno, title, content, writer, insert_ts, update_ts 
//        FROM tbl_board 
//
//        <!-- switch 문 -->
//        <where>
//
//            <choose>
//
//                <when test="bno != null">
//                    bno = #{bno}
//                </when>
//
//                <when test="title != null">
//                    OR title LIKE '%'||#{title}||'%'
//                </when>
//
//                <!-- 그렇지 않으면 컨탠츠 내용내에서 검색하라 -->
//                <!-- otherwise는 필수사항이 아닌 선택사항이다. -->
//                <otherwise>
//                    content LIKE %||#{content}||'%'
//                </otherwise>
//
//            </choose>
//
//        </where>
//
//    </select>
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(6)
	@DisplayName("6. findBoardByBnoOrWriter")
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void findBoardByBnoOrWriter() {
		
		log.info("findBoardByBnoOrWriter() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + 6. sqlSession : {}", sqlSession);
		
		try ( sqlSession; ) {
			
			Integer bno = 33;
			String title = "17";
						
			String namesapce = "BoardMapper";
			String sqlId = "findBoardByBnoOrWriter";
			
			Map<String, Object> params = new HashMap<>();
			params.put("bno", bno);
			params.put("title", title);
			
			// case - when의 경우에는 swith문과 동일하게
			// 같은 조건을 달성하는 when이 많을지라도 하나의 when만 실행시킨다.
			// 만약 title만 작성되어, 2번째 when이 실행되면 앞의 OR로 인해 문제가 발생한다고 걱정할 수 있지만,
			// 마이 바티스가 자동으로 지워주기에 괜찮은 부분이다.
			
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namesapce + "." + sqlId, params);
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // findBoardByBnoOrWriter
	
//	=========================================================================================
//	<!-- 9. 게시판 검색어 조건에 맞게, 특정 게시글 번호를 검색해서 반환 ( 검색어가 여러개 ) 6 (******)  -->
//    <select id="findBoardsBySomeBnos" resultType="org.zerock.myapp.domain.BoardVO">
//        SELECT bno, title, content, writer, insert_ts, update_ts 
//        FROM tbl_board 
//
//        <where>
//            
//            bno IN
//
//            <!-- ( bno1, bno2, bno3 ... ) -->
//            <!-- item은 list의 원소를 bno로 꺼내겠다는 의미이다. -->
//            <foreach collection="list" item="bno" index="index" open="(" close=")" separator=",">
//                #{bno}
//            </foreach>
//
//        </where>
//    </select>
//	=========================================================================================
	
	// @Disabled
	@Test
	@Order(7)
	@DisplayName("7. findBoardsBySomeBnos")
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void findBoardsBySomeBnos() {
		
		log.info("findBoardsBySomeBnos() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		log.info("\t + 7. sqlSession : {}", sqlSession);
		
		try ( sqlSession; ) {
			
			List<Integer> bnoList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
			log.info("\t + 7. bnoList : {}", bnoList);
						
			String namesapce = "BoardMapper";
			String sqlId = "findBoardsBySomeBnos";
						
			List<BoardVO> boards = sqlSession.<BoardVO>selectList(namesapce + "." + sqlId, bnoList);
			Objects.requireNonNull(boards);
			boards.forEach(log::info);
			
		} // try - with - resources
		
	} // findBoardsBySomeBnos
		
//	=========================================================================================
	
} // end class
