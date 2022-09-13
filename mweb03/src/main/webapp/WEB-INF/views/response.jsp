<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response</title>
</head>
<body>

    <h1>response.jsp</h1>
    <hr>

    <h2>모델 데이터 출력</h2>

    <ul>
        <li>${__MODEL__.name}</li>
        <li>${__MODEL__.age}</li>
    </ul>

</body>
</html>