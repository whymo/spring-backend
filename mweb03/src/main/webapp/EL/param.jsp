<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>param.jsp</title>
</head>

<body>
    
    <h1>/EL/param.jsp</h1>
    <hr>

    <h1>EL 실습</h1>

    <!-- EL에서는 request 전송파라미터 값을 param으로 바로 얻어낼 수 있다. (**) -->
    <h2>1. 사용자 아이디 : ${ param.userid }</h2>
    <h2>2. 사용자 비밀번호 : ${ param.passwd }</h2>

    <!-- hobby는 여러 값을 가지고 있기에, -->
    <!-- paramValues로 배열 값을 가지고 온 후에 [인덱스 번호]를 통해 원소를 하나씩 출력해야 한다. -->
    <h2>3. 사용자 취미1 : ${ paramValues.hobby[0] } </h2>
    <h2>3. 사용자 취미2 : ${ paramValues.hobby[1] } </h2>

    <!-- EL 안에서도 정적 메소드(Arrays)를 활용하여 배열의 원소를 출력해 줄 수도 있다. (***) -->
    <h2>4. hobbies : ${ Arrays.toString( paramValues.hobby ) }</h2>

</body>

</html>