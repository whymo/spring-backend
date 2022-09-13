package org.zerock.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data // DTO는 @Data, VO는 @Value
public class BoardDTO {
	
	private Integer bno;
	private String title;
	private String content;
	private String writer;

} // end class
