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
    <title>exam06 - JSTL formatting 라이브러리 2</title>
</head>

<body>
    
    <h1>/JSTL/exam06.jsp</h1>
    <hr>

    <!-- =============================================== -->

    <h2>JSTL formatting 라이브러리 1 - ( fmt:requestEncoding ) </h2>
    <hr>

    <!-- = request.setCharaterEncoding("UTF-8"); -->
    <!-- 아래의 코드는 반드시 전송파라미터의 값을 얻기 전에 해야 한다. -->
    <fmt:requestEncoding value="UTF-8" />

    <!-- 전송 파라미터에서 값을 얻어 출력한다. -->
    1. name : <%= request.getParameter("name") %> <br>
    2. age : <%= request.getParameter("age") %> <br>

    <p></p>

    <!-- EL 내장객체 활용 방법 -->
    3. name : ${param.name} <br>
    4. age : ${param.age} <br>

    <!-- =============================================== -->

    <h2>JSTL formatting 라이브러리 2 - ( fmt:setBundle + fmt:message ) </h2>
    <hr>

    <!-- .properties는 키와 값 쌍으로 들어가는 형태이며, 기본이 아스키 코드이기에 한글이 변환된다. -->
    <!-- basename에서는 확장자이름을 제외하고 작성해야 한다. -->
    <!-- 즉, bundle.properties에서 sendMessage라는 키의 값을 myBundle에 넣어 출력한 것이다. -->
    <fmt:setBundle basename="bundle" var="myBundle" />

    <!-- fmt:message에서 bundle은 출력할 값을 지정하는 것이다. -->
    <%-- 단, 이때의 bundle은 setBundle에서 var로 지정한 값만 ${}로 지정이 가능하다. --%>
    <h2>sendMessage : <fmt:message key="sendMessage" bundle="${myBundle}" /> </h2>

    <!-- =============================================== -->

    <h2>JSTL formatting 라이브러리 3 - ( fmt:formatDate ) </h2>
    <hr>

    <!-- 포메팅하기 전의 Date -->
    <!-- scope을 지정해주지 않으면, 기본적으로 pageScope가 선택된다. -->
    <c:set var="myDate" value="<%= new java.util.Date() %>" scope="request" />
    <h3>포멧팅하기 전</h3>
    <c:out value="${myDate}" /> <br>

    <!-- ======================= -->

    <!-- 포멧팅 -->
    <h3>포멧팅</h3>

    <!-- 1. 2022. 7. 20. -->
    1. <fmt:formatDate value="${myDate}" type="date" /> <br>

    <!-- 2. 오후 12:34:24 -->
    2. <fmt:formatDate value="${myDate}" type="time" /> <br>

    <!-- 3. 2022. 7. 20. 오후 12:34:24 -->
    3. <fmt:formatDate value="${myDate}" type="both" /> <br>

    <!-- 4. 22. 7. 20. 오후 12시 34분 24초 KST -->
    4. <fmt:formatDate value="${myDate}" type="both" dateStyle="short" timeStyle="long" /> <br>

    <!-- 5. 2022년 7월 20일 오후 12:34 -->
    5. <fmt:formatDate value="${myDate}" type="both" dateStyle="long" timeStyle="short" /> <br>

    <!-- ======================= -->

    <!-- 패턴 적용하기 -->

    <!-- 6. 2022-07-20 오후 12:43:25 -->
    6. <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd a HH:mm:ss" /> <br>

    <!-- + ss에서 ss.SSS로 작성해주면, 밀리세컨드까지 표시할 수 있다. -->
    <!-- + a는 오전 / 오후를 의미한다. -->
    <!-- + 그 외 추가하고 싶은 포멧팅이 있다면, 알아본 후 패턴에 추가해주면 된다. -->

    <!-- ======================= -->

    <!-- 숫자 포멧팅하기 -->

    <!-- 7. ₩100,000 -->
    7. <fmt:formatNumber value="100000" type="currency" /> <br>

    <!-- 8. 12% -->
    8. <fmt:formatNumber value="0.123" type="percent" /> <br>

    <!-- 9. 987,654,321.12 -->
    9. <fmt:formatNumber value="987654321.1234" pattern="###,###,###.00" /> <br>

    <!-- =============================================== -->

</body>

</html>