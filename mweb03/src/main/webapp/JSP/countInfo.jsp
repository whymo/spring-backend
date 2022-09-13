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
    <title>countInfo.jsp</title>
</head>

<body>
    <h1>/JSP/countInfo.jsp</h1>
    <hr>

    <h2>방문자수 조회하기 화면</h2>

    <%
    
        // Application scope에서 countValue(방문자수)라는 이름의 공유 데이터를 얻음
        // getAttribute로 얻으면 Object 객채로 반환되기에, 형변환시켜줘야 한다.
        int visitors = (Integer) application.getAttribute("countValue");
    
    %>

    <p>현재까지 총 방문자수 : <%= visitors %> </p>

</body>

</html>