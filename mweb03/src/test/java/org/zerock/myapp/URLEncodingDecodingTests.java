package org.zerock.myapp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGenerator.Standard;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class URLEncodingDecodingTests {
	
	@Test
	@Order(1)
	@DisplayName("testURLEncoder")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testURLEncoder() throws UnsupportedEncodingException {
		
		log.trace("testURLEncoder() invoked.");
		
		String str1 = "한글"; // 한글은 utf8로 인코딩된다.
		String str2 = "ABCDEFG1234567"; // 영어와 숫자는 인코딩해도 변함이 없다.
		String str3 = "ABCDEFG 1234567"; // 공백은 +로 바뀐다.
		
		// 인코딩하기
		String urlencodedStr = URLEncoder.encode(str1, "utf8");
		log.info("\t + urlencodedStr : {}", urlencodedStr);
		
		// 디코딩하기
		// String urldecoedeStr = URLDecoder.decode(urlencodedStr, StandardCharsets.UTF_8);
		String urldecoedeStr = URLDecoder.decode(urlencodedStr, "utf8");
		log.info("\t + urldecoedeStr : {}", urldecoedeStr);
		
	}// testURLEncoder

} // end class
