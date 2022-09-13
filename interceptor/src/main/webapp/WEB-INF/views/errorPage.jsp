<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>errorPage.jsp</title>
</head>
<body>
	<h1>/WEB-INF/views/errorPage.jsp</h1>
	
	<hr>
	
	<p>${exception}</p>
	
	<hr>
	
	<ul>
		<c:forEach items="${exception.getStackTrace()}" var="stack">
			<li><c:out value="${stack}"/></li>
		</c:forEach>
	</ul>
</body>
</html>