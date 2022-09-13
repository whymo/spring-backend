<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page 
    import="org.zerock.myapp.domain.LoginBean"
    import="java.util.List"
    import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>set.jsp</title>
</head>

<body>
    
    <h1>/EL/set.jsp</h1>
    <hr>

    <h1>EL - List 실습</h1>

    <%
    
        LoginBean oneBean = new LoginBean();
        oneBean.setUserid("aaa");
        oneBean.setPasswd("12345");

        LoginBean twoBean = new LoginBean();
        twoBean.setUserid("bbbb");
        twoBean.setPasswd("123456");

        // LoginBean 타입을 원소로 가지는 리스트 객체를 만들고, 
        // 2개의 자바빈즈 객체( oneBean / twoBean )를 요소로 추가
        List<LoginBean> list = new ArrayList<>();
        list.add(oneBean);
        list.add(twoBean);

        // Request Scope에 리스트 객체를 속성으로 바인딩
        request.setAttribute("__MODEL__", list);
    
    %>

    <jsp:forward page="get.jsp">
        <jsp:param name="name" value="예이이이이" />
        <jsp:param name="age" value="30" />
    </jsp:forward>

</body>

</html>