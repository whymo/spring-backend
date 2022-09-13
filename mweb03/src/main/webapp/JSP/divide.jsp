<%@ page 
    language="java" 
    isErrorPage = "false"
    errorPage = "/JSP/error.jsp"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

    int n = 4 / 0;
    // 오류가 발생되는 코드이다.
    // isErrorPage = "true";를 작성해 줘야만 exception 내장객체를 사용할 수 있다.
    // 또한 errorPage = "에러처리jsp"로 이 페이지에서 오류가 발생하면 어느 곳에서 예외를 처리할지 정해야 한다.

%>