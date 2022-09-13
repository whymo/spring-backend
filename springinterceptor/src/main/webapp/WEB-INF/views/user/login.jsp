<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login.jsp</title>

    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/favicon.ico" type="image/x-icon">

    <script>
        // To display sign-in result.
        var result = "${__RESULT__}";

        if(result != null && result.length > 0) {
            alert(result);
        } // if
    </script>
</head>

<body>
    <h1>/WEB-INF/views/user/login.jsp</h1>
    <hr>

    <form action="/user/loginPost" method="post" style="width: 300px;">
        <fieldset style="border: 5px outset gold;">
            <legend>Login Form</legend>

            <div><input type="text" name="userid" placeholder="User ID"></div>
            <div><input type="password" name="userpw" placeholder="User Password"></div>
            <div>Remember-me <input type="checkbox" name="rememberMe"></div>
            
            <p></p>

            <div><button type="submit">Sign-in</button></div>
        </fieldset>
    </form>
</body>
</html>