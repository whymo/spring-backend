package org.zerock.myapp.domain;

import lombok.Data;

@Data // @Data = DTO, @Value = VO
public class EmpDTO {
	
	// DTO는 화면에서 입력한 값을 뒤로 보내준다.
	
	// DTO 정리 :
	// DTO는 계층간 데이터를 교환하기 위해 사용하는 객체로.
	// 로직을 가지지 않는 순수한 데이터 객체이다.
	
	// 유저가 입력한 데이터를 DB에 넣는 과정 :
	// 유저가 자신의 브라우저에서 데이터를 입력하여 form에 있는 데이터를 DTO에 전송한다.
	// -> 해당 DTO를 받은 서버가 DAO를 이용해서 데이터베이스에 데이터로 넣는다.
	
	private Integer empno;			// PK
	private String ename;			// NULL 허용
	private Double sal;				// NULL 허용
	private Integer deptno;			// NULL 허용
	
	// + NULL이 들어갈수 있는 타입은 기본타입에 없기에, Wrapper 참조 타입으로 해줘야 한다.
	// + 0과 NULL은 다른 것이다.

} // end class
