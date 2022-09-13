<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>errorPage.jsp</title>
</head>
<body>
	<h1>/WEB-INF/views/errorPage.jsp</h1>
	<hr>

    <p>${__EXCEPTION__}</p>

    <hr>

    <ul>
        <c:forEach items="${ __EXCEPTION__.getStackTrace() }" var="stack">
            <li><c:out value="${stack}" /></li>
        </c:forEach>
    </ul>
	
</body>
</html>