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
    <title>LoginInfo.jsp - 내장객체 실습</title>
</head>

<body>
    
    <h1>LoginInfo.jsp - 내장객체 실습</h1>
    <h1>로그인 입력 파라미터 출력</h1>

    <hr>

    <!-- scriptlet Tag -->
    <%
    
        // request 내장객체
        request.setCharacterEncoding("UTF-8");

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        // 응답문서에 출력
        out.println("1. userid : " + userid);
        out.println("2. password : " + password);

        // 콘솔에 출력
        System.out.println("1. userid : " + userid);
        System.out.println("2. password : " + password);

        // 롬복이 사용이 불가능하기에, log도 사용이 불가능하다.
    
    %>

    <!-- Expression Tag -->
    <p>1. 아이디 값 : <%= userid %> </p>
    <p>2. 비밀번호 값 : <%= password %> </p>

</body>

</html>