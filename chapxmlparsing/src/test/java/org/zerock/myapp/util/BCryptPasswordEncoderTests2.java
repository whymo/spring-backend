package org.zerock.myapp.util;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BCryptPasswordEncoderTests2 {
	
	@Test
	@Order(1)
	@DisplayName("1. testBCryptPasswordEncoder")
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	void testBCryptPasswordEncoder() {
		
		log.trace("testBCryptPasswordEncoder() invoked.");
		
		String pw = "YOYOYO123456!!";			// 원래 암호
		String pw2 = "YOYOYO123456!!" + "__SALT__";			// 원래 암호 + 솔트
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// + 비밀번호를 해쉬 값으로 암호화해주는 클래스
		
		String cipherText = encoder.encode(pw); // cipherText == Hash Value ( 암호화한 값 )
		boolean isMatched = encoder.matches(pw, cipherText); // 비밀번호와 암호화된 값이 같은 값인지 확인 (***)
		
		Objects.requireNonNull(cipherText);
		log.info("\t + 1. cipherText : {}", cipherText);
		log.info("\t + 2. cipherText.length : {}", cipherText.length());
		
		log.info("\t + 3. isMatched : {}", isMatched);
		
	} // testBCryptPasswordEncoder

} // end class
