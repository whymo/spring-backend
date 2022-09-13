<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" scope="page" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>exam01.jsp</title>
</head>

<body>

	<h1>/JSTL/exam01.jsp</h1>
	<hr>

    <!-- =============================================== -->

	<h1>JSTL Core 라이브러리 실습1</h1>
	<hr>

	<!-- set 태그의 목적 : 공유 영역에 속성 바인딩 수행 -->
	<!-- var 속성 : 바인딩되는 속성의 이름 지정 -->
	<!-- value 속성 : 바인딩되는 속성의 값 -->
	<!-- 중요 : 이 태그의 var 속성의 값이 EL 변수명이 된다! -->

    <!-- setAttribute -->
	<c:set var="__PAGE__" value="PAGE_1" scope="page" />
	<c:set var="__REQUEST__" value="REQUEST_1" scope="request" />
	<c:set var="__SESSION__" value="SESSION_1" scope="session" />
	<c:set var="__APPLICATION__" value="APPLICATION_1" scope="application" />

	<!-- out 태그의 value 속성의 값 : (1) 문자열 (2) EL 표기법 -->
	1 - 1. <c:out value="Hello World!" /> <br>
	
	1 - 2. <c:out value="${__PAGE__}" /> <br>
	1 - 3. ${__PAGE__} <br>

    <!-- =============================================== -->

	<h1>JSTL Core 라이브러리 실습2</h1>
	<hr>

	<!-- 4개의 공유영역 한군데에 정한 이름으로 정한 값을 바인딩 수행 -->

	<!-- 아래의 set 태그는 공유영역에 속성 바인딩을 수행하는 것이 아니라! -->
	<!-- 이미 공유영역에 바인딩 되어있는 자바빈즈 객체의 특정 프로퍼티의 값을 -->
	<!-- 설정(set)하는 태그역할을 수행 -->

    <c:set target="${ myBean }" property="userid" value="inky123456" />

    2 - 1. ${ myBean.userid }<br>

    2 - 2. <c:out value="${ myBean.userid }" />
    
    <%-- c:out과 EL의 출력 결과물은 같게 나오지만, --%>
    <%-- c:out은 특수문자가 들어갔을 경우, EL에 비해 안전하게 출력해 준다. --%>

    <!-- =============================================== -->

    <h1>JSTL Core 라이브러리 실습3</h1>
	<hr>

    3 - 1. 삭제 전 : <c:out value="${__REQUEST__}" /> <br>

    <c:remove var="__REQUEST__" />

    3 - 2. 삭제 후 : <c:out value="${__REQUEST__}" /> <br>

</body>

</html>