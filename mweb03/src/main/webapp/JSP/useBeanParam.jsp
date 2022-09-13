<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 기본 scope에 바인딩 되어 있는 자바빈즈 객체를 Request 메세지의 전송 파라미터 수집용으로 사용 -->
<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" />

<!-- .getParameter 대신 해준다. -->
<!-- param 값은 전송 파라미터에 맞게 설정해 주면 된다. -->
<jsp:setProperty name="myBean" property="userid" param="myUserid" />
<jsp:setProperty name="myBean" property="passwd" param="myPasswd" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>useBeanParam</title>
</head>

<body>
    
    <h1>/JSP/useBeanParam.jsp</h1>
    <hr>

    <h1>useBeanParam 연습</h1>
    <h2>Param 속성을 통한 전송파라미터 자동수집 실습</h2>

    <!-- 자바빈즈 객체의 프로퍼티(속성) 출력 -->
    <h3>1. userid : <%= myBean.getUserid() %></h3>
    <h3>2. passwd : <%= myBean.getPasswd() %></h3>

</body>

</html>