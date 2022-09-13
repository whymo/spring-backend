<%-- <%@ page isELIgnored="true" %> --%>
<!-- 위와 같이 사용되면 EL이 무시가 되기에, 아래 적힌 EL이 기능하지 않고 단순히 문자로 사용된다. -->

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>get.jsp</title>
</head>

<body>
    
    <h1>/EL/get.jsp</h1>
    <hr>

    <h1>EL - List 실습</h1>

    <!-- __MODEL__의 이름을 가지고 있는 데이터의 값은 list이며, -->
    <!-- list를 반환하기에 이를 가지고 [0]번째 원소인 oneBean의 값 출력 -->
    <h2>1. 1st LoginBean : ${ __MODEL__[0].userid }, ${ __MODEL__[0].passwd } </h2>

    <!-- list의 [1]번째 원소인 twoBean의 값 출력 -->
    <h2>2. 2nd LoginBean : ${ __MODEL__[1].userid }, ${ __MODEL__[1].passwd } </h2>
    <h2>3. name : ${ param.name } </h2>
    <h2>4. age : ${ param.age } </h2>

</body>

</html>