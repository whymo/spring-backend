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
    <title>/JSP/LoginInfo2.jsp</title>
</head>

<body>
    
    <h1>/JSP/LoginInfo2.jsp</h1>
    <hr>

    <h2>로그인 정보 알아보러 가기</h2>

    <%
    
        // 현재의 웹 브라우저가 로그인에 성공한 브라우저인지 아닌지 판단
        // 로그인에 성공한 웹 브라우저라면, 로그아웃이라는 링크를 생성

        // Step1. Session scope에서 성공 로그인정보(id, password)를 획득
        // 만일 없으면, 리다이렉션을 통해서 로그인 창으로 밀어낸다.

        String userid = (String) session.getAttribute("userid");
        String password = (String) session.getAttribute("password");

        if( userid != null && password != null ){ // 로그인 성공정보가 유효하다면
    %>

    <p> 1. userid : <%= userid%> </p>
    <p> 2. password : <%= password%> </p>
    <h2> <a href="/JSP/logout.jsp">로그아웃</a></h2>

    <%
    
        } else { // 성공 로그인 정보가 없다면

            response.sendRedirect("/LoginForm.html");
            System.out.println(">>>>Re-directed<<<<");

            return;

        } // if - else
    
    %>
</body>

</html>