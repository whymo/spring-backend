package org.zerock.myapp.domain;

import lombok.Data;

// @Value
@Data // ( Genson 라이브러리는 기본 생성자를 요구한다. )
public class Foo {
	
	private Integer id;
	private String name;

} // end class
