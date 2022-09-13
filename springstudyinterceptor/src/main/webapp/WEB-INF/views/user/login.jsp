<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View - user/login.jsp</title>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js" integrity="sha512-QDsjSX1mStBIAnNXx31dyvw4wVdHjonOwrkaIhpiIlzqGUCdsI62MwQtHpJF+Npy2SmSlGSROoNWQCOFpqbsOg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script>

        // + 로그인 결과를 보여준다.
        var result = ${__RESULT__};

        if ( result != null && result.length > 0 ){
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
            <div><input type="password" name="userpw" placeholder="User PassWord"></div>
            <div>Remember-me <input type="checkbox" name="rememberMe" value="on"></div>

            <p></p>

            <div><button type="submit">Sign-in</button></div>
        
        </fieldset>
    
    </form>
    
</body>

</html>