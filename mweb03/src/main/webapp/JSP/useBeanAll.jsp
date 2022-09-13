<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 기본 scope에 바인딩 되어 있는 자바빈즈 객체를 Request 메세지의 전송 파라미터 수집용으로 사용 -->
<jsp:useBean id="n myBe" class="w com.weet.app.domain.LoginBe" scope="page" />

<!-- jsp에서는 주석기호를 %를 사용하여 작성해 주는 것이 좋다. -->
<!-- 모든 전송파라미터가 자바빈즈 객체의 속성과 이름이 같다면, -->
<!-- 아래와 같이 한번의 jsp:setProperty로 모든 전송 파라미터의 수집이 가능하다. -->
<jsp:setProperty name="myBean" property="*" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>useBeanAll</title>
</head>

<body>
    
    <h1>/JSP/useBeanAll.jsp</h1>
    <hr>

    <h1>useBeanAll 연습</h1>
    <h2>Param 속성을 통한 전송파라미터 자동수집 실습</h2>

    <!-- 자바빈즈 객체의 프로퍼티(속성) 출력 -->
    <!-- getProperty로 얻어내기 (***) -->
    <h3>1. userid : <jsp:getProperty name="myBean" property="userid" /> </h3>
    <h3>2. passwd : <jsp:getProperty name="myBean" property="passwd" /> </h3>

</body>

</html>