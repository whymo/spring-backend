<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" scope="session" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>useBeanScope</title>
</head>

<body>

    <h1>/JSP/useBeanScope.jsp</h1>
    <hr>

    <h1>useBeanScope 연습</h1>

    <%
    
        myBean.setUserid("yoyoyo");
        myBean.setPasswd("7777777");

    %>

    <!-- 자바빈즈 객체의 프로퍼티(속성) 출력 -->
    <h3>1. userid : <%= myBean.getUserid() %></h3>
    <h3>2. passwd : <%= myBean.getPasswd() %></h3>

</body>

</html>