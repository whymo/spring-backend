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

        /* a태그 효과 없애기 */
        a, a:link, a:visited{
            text-decoration: none;
            color: black;

            cursor: pointer
        }

        /* + 페이지 번호 목록 */
        #pagenation{
            width: 100%;
            margin: 0 auto;
        }

        #pagenation ul {
            float: right;
        }

        #pagenation li {
            float: left;

            list-style: none;
            /* 가로 중앙 정렬 */
            text-align: center;
            /* 세로 중앙 정렬 */
            line-height: 30px;

            width: 30px;
            height: 30px;
        }

        .prev, .next{

            /* !important는 무조건 이것을 적용하라는 의미 */
            width: 70px !important;

            color: white !important;
            background-color: darkgray !important;
        }

        /* 현재 페이지는 다르게 나오게 하기 */

        .currPage{
            color: red !important;
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

            // 등록
            $('#regBtn').on('click', () => {
                self.location = "/board/register";
            });

            // 이전 / 다음 페이지 목록
            // + 익명함수(에로우펀션) 내에서의 this는 도큐먼트를 의미한다.
            $('a.prev, a.next').click( function (e) {

                // ==============================================================
                // ( 주의 ) 함수 내에서의 this는 arrow 함수 내에서의 this와 다르다.
                // 1. 함수에서의 this : 이벤트 타겟 ( ex. a.prev, a.next )
                // 2. arrow 함수에서의 this : DOM
                // ==============================================================

                // a태그의 기본 기능을 무력화시킨다.
                // + 기존의 click 이벤트 무력화
                e.preventDefault();

                // form태그 직접 조작 및 전송
                let formObj = $('#pagenationForm');
                formObj.attr('action', '/board/list');
                formObj.attr('method', 'GET');

                console.clear();
                console.log('>>> this.href : ', $(this).attr('href'));

                formObj.find('input[type=hidden][name=currPage]').val($(this).attr('href'));
                // formObj.find('input[name=currPage]').val($(this).attr('href'));도 가능

                formObj.submit();

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
                       <td><a href="/board/get?bno=${board.bno}&currPage=${__PAGENATION__.cri.currPage}">${board.title}</a></td>
                       <td><a>${board.writer}</a></td>
                       <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.insertTs}" /></td>
                       <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.updateTs}" /></td>
                   </tr>
                </c:forEach>
            </tbody>

            <tfoot></tfoot>

        </table>

        <p>&nbsp;</p>

        <div id="pagenation">

            <form id="pagenationForm">

                <!-- 3가지 기준 정보를 hidden으로 제공 -->
                <input type="hidden" name="currPage">

                <!-- PAGEDTO를 통해서 PAGENATION 출력 -->
                <ul>

                    <!-- 이전 페이지 목록 -->
                    <!-- 이전 페이지 목록이 있을 때만 나타난다. -->
                    <c:if test="${__PAGENATION__.prev}">

                        <li class="prev"> <a href="${__PAGENATION__.startPage -1}" class="prev">이전</a></li>

                    </c:if>

                    <!-- 현재 PAGENATION 범위에 속한 페이지 번호 목록 출력 -->
                    <c:forEach var="pageNum" begin="${__PAGENATION__.startPage}" end="${__PAGENATION__.endPage}">

                        <li> 
                            <a 
                                href="/board/list?currPage=${pageNum}" 
                                class="${pageNum == __PAGENATION__.cri.currPage ? 'currPage' : '' }" > 

                                <strong>${pageNum}</strong>

                            </a>
                        </li>

                    </c:forEach>

                    <!-- 다음 페이지 목록 -->
                    <!-- 다음 페이지 목록이 있을 때만 나타난다. -->
                    <c:if test="${__PAGENATION__.next}">

                        <li class="next"> <a href="${__PAGENATION__.endPage +1}" class="next">다음</a></li>

                    </c:if>

                </ul>

            </form>

        </div>

    </div>
    
</body>

</html>