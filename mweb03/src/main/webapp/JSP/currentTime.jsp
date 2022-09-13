<!-- 모든 종류의 지시자는 중복선언이 가능하지만 -->
<!-- 각 지시자의 속성은 중복선언되어서는 안된다!! -->

<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page
    import="java.util.Date"
    import="java.util.Calendar"
    import="java.util.List" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>currentTime.jsp</title>
</head>

<body>
    
    <h1>현재 날짜 출력 실습</h1>

    <%
    
        // script Tag : 자바 실행문장(조각)들을 실행
        Date now = new Date();
        System.out.println("- now : " + now);

        int age = 23;

    %>

    <!-- Expression Tag : 표현식 == 연산식 -->
    <h2>Now : <%= now %></h2>
    <h2>1. 이름 : <%= "Yoseph" %></h2>
    <h2>2. 산술연산식의 결과 : <%= 10+100 %></h2>
    <h2>3. 나이 : <%= age %></h2>

</body>

</html>