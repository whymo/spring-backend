package org.zerock.myapp.javastd;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class HttpURLConnectionExample {
	
	public static void main(String[] args) throws Exception {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		// ========================================
		// 1. 지정된 url 주소로 URL 객체 생성하기
		// ========================================
		
		// URL url = new URL("https://www.naver.com/");
		URL url = new URL("http://localhost:7700");
		log.info("\t + 1. url : {}", url);
		// + 1. url : https://www.naver.com/
		// + URL을 잘못입력할 경우  MalformedURLException 예외가 발생한다.
		
		// ==========================================
		// 2. URL 객체에서 URLConnection 객체를 획득
		// ==========================================
		
		URLConnection conn = url.openConnection();
		log.info("\t + 2. conn type : {}", conn.getClass().getName());
		// + 2. conn type : sun.net.www.protocol.https.HttpsURLConnectionImpl
		
		// =================================================
		// 3. URLConnection 객체를 자식타입으로 강제형변환
		// =================================================
		
		HttpURLConnection httpConn = (HttpURLConnection) conn;	// + HTTP Client 역할을 한다.
		// httpConn.disconnect();
		
		// =======================================================================================================
		// 4. HttpURLConnection 객체를 통해서, HTTP request(요청메세지)를 보내고 HTTP response(응답메세지)를 받자
		// =======================================================================================================
		// + 이때 요청과 응답은 InputStream과 OutputStream을 통해서 수행한다.
		// =======================================================================================================
		
		InputStream is = httpConn.getInputStream();		// + 바이트 기반의 입력스트림 획득
		// + InputStream을 얻는 동시에 요청을 보내게 된다.
		
		// httpConn.getOutputStream();
		// + 나로부터 응답 메세지를 보내는 것이 아니라, url에서 받기만 할 예정이기에 InputStream만 필요
		
		int b;
		
		while ( ( b = is.read() ) != -1 ) { // + EOF : ( End Of File )
			
			System.out.print( (char) b);
			// + url에서 보내오는 것은 거대한 문자열이기에 char타입으로 변환하여 받아야 한다.
			
		} // while
		
		is.close();		// + 자원 해제
		
	} // main

} // end class
