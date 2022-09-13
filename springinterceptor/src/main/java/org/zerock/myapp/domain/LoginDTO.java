package org.zerock.myapp.domain;

import lombok.Data;


@Data
public class LoginDTO {
	private String userid;
	private String userpw;
	
	// 참조타입의 기본값은 null 임.
	// 따라서, 체크박스의 check여부의 기본값은 false 로 지정.
	private Boolean rememberMe;
	

} // end class
