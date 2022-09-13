<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View - bord/list.jsp</title>

    <script>

        var result = "${ param.__RESULT__ }";
        console.log(`result : ${result}`);

        if ( result != null && result.length > 0 ) {
            alert('${ param.__RESULT__ }');
        } //if

    </script>

</head>

<body>

    <h1>/WEB-INF/views/bord/list.jsp</h1>
    <hr>

    <h1> I AM bord/list!!!</h1>
    
    <h7> ${__LIST__}</h7>
    
</body>

</html>