package org.zerock.myapp.domain;

import lombok.Value;


// + getter / setter 롬복
//@Getter @Setter

// + 생성자 롬복
//@NoArgsConstructor
//@AllArgsConstructor

// + Data 롬복으로 getter, setter, 매개변수가 없는 생성자를 한번에 만들 수 있다. + @toString
// + @Data는 DTO 패턴에서 사용된다. (***)
//@Data

// + VO패턴에서는 읽기 전용이기에, @Value를 통해서 읽기는 가능하지만, 수정은 못하도록 해야 한다. 
// + @Value는 VO패턴에서 사용된다. (***)
@Value
public class EmpVO {
	
	private Integer empno;			// PK
	private String ename;			// NULL 허용
	private Double sal;				// NULL 허용
	private Integer deptno;			// NULL 허용
	
	// NULL을 포함해야 될 때에는 기본타입이 아닌 Wrapper(참조) 타입을 사용해야 한다. (***)

	// VO 정리 :
	// VO는 값 오브젝트(객체)로써 값을 위해 사용된다.
	// 오직 읽기 전용이며, DTO와 비슷하지만 setter가 없기에 값을 변경할 수 없다.
	
} // end class
