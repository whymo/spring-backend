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
    <title>forward</title>
</head>

<body>
    
    <!-- 현 JSP에서는 아래의 태그를 응답문서 Body에 출력한다. -->
    <h1>/JSP/forward.jsp</h1>
    <hr>

    <h1>forward 지시어 태그 연습</h1>
    <h2>/JSP/current.jsp에서 포워드된 화면</h2>

    <%
    
        String httpMethod = request.getMethod(); // HTTP Method

        // 직접 입력한 피라미터 획득 ( 포스트맨 )
        String data = request.getParameter("data");

        // current.jsp에서 준 전송파라미터 획득
        String nickName = request.getParameter("nickName");
    
    %>

    <h3>포워드 되었으며, 넘어온 파라미터값은 <%= nickName %>입니다. </h3>
    <h3>직접 입력시킨 파라미터값은 <%= data %>입니다. </h3>

    <h3>HTTP method : <%= httpMethod %> </h3>

</body>

</html>