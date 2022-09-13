package org.zerock.myapp.mapper;

import lombok.Value;


@Value
public class MemberVO {
	
	private String userid;
	private String userpw;
	private String username;
	private Character enabled;

} // end class
