<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>main-header(black)</title>

    <link rel="stylesheet" href="/webapp/resources/css/header.css">
</head>

<body>

    <header>

        <div class="dg-menu">

            <ul class="dg-menu-L">
                <li class="dg-menu-L1"><a href="#">영화</a>
                    <ul class="dg-menu-drop-movie">
                        <li><a href="#">홈</a></li>
                        <li><a href="#">상영 예정작</a></li>
                        <li><a href="#">현재 상영작</a></li>
                    </ul>
                </li>

                <li class="dg-menu-L2"><a href="#">예매</a>
                    <ul class="dg-menu-drop-reservation">
                        <li><a href="#">예매하기</a></li>
                        <li><a href="#">상영 시간표</a></li>
                    </ul>
                </li>

                <li class="dg-menu-L3"><a href="#">멤버십</a>
                    <ul class="dg-menu-drop-membership">
                        <li><a href="#">멤버십 이용약관</a></li>
                        <li><a href="#">나의 멤버십</a></li>
                    </ul>
                </li>
            </ul>

            <span class="dg-logo"><a href="#"><img src="/image-dg/검은배경 로고.svg"></a></span>

            <ul class="dg-menu-R">
                <li class="dg-menu-R1"><a href="#">고객센터</a>
                    <ul class="dg-menu-customer-service">
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">공지사항</a></li>
                        <li><a href="#">1:1 문의</a></li>
                    </ul>
                </li>

                <!-- 로그인한 회원이 없을 경우 -->
                <li class="dg-menu-login"><a href="#">로그인</a></li>
                <!-- 로그인한 회원이 있을 경우 -->
                <li class="dg-menu-logout"><a href="#">로그아웃</a></li>

                <!-- 비회원일 경우 -->
                <li class="dg-menu-nonmember"><a href="#">회원가입</a></li>
                <!-- 회원일 경우 -->
                <li class="dg-menu-member"><a href="#">마이 페이지</a></li>
            </ul>
        </div>

        <hr>



    </header>

    <!-- <script src="/js/header.js"></script> -->


</body>

</html>