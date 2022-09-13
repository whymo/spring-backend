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
    <title>exam04 - forEach</title>
</head>

<body>
    
    <h1>/JSTL/exam04.jsp</h1>
    <hr>

    <!-- =============================================== -->

    <h2>JSTL Core 라이브러리 실습 1 - forEach문 - 배열 ( c:forEach )</h2>
    <hr>

    <%
    
        // scriptlet tag
        // 자바 배열 리터럴 생성
        int [] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        // request scope에 배열객체 바인딩
        // 공유속성의 이름인 myArr은 EL변수명이 된다.
        request.setAttribute("myArr", num);
    
    %>

    <!-- item 속성 : 배열 또는 리스트 지정 -> 반복횟수 지정 -->
    <!-- var 속성 : item 속성에 지정된 자료구조의 1개의 원소값을 가진다. -->
    <!-- 그리고 이 var 속성의 이름이 곧 EL 변수명이 된다. -->
    <c:forEach var="element" items="${myArr}">
        <c:out value="${element}" />
    </c:forEach>

    <!-- =============================================== -->

    <h2>JSTL Core 라이브러리 실습 2 - forEach문 - List ( c:forEach )</h2>
    <hr>

    <%
    
        // List 객체일 경우

        // page directive에 import 속성으로 List 타입명을 기재하지 않을 것이라면,
        // 아래와 같이 개발자가 직접 FQCN으로 객체를 생성해도 된다. ( since java 8 )
        java.util.List<String> list = new java.util.ArrayList<>();

        list.add("홍길동");
        list.add("이순신");
        list.add("유관순");

        // request scope에 공유객체로 list 객체를 바인딩한다.
        // 이때 공유속성의 이름은 EL변수가 된다.
        request.setAttribute("__NAME__", list);

    %>

    <c:forEach var="name" items="${__NAME__}">
        <c:out value="${name}" />
    </c:forEach>

    <!-- =============================================== -->

    <h2>JSTL Core 라이브러리 실습 3 - forEach문 - begin + end + step ( c:forEach )</h2>
    <hr>

    <ol>

        <!-- = for( int count = 0; count < 10; count ++ )와 같다. -->
        <c:forEach var="count" begin="0" end="10" step="1">
            <li>${count}</li>
        </c:forEach>

    </ol>

    <!-- =============================================== -->

    <h2>JSTL Core 라이브러리 실습 4 - forTokens문 - ( c:forTokens )</h2>
    <hr>

    <%
    
        // CSV : Comma(,) Sperated Value
        // = 쉼표로 구분되어 있는 문자열 ( CSV 형식의 문자열 )
        String str = "A,B,C,D";

        // request Scope에 바인딩 : "data"란 이름이 EL변수명이 된다.
        request.setAttribute("data", str);
    
    %>

    <!-- String Tokenizer와 같은 기능을 한다. -->
    <!-- delims는 구분자를 의미한다. -->
    <c:forTokens var="token" items="${data}" delims=",">
        <c:out value="${token}" /><br>
    </c:forTokens>

</body>

</html>