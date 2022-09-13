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
    <title>count.jsp</title>

    <style>

        h2{
            color: lightslategrey;
            font-size: 3em;
            border-bottom: 3px double lightcoral;
            text-align: center;
        }

    </style>

</head>

<body>
    <h1>/JSP/count.jsp</h1>
    <hr>

    <h2>방문자수 설정하기 화면</h2>

    <!-- 선언으로 필드 생성 -->
    <%! int count; %>

    <!-- Scriptlet tag -->
    <%
    
        count ++;

        // Application 공유 데이터 영역에 count 올려 놓기
        // Scriptlet tag는 service와 같이 실행될때마다 실행되기에 방문자수가 올라간다.
        // 이때 count에는 Object 타입이 와야 하는데, int가 Integer로 오토박싱된 것이다.
        application.setAttribute("countValue", count);
    
    %>

    <!-- 이거는 Application 영역에 올려 놓은 데이터를 활용한 것이 아니라, 지역변수를 활용한 것이다. -->
    <h2>방문자수 : <%= count%> </h2>

</body>

</html>