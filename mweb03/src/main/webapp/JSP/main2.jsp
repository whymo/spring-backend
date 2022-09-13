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
    <title>main2</title>
</head>

<body>
    
    <h1>/JSP/main2.jsp</h1>
    <hr>

    <h1>include 지시어 태그 연습</h1>

    <p>현재 시간을 구하는 연습문제입니다. 다음줄에 삽입됩니다.</p> <br>

    <!-- target인 header.jsp를 include할 때, header.jsp에 필요한 파라미터를 전달하는 용도로 -->
    <!-- param 액션 태그를 사용해야 한다. -->
    <jsp:include page="/JSP/header.jsp" flush="true"> 
        <jsp:param name="nickName" value="한글" />
    </jsp:include>

</body>

</html>