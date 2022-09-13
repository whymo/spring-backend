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
    <title>other.jsp</title>
</head>

<body>
    
    <h1>/EL/other.jsp</h1>
    <hr>

    <h1>EL 실습 - scope.jsp에서 공유 데이터 가지고 오기</h1>

    <!-- 아래는 EL의 내장 객체이다. ( 내장객체의 이름이 JSP와 다르다. ) -->
    <%-- EL에서는 ${ 각 SCOPE별내장객체명.공유속성명 }을 통해서 공유 객체를 출력해야 한다. --%>
    <!-- 단 EL의 경우 값을 얻어낼 수는 있지만, 특정 scope에 값을 올려 놓을 수는 없다. -->
    <!-- 왜냐하면, EL은 값을 출력하는데 목적이 있기 때문이다. -->

    <!-- ======================================================= -->

    <!-- page와 request의 경우에는 출력되지 않는다. -->
    <!-- 그 이유는 page는 scope.jsp가 다 읽어지는 순간 파괴되고 -->
    <!-- request의 경우에는 응답을 주는 순간 파괴되기 때문이다. -->
    <h2>1. pageScope : ${ pageScope.pageScope } </h2>
    <h2>2. requestScope : ${ requestScope.requestScope } </h2>

    <!-- ======================================================= -->

    <!-- 브라우저와 WAS를 죽이지 않았다면, 정상 출력된다. -->
    <h2>3. sessionScope : ${ sessionScope.sessionScope } </h2>
    <h2>4. applicationScope : ${ applicationScope.applicationScope } </h2>

</body>

</html>