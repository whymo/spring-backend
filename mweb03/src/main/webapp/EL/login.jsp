<%@ page import="org.zerock.myapp.domain.LoginBean" %>

<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- id는 빈이름으로 자유롭게 작성해도 괜찮다. -->
<!-- class는 빈클래스의 FQCN이다. -->
<!-- scope는 공유데이터 영역이다. -->
<jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" scope="page" />
<!-- property와 전송 파라미터의 이름이 같아야 *를 사용할 수 있다. -->
<jsp:setProperty name="myBean" property="*" />

<% System.out.println(myBean); %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login.jsp</title>
</head>

<body>

    <h1>/EL/login.jsp</h1>
    <hr>

    <h1>자바빈의 property 출력하기</h1>

    <%
    
        // 페이지 공유영역에서 myBean이라는 데이터 끄집어 내기
        LoginBean loginBean = (LoginBean) pageContext.getAttribute("myBean");
        session.setAttribute("__YOSEPH__", loginBean );
    
    %>

    <h2>1.JSP scripting element를 사용한 출력</h2>
    <h3>1 - 1. userid : <%= loginBean.getUserid() %></h3>
    <h3>1 - 2. passwd : <%= loginBean.getPasswd() %></h3>
    
    <h2>2. JSP action tag를 사용한 출력</h2>
    <h3>2 - 1. userid : <jsp:getProperty name="myBean" property="userid" /></h3>
    <h3>2 - 2. passwd : <jsp:getProperty name="myBean" property="passwd" /></h3>

    <h2>3. JSP EL을 사용한 방법</h2>
    <h3>3 - 1. ${myBean} </h3>

    <h2>4. useBean의 id값인 myBean이란 이름의 참조변수를 이용해서 출력</h2>
    <h3>4 - 1. userid : <%= myBean.getUserid() %> </h3>
    <h3>4 - 2. passwd : <%= myBean.getPasswd() %> </h3>

    <h2>5. JSP EL 표현식을 이용한 자바빈즈 객체의 property를 출력</h2>
    <!-- 이 경우 __YOSEPH__가 자바빈즈 객체이기 때문에 .뒤에 나오는 것은 속성이 나오게 된다. -->
    <!-- 게터 메소드를 호출한 것과 같은 기능을 한다. -->
    <h3>5 - 1. userid : ${__YOSEPH__.userid } </h3>
    <h3>5 - 2. passwd : ${__YOSEPH__.passwd } </h3>

    <%-- 만일 ${} 안에 자바빈즈객체가 온다면, .뒤에는 ${ 자바빈즈객체.프로퍼티명 }으로 작성해줘야 하며, 이는 게터 메서드와 동일하다. --%>

    <h2>6. EL 표현식의 내장객체를 활용한 방법</h2>
    <h3>6 - 1. session Scope __YOSEPH__ : ${ sessionScope.__YOSEPH__ }</h3>
    <h3>6 - 2. session Scope __YOSEPH__.userid : ${ sessionScope.__YOSEPH__.userid }</h3>
    <h3>6 - 3. session Scope __YOSEPH__.passwd : ${ sessionScope.__YOSEPH__.passwd }</h3>

</body>

</html>