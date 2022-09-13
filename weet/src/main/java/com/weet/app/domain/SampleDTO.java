package com.weet.app.domain;

import lombok.Data;

@Data
public class SampleDTO {
	
	// + DTO 클래스의 필드 타입 역시 VO 클래스와 마찬가지로
	// + 기본타입을 사용하지 않고, 참조타입으로 선언해야 한다.
	// + 자바빈즈 클래스 규약을 지키고 있다.
	
	private String name;
	private Integer age;

} // SampleDTO
