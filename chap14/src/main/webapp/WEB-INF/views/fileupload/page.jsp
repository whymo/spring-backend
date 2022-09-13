<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View - void.jsp</title>
</head>

<body>

    <h1>/WEB-INF/views/fileupload/page.jsp</h1>
    <hr>

    <h1> I AM PAGE!!!!</h1>

    <form 
        action="/fileupload/doit" 
        method="POST" 
        enctype="multipart/form-data">

        <div>file1 : <input type="file" name="files"></div>
        <div>file2 : <input type="file" name="files"></div>
        <div>file3 : <input type="file" name="files"></div>
        <div>file4 : <input type="file" name="files"></div>
        <div>file5 : <input type="file" name="files"></div>
        <div><input type="submit" value="Upload"></div>

    </form>
    
</body>

</html>