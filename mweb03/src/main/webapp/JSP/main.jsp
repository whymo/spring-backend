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
    <title>main</title>
</head>

<body>
    
    <h1>include 지시어 태그</h1>

    <p>내용을 수정 중입니다. 다음 줄에 삽입이 됩니다.</p>

    <%@ include file="copyright.jsp" %>
    <!-- 태그에 익숙해져야 한다. -->
    <!-- 지금은 정적인 포함이다. -->

</body>

</html>