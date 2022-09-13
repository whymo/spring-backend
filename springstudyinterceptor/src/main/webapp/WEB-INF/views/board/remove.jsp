<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View - bord/list.jsp</title>

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

        #listBtn, #modifyBtn{
            width: 80px;
            height: 40px;

            border: 0px;

            font-size: 16px;
            font-weight: bold;
        }

        #modifyBtn{
            color: white;
            background-color: darkolivegreen;
        }

        #listBtn{
            color: white;
            background-color: darkslateblue;
        }

        button:hover{
            cursor: pointer;
        }

    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js" integrity="sha512-QDsjSX1mStBIAnNXx31dyvw4wVdHjonOwrkaIhpiIlzqGUCdsI62MwQtHpJF+Npy2SmSlGSROoNWQCOFpqbsOg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script>

        $(function() {

            // 목록으로 돌아갔을 때, 그 전에 볼 수 있는 페이지로 돌아갈 수 있도록 currPage를 넘긴다.
            $('#modifyBtn').on('click', () => {
                self.location = "/board/modify?bno=${__BOARD__.bno}&currPage=${param.currPage}";
            });

            $('#listBtn').on('click', () => {
                self.location = "/board/list?currPage=${param.currPage}";
            });

        });

    </script>

</head>

<body>

    <h1>/WEB-INF/views/board/get.jsp</h1>
    <hr>

    <div id="wrapper">

        <form action="#">

            <input type="hidden" name="currPage">

            <table>

                <tbody>

                    <tr>
                        <td><label for="bno">글번호</label></td>
                        <td><input type="text" name="bno" id="bno" size="20" value="${__BOARD__.bno}" readonly></td>
                    </tr>

                    <!-- 제목 -->
                    <tr>
                        <td><label for="title">제목</label></td>
                        <td><input type="text" name="title" id="title" size="50" value="${__BOARD__.title}" readonly></td>
                    </tr>

                    <tr>
                        <td><label for="content">내용</label></td>
                        <td><textarea name="content" id="content" cols="52" rows="10" readonly>${__BOARD__.content}</textarea></td>
                    </tr>

                    <tr>
                        <td><label for="title">제목</label></td>
                        <td><input type="text" name="writer" id="writer" size="20" value="${__BOARD__.writer}" readonly></td>
                    </tr>

                    <tr>
                        <td colspan="">&nbsp;</td>
                    </tr>

                    <tr>

                        <td colspan="2">
                            <button type="button" id="modifyBtn">수정</button>
                            <button type="button" id="listBtn">목록</button>
                        </td>
                    </tr>

                </tbody>

            </table>

        </form>

    </div>
    
</body>

</html>