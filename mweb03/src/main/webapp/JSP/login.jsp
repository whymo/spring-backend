<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    
    // Step 1
    request.setCharacterEncoding("utf8");

    String userid = request.getParameter("userid");
    String password = request.getParameter("password");

    // Step 2 - 무조건 로그인 처리
    // 단, 아이디와 비번이 정상 수신될 경우에만

    if( ( userid != null && !"".equals(userid) ) &&  ( password != null && !"".equals(password) ) ){ 

        // 로그인이 된 상태일때

        System.out.println(">>>>>>>> 로그인되었습니다!! <<<<<<<<");
        // 로그인 정보를 보여준다.

    } else {

        // 로그인이 되지 않은 상태일때

        response.sendRedirect("/LoginForm.html");
        // 만약 로그인이 되지 않은 상태라면 로그인 창으로 이동

        System.out.println(">>>>>>>> 로그인 창으로 이동 : sendRedirect!! <<<<<<<<");
        // 로그인 창으로 이동

        return;
        // return을 사용하여 로그인이 되지 않은 상태이면, 더 이상의 메소드의 실행을 막아버린다. (***)

    } // if - else

    // Step 3
    // 로그인이 성공한다면 무엇을 해야 되는가?
    // 현재 로그인 한 웹 브라우저와 생명주기가 동일한 Session Scope에
    // Login Succed 정보를 올려 놓아야 한다.

    session.setAttribute("userid", userid);
    session.setAttribute("password", password);

%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login.jsp"</title>
</head>

<body>
    
    <h1>/JSP/login.jsp"</h1>
    <hr>

    <h2>로그인 정보를 Session Scope에 저장</h2>

    <ul>
        <li>안녕하세요! <%= userid %></li>
        <li> <a href="/JSP/loginInfo2.jsp">로그인 정보보기</a> </li>
    </ul>

</body>

</html>