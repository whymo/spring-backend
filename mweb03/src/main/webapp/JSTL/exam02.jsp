<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>exam02.jsp</title>
</head>

<body>
    
    <h1>/JSTL/exam02.jsp</h1>
    <hr>

    <h2>JSTL Core 라이브러리 실습1</h2>

    <!-- EL로 작성해줘야 한다. -->
    1. __PAGE__ : <c:out value="${ __PAGE__ }" /><br>
    2. __REQUEST__ : <c:out value="${ __REQUEST__ }" /><br>
    3. __SESSION__ : <c:out value="${ __SESSION__ }" /><br>
    4. __APPLICATION__ : <c:out value="${ __APPLICATION__ }" /><br>

</body>

</html>