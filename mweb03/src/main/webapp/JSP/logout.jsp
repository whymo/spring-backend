<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    
    // 1. 해당 브라우저에 할당된 Session Scope 파괴
    session.invalidate();

    // 2. 로그아웃 처리 후에, 웹 브라우저는 어디로 이동해야 되는지 지정 ( Re-direct )
    response.sendRedirect("/LoginForm.html");

    // + Forward
    // RequestDispatcher dis = request.getRequestDispatcher("/LoginForm.html");
    // dis.forward(request, response);

    // Forward는 해당 페이지까지는 실행하지만, Re-direct는 바로 이동한다.
    // 즉, 밑의 스크립트는 실행되지 못한다.

    return;
    // return은 어차피 가장 마지막에 수행되기에, 지워도 괜찮다.

%>