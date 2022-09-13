<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View - commandObject.jsp</title>
</head>

<body>

    <h1>/WEB-INF/views/commandObject.jsp</h1>
    <hr>

    <!-- EL 표현식 -->
    <!-- Model 상자 안에 들어간 데이터 활용 (***) -->

    <!-- + Model에 key로 sampleDto라는 이름으로 저장한 value는 -->
    <!-- + SampleDTO 객체인데, 이는 자바빈즈 클래스에 해당되기에 -->
    <!-- + sampleDto.property명이 와야 한다. -->
    <h1>1. dto : ${sampleDto}</h1>
    <h2>2. name : ${sampleDto.name}</h2>
    <h2>3. age : ${sampleDto.age}</h2>
    <h2>4. page : ${page}</h2>
    
</body>

</html>