<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>마이페이지 - 내활동</title>

    <link href="https://webfontworld.github.io/SCoreDream/SCoreDream.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/63eb3bc178.js" crossorigin="anonymous"></script>

    <link href="/css/Mypage1.css" rel="stylesheet" />
    <link href="https://webfontworld.github.io/SCoreDream/SCoreDream.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/63eb3bc178.js" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- favicon -->
    <link rel="icon" href="webapp/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="webapp/resources/ico/favicon.ico" type="image/x-icon">

    <script src="webapp/resources/js/loadHTML.js"></script>

</head>

<body>

    <!-- ==================HEADER==================== -->

    <section class="header">
        <nav class="nav_left">
            <a href="http://127.0.0.1:5500/WEET/weet_header2.html"  target="_self">
                <img src="img/weet_logo.png" alt="위의 이미지를 누르면 연결됩니다." width="100">
            <a>
            <div class="categories">
                <a href="커뮤니티페이지" target="_self">
                    커뮤니티
                </a>
                <a href="클래스페이지" target="_self">
                    클래스
                </a>
            </div>
        </nav>
        
        <div class="header_right">
            <input type="text" name="search"placeholder="트레이너명 또는 클래스명 입력">
            <a href="#" class="fa-solid fa-magnifying-glass" target="_self"></a>
            <a href="마이페이지" class="fa-solid fa-user" target="_self"></a>
            <a href="알람" class="fa-solid fa-bell" target="_self"></a>
        </div>
    </section>
    <div class="underline"></div>


    <!-- ==================CONTENT==================== -->

    <section class="content">

        <nav class="content_left">
            <!-- 프로필 + 왼쪽 목록 -->

            <div class="profile">

                <div class="profile_photo"></div>

                <div class="profile_id">WEE.T</div>

                <img src="./img/naver2.png" class="profile_social" alt="소설로그인표시">

                <a href="/마이페이지_회원_내정보.html" class="profile_email">helloword@naver.com <i class="fas fa-angle-right"></i> </a>

            </div>

            <div class="mypage_list">
                <!-- 왼쪽 목록 -->

                <ul class="mypage_list_top">
                    <li><a href="/마이페이지_내활동.html" class="on">내 활동</a></li>
                    <li><a href="/마이페이지_클래스룸.html">내 클래스룸</a></li>
                    <li><a href="/마이페이지_마이바디.html">MY BODY</a></li>
                </ul>

                <ul class="mypage_list_bottom">
                    <li><a href="/마이페이지_구매내역.html">구매 내역</a></li>
                    <li><a href="#">고객센터</a></li>
                </ul>

            </div>

        </nav>

        <div class="content_right">
            <!-- 오른쪽 영역 : 위쪽 목록 + 전체 / 답변완료 + -->

            <div class="mypage_top">

                <ul class="mypage_toplist">
                    <li><a href="/마이페이지_내활동.html" class="on">TR 게시판</a></li>
                    <li><a href="/마이페이지_내활동_좋아요.html">좋아요</a></li>
                    <li><a href="/마이페이지_내활동_댓글.html">댓글</a></li>
                </ul>

            </div>

            <div>

                <ul class="mypage_top_tabs">
                    <li><a href="/마이페이지_내활동.html" class="on"><span class="on2"></span> 전체</a></li>
                    <li><a href="/마이페이지_내활동_답변완료.html"><span></span> 답변 완료</a></li>
                </ul>

            </div>

            <div class="TR_tables">

                <div class="tb1">
                    <span class="tr_no">109</span>
                    <a href="#" class="tr_title">운동을 처음 시작하는데, PT로 시작해도 ...</a>
                    <span class="tr_date">2022.06.19</span>
                    <span style="color: red;" class="heart"><i class="fas fa-heart"></i></span> <span class="tr_like">12</span>
                    <span style="color: #2370DF;" class="comment"><i class="fa-regular fa-comment-dots"></i></span><span class="tr_comment">5</span>
                </div>

                <div class="tb1">
                    <span class="tr_no">108</span>
                    <a href="#" class="tr_title">다음주부터 PT를 시작하게 되었어요!</a>
                    <span class="tr_date">2022.06.19</span>
                    <span style="color: red;" class="heart"><i class="fas fa-heart"></i></span> <span class="tr_like">7</span>
                    <span style="color: #2370DF;" class="comment"><i class="fa-regular fa-comment-dots"></i></span><span class="tr_comment">6</span>
                </div>

                <div class="tb1">
                    <span class="tr_no">107</span>
                    <a href="#" class="tr_title">제 식단 좀 봐주실 수 있을까요?</a>
                    <span class="tr_date">2022.06.19</span>
                    <span style="color: red;" class="heart"><i class="fas fa-heart"></i></span> <span class="tr_like">7</span>
                    <span style="color: #2370DF;" class="comment"><i class="fa-regular fa-comment-dots"></i></span><span class="tr_comment">6</span>
                </div>

                <div class="tb1">
                    <span class="tr_no">106</span>
                    <a href="#" class="tr_title">게시물 제목</a>
                    <span class="tr_date">2022.06.19</span>
                    <span style="color: red;" class="heart"><i class="fas fa-heart"></i></span> <span class="tr_like">7</span>
                    <span style="color: #2370DF;" class="comment"><i class="fa-regular fa-comment-dots"></i></span><span class="tr_comment">6</span>
                </div>

            </div>

        </div>

    </section>

    <!-- ==================FOOTER==================== -->
    

    
    <footer class="footer">
        <div class="underline"></div>
        <img src="img/weet_logo_bw.png" alt="">
        <!-- <p>맞춤형 PT 예약&관리 플랫폼</p> -->

        <div class="notice_service">
            <button type="button" name="btnNotice" onclick="location.href='공지사항 주소';">공지사항</button>
            <button type="button" name="btnService" onclick="location.href='고객센터 주소';">고객센터</button>
            <br>
            <ul class="time">
                <li>운영시간 : 평일10시~18시 (주말, 공휴일 제외)</li>
                <li> 점심시간 : 12시 30분~14시</li>
            </ul>
        </div>
        <div class="underline2"></div>
        <div class="terms">
            <a href="이용약관페이지" target="_self">이용약관</a>
            <a href="개인정보처리방침페이지" target="_self">개인정보처리방침</a>
        </div>
        <div class="underline2"></div>
        <div class="address">
            <p>대표: 조은별 | 서울특별시 강남구 테헤란로14길 6 남도빌딩 2F | Tel : 1234-5678<br>
                사업자 등록번호 : 123-45-67892 | 통신판매업신고 : 2222-서울강남-1111호
            </p>
        </div>
    </footer>
   
</body>

</html>