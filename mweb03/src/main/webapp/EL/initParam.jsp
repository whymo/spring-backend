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
    <title>initParam.jsp</title>
</head>

<body>
    
    <h1>/EL/initParam.jsp</h1>
    <hr>

    <h1>EL 실습 - 초기화 파라미터 값 가지고 오기</h1>

    <h2>1. context 파라미터 이름 값 : ${ initParam.jdbcDriver } </h2>
    <h2>2. context 파라미터 주소 값 : ${ initParam.savePath } </h2>

</body>

</html>