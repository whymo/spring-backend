<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>declare.jsp - 선언하다.</title>
</head>

<body>
    
    <h1>Declare 태그를 이용한 JSP LifeCycle 메소드</h1>

    <%! 
    
        // 변환된 클래스 소스 파일에 필드 선언
        String initMesg = "jspInit 메소드";
        String delMesg = "jspDestroy 메소드"; // 변환될 클래스 소스파일에 메소드 선언
        
        // 변환된 클래스 소스 파일에 메소드 선언
        // JSP LifeCycle( 서블릿과 유사하게 ) 메소드

        public void jspInit(){ // when NEW state call back : 시작할때
            System.out.println(">>>>> " + initMesg + "<<<<<");
        } // jspInit
        
        public void jspDestroy(){ // when Destroy state call back : 파괴되기 전에
            System.out.println(">>>>> " + delMesg + "<<<<<");
        } // jspDestroy()
        
    %>

    <!-- JSP 선언 태그의 경우 위치는 상관이 없다. -->
    <!-- 단, 롬복은 사용할 수 없다. -->

</body>

</html>