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
    <title>config.jsp</title>
</head>

<body>
   <h1>/JSP/config.jsp</h1>
   
   <hr>

   <h2>config 내장객체를 이용한 초기화 파라미터 값 얻어내기</h2>

   <%
   
    // Scriptlet : 자바 실행문장을 얼마든지 만들 수 있는 JSP 태그
    String dirPath = config.getInitParameter("dirPath");
    String userid = config.getInitParameter("userid");
   
   %>

   <ol>
       <li> dirPath : <%= dirPath %> </li>
       <li> userid : <%= userid%> </li>
   </ol>

</body>

</html>