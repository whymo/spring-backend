package org.zerock.myapp.domain;

import lombok.Data;


@Data
public class LoginDTO {
	
	private String userid;
	private String userpw;
	
	// + 참조타입의 기본값은 NULL이다.
	// + 따라서 체크박스의 check 여부의 기본값은 false로 지정하는 것이 좋다.
	private Boolean rememberMe = false;

} // end class
