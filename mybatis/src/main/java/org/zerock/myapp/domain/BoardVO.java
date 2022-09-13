package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value // VO 객체는 Value, DTO는 @data
public class BoardVO {
	
	// null도 포함되어야 하기 때문에 참조타입이어야 한다.
	// 기본타입(ex. int)은 참조타입(ex. Integer)과 다르게 NULL을 받아들일 수 없다.
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	
	private Timestamp insert_ts;
	private Timestamp update_ts;
	
	// Timestamp는 오류가 날 수 있는데, 그렇다면 Date를 사용하자
	// private Date INSERT_TS;
	// private Date UPDATE_TS;
	
	// private LocalDateTime INSERT_TS;
	// private LocalDateTime UPDATE_TS;

} // end class
