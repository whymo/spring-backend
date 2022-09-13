<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View - bord/modify.jsp</title>

    <style>
        
        *{
            margin: 0 auto;
            padding: 0;
        }

        #wrapper{
            width: 1024px;
            margin-top: 30px;

            font-family: 'D2Coding';
            font-size: 14px;
        }

        #listBtn, #submitBtn, #deleteBtn{
            width: 80px;
            height: 40px;

            border: 0px;

            font-size: 16px;
            font-weight: bold;
        }

        #submitBtn{
            color: white;
            background-color: darkolivegreen;
        }

        #listBtn{
            color: white;
            background-color: darkslateblue;
        }

        #deleteBtn{
            color: white;
            background-color: red;
        }

        button:hover{
            cursor: pointer;
        }

    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js" integrity="sha512-QDsjSX1mStBIAnNXx31dyvw4wVdHjonOwrkaIhpiIlzqGUCdsI62MwQtHpJF+Npy2SmSlGSROoNWQCOFpqbsOg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script>

        $(function() {

            // 삭제버튼 클릭시, Post /board/remove?bno=로 요청 전송
            $('#deleteBtn').on('click', () => {

                let formObj = $('form');
                
                // formObj.attr(속성이름, 속성값);
                formObj.attr('action', '/board/remove');
                formObj.attr('method', 'POST');
                formObj.submit();

            });

            $('#listBtn').on('click', () => {
                self.location = "/board/list";
            });

        });

    </script>

</head>

<body>

    <h1>/WEB-INF/views/board/modify.jsp</h1>
    <hr>

    <div id="wrapper">

        <form action="/board/update" method="post">

            <table>

                <tbody>

                    <tr>
                        <td><label for="bno">글번호</label></td>
                        <td><input type="text" name="bno" id="bno" size="20" value="${__BOARD__.bno}" readonly></td>
                    </tr>

                    <!-- 제목 -->
                    <tr>
                        <td><label for="title">제목</label></td>
                        <td><input type="text" name="title" id="title" size="50" value="${__BOARD__.title}"></td>
                    </tr>

                    <tr>
                        <td><label for="content">내용</label></td>
                        <td><textarea name="content" id="content" cols="52" rows="10">${__BOARD__.content}</textarea></td>
                    </tr>

                    <tr>
                        <td><label for="title">작성자</label></td>
                        <td><input type="text" name="writer" id="writer" size="20" value="${__BOARD__.writer}"></td>
                    </tr>

                    <tr>
                        <td colspan="">&nbsp;</td>
                    </tr>

                    <tr>

                        <td colspan="2">
                            <button type="submit" id="submitBtn">수정등록</button>
                            <button type="button" id="deleteBtn">삭제</button>
                            <button type="button" id="listBtn">목록</button>
                        </td>
                    </tr>

                </tbody>

            </table>

        </form>

    </div>
    
</body>

</html>