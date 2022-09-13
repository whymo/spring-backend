package org.zerock.myapp.domain;

import lombok.Value;

@Value // VO는 @Value, DTO는 @Data
public class Sample2VO {
	
//	private String name;
//	private Integer age;
	private Double height;
	private Double weight;

} // end class
