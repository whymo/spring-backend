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
    <title>response.jsp</title>
</head>

<body>

    <!-- 요청을 target으로 다시 보내게 한다. -->
    <!-- 즉, response.jsp를 런 온 서버를 하면, responseRedirect.jsp로 바로 이동하게 된다. -->
    <% response.sendRedirect("responseRedirect.jsp"); %>
    
</body>

</html>