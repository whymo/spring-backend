package org.zerock.myapp.domain;

import lombok.Value;

@Value // VO는 @Value, DTO는 @Data
public class SampleVO {
	
	private String name;
	private Integer age;

} // end class
