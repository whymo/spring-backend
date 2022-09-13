package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;


@Value
public class BoardVO {
	
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Timestamp insertTs;
	private Timestamp updateTs;

} // end class
