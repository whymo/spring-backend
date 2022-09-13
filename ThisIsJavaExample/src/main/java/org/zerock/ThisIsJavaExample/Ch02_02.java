package org.zerock.ThisIsJavaExample;

public class Ch02_02 {
	
	public static void main(String[] args) {
		
		int v1 = 15;
		
		if(v1 > 10) {
			int v2 = v1 - 10;
		} // if - v1이 10보다 크면 실행
		
//		int v3 = v1 + v2 + 5;
		// v2에서 에러가 발생하게 되는데, 이는 v2가 if블록안에서 선언하였기에 if블록 내에서만 유효하기 때문이다.
		
	} // end class

} // end class
