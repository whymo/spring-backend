<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>list</title>

    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/favicon.ico" type="image/x-icon">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

    <script>

        $(function () {

            $('#logoutBtn').on('click', function () {
                self.location.href = '/user/logout';        // GET, /user/logout
            }); // .onclick

        }); // .jq

    </script>
</head>
<body>
    <h1>/WEB-INF/views/board/list.jsp</h1>
    <hr>

    <button type="button" id="logoutBtn">Logout</button>

</body>
</html>