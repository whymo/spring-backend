<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 위의 2개는 include.jsp로 인해 생략이 가능하다. -->

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>exam05 - JSTL formatting 라이브러리</title>
</head>

<body>
    
    <h1>/JSTL/exam05.jsp</h1>
    <hr>

    <!-- =============================================== -->

    <h2>JSTL formatting 라이브러리 - ( fmt:requestEncoding ) </h2>
    <hr>

    <!-- = request.setCharaterEncoding("UTF-8"); -->
    <!-- 아래의 코드는 반드시 전송파라미터의 값을 얻기 전에 해야 한다. -->
    <fmt:requestEncoding value="UTF-8" />

    1. name : <%= request.getParameter("name") %> <br>
    2. age : <%= request.getParameter("age") %> <br>

    <p></p>

    <!-- EL 내장객체 활용 방법 -->
    3. name : ${param.name} <br>
    4. age : ${param.age} <br>

    <!-- =============================================== -->

</body>

</html>