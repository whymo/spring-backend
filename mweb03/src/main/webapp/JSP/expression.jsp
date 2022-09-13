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
    <title>Expression tag.jsp</title>
</head>

<body>
    
    <!-- Scriptlet Tag -->
    <%
        String name = "Yoseph";
        int age = 23;
    %>

    <!-- Expression Tag -->
    <!-- < %=  변수명 / 리터럴 / 상수 / 연산식 / 리턴값이 있는 메소드 호출 %> -->

    <h1>1. name : <%= name %></h1>
    <h1>2. age : <%= age %></h1>

</body>

</html>