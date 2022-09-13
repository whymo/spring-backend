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
    <title>current</title>
</head>

<body>
    
    <!-- 현 JSP에서는 아래의 태그를 응답문서 Body에 출력한다. -->
    <h1>/JSP/current.jsp</h1>
    <hr>

    <h1>forward 지시어 태그 연습</h1>

    <jsp:forward page="/JSP/forward.jsp"> 
        <jsp:param name="nickName" value="한글" />
    </jsp:forward>

</body>

</html>