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
    <title>operator.jsp</title>
</head>

<body>

    <h1>/EL/operator.jsp</h1>
    <hr>

    <h1>EL 연산자 실습</h1>

    <!-- 연산결과가 나온다. -->
    ${ 3 + 5 }<br>
    ${ 3 - 5 }<br>
    ${ 3 * 5 }<br>
    ${ 13 / 5 }<br>
    ${ 13 % 5 }<br>

    <!-- true / flase로 나온다. -->
    <h2>${ 3 == 5 }</h2>
    <h2>${ 3 > 5 }</h2>
    <h2>${ 3 < 5 }</h2>
    <h2>${ !( 3 == 5 ) }</h2>
    
</body>

</html>