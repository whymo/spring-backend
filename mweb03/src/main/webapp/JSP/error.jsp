<%@ page 
    language="java" 
    isErrorPage = "true"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 이 JSP는 다른 JSP에서 발생하는 예외(Exception)를 내장객체인 exception으로 받아서 -->
<!-- 예외처리하는 역할을 수행한다. ( isErrorPage = "true"일 경우!!! ) -->

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>error.jsp</title>
</head>

<body>
    <h1>/JSP/error.jsp</h1>
    <hr>

    <h2>divide.jsp에서 발생한 예외를 처리하는 페이지</h2>

    <h1>고객 센터로 문의해주시기를 바랍니다.</h1>
    <p>잠시 시스템에 문제가 발생했습니다.</p>
    <p>잠시 후에 다시 시도해주시길 바랍니다.</p>

    <hr>

    <h2><%= exception.getClass() %> : <%= exception.getLocalizedMessage() %></h2>

    <hr>

    <ol>

    <%

        StackTraceElement [] ste = exception.getStackTrace();

        for( StackTraceElement element : ste ) {
    
    %>
        <li><%= element %></li>
    
    <%
    
        } // enhanced for
    
    %>


    </ol>

</body>

</html>