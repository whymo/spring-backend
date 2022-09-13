package org.zerock.myapp.domain;

import lombok.Data;


@Data
public class Person {
	
	private String name;
	private Integer age;
	private Double weight;
	private Double height;
	
	private Foo foo;

} // end class
