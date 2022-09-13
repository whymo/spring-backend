<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" scope="page" />

<!-- 속성 설정 -->
<jsp:setProperty name="myBean" property="userid" value="wowowow" />
<jsp:setProperty name="myBean" property="passwd" value="1234567" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>useBeanSet</title>
</head>

<body>
    
    <h1>/JSP/useBeanSet.jsp</h1>
    <hr>

    <h1>useBeanSet 연습</h1>

    <!-- 자바빈즈 객체의 프로퍼티(속성) 출력 -->
    <h3>1. userid : <%= myBean.getUserid() %></h3>
    <h3>2. passwd : <%= myBean.getPasswd() %></h3>

</body>

</html>