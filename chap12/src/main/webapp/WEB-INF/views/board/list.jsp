<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View - board/list.jsp</title>

    <style>

        *{
            /* margin: 0 auto;으로 하면 가운데에 정렬되게 된다.  */
            margin: 0 auto;
            padding: 0;
        }

        #wrapper{
            width: 1024px;
            
            font-family: 'D2Coding';
            font-size: 14px;
        }

        table{
            width: 100%;

            /* border을 접어버린다. */
            border-collapse: collapse;
            border: 1px ridge lightslategrey;

            text-align: center;
        }

        th{
            padding: 10px;

            color: white;
            background-color: darkslateblue;

            font-size: 16px;
        }

        caption{
            font-size: 20px;
            font-weight: bold;

            padding-bottom: 10px;
        }

        tr:hover{

            /* tr에 호버링할 때, 마우스 커서가 손모양으로 변한다. */
            cursor: pointer;

            background-color: lightpink;

        }
        
        /* td태그 중에 2번째 td태그 */
        td:nth-child(2){
            text-align: left;
            padding-left: 10px;

            width: 40%;
        }

        #regBtn{
            width: 150px;
            height: 40px;

            border: none;

            font-size: 17px;
            font-weight: bold;

            color: white;
            background-color: dimgrey;

            float: right;
        }

        /* + 플롯팅 효과 없애는 방법!! (***) */
        #regBtn::after{
            content: '';
            display: block;
            clear: both;
        }

        a, a:link, a:visited{
            text-decoration-line: none;
        }

    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js" integrity="sha512-QDsjSX1mStBIAnNXx31dyvw4wVdHjonOwrkaIhpiIlzqGUCdsI62MwQtHpJF+Npy2SmSlGSROoNWQCOFpqbsOg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script>

        $(function() {

            let prevStepResult = "${__RESULT__}";

            if( prevStepResult != null && prevStepResult.length > 0 ){
                alert(prevStepResult);
            } // if

            $('#regBtn').on('click', () => {
                self.location = "/board/register";
            });

         });

    </script>

</head>

<body>

    <h1>/WEB-INF/views/board/list.jsp</h1>
    <hr>

    <p>&nbsp;</p>

    <div id="wrapper">

        <button type="button" id="regBtn">REGISTER</button>

        <table border="1">
            <caption>TBL_BOARD</caption>

            <thead>
                <tr>
                    <th>bno</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>insert_Time</th>
                    <th>update_Time</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="board" items="${__LIST__}">
                   <tr>
                       <td><a>${board.bno}</a></td>
                       <td><a href="/board/get?bno=${board.bno}">${board.title}</a></td>
                       <td><a>${board.writer}</a></td>
                       <td><fmt:formatDate pattern="yyyy/mm/dd HH:mm:ss" value="${board.insertTs}" /></td>
                       <td><fmt:formatDate pattern="yyyy/mm/dd HH:mm:ss" value="${board.updateTs}" /></td>
                   </tr>
                </c:forEach>
            </tbody>

            <tfoot></tfoot>

        </table>

    </div>
    
</body>

</html>