<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 위의 2개는 include.jsp로 인해 생략이 가능하다. -->

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>exam03</title>
</head>

<body>
    
    <h1>/JSTL/exam03.jsp</h1>
    <hr>

    <!-- =============================================== -->

    <h2>JSTL Core 라이브러리 실습 1 - if문 ( c:if test )</h2>
    <hr>

    <!-- = pageContext.setAttribute("myColor", "빨강"); -->
    <c:set var="myColor" value="빨강" scope="page" />

    <!-- test에 작성하면 된다. -->
    <%-- 단, test에서 EL표현식에 맞게 ${} 안에 작성해야 한다. --%>
    <!-- EL변수에는 EL내장객체나 공유속성만이 올 수 있다. (***) -->
    <c:if test="${ myColor == '빨강' }">
        <p>색상은 빨강색이다.</p>
    </c:if>

    <!-- =============================================== -->

    <h2>JSTL Core 라이브러리 실습 2 - choose + when ( switch문 )</h2>
    <hr>

    <!-- pageContext.setAttribute("grade", "70") -->
    <!-- grade란 EL변수(= 공유속성 이름) 생성됨 -->
    <c:set var="grade" value="70" scope="page" />

    <!-- 자바의 switch문 또는 SQL의 CASE - WHEN 문장을 JSTL을 이용해 구현 -->
    <%-- 3개의 JSTL 태그를 이용해서 구현 : <c:choose /> <c:when /> <c:otherwise /> --%>
    <c:choose>

        <c:when test="${ grade >= 90 }">
            <p>학점은 A이다.</p>
        </c:when>

        <c:when test="${ grade >= 80 }">
            <p>학점은 B이다.</p>
        </c:when>

        <c:when test="${ grade >= 70 }">
            <p>학점은 C이다.</p>
        </c:when>

        <c:otherwise>
            <p>학점은 F이다.</p>
        </c:otherwise>

    </c:choose>

</body>

</html>