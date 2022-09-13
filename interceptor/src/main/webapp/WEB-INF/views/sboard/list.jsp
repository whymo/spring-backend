<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>list.jsp</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>

    <script>

        $(function () {
            console.clear(); console.log('ready .....');

            $('#logout').click(function () {
                console.log('clicked.');

                self.location.href = '/user/logout';
            }); // onclick
            
        }); // jq

    </script>
</head>
<body>
    <h1>/sboard/list.jsp</h1>

    <hr>

    <button id="logout">Logout</button>
</body>
</html>