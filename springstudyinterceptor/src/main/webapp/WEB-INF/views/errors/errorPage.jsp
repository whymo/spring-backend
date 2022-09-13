<%@ page 
    language="java"
    isErrorPage ="true" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>/JSP/errorPage.jsp</title>
</head>

<body>
    
    <h1>/JSP/errorPage.jsp</h1>
    <hr>

    <p>${_EXCEPTION_}</p>
    <hr>

    <ul>
        <c:forEach items="${ _EXCEPTION_.getStackTrace() }" var="stack">
            <li><c:out value="${stack}" /></li>
        </c:forEach>
    </ul>
    
</body>

</html>