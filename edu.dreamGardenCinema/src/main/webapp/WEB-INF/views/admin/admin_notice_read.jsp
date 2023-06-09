<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>notice</title>
    <link rel="stylesheet" href="/resources/css/admin/admin_notice_read.css">
</head>
<body>
    <!-- 전체 감싸는 div -->
    <div class="admin_noticeWrap">

            <!-- 게시판 제목 -->
          <span id="admin_noticeTitle">공지사항 관리</span>

            <!-- 게시판 -->
            <div class="admin_noticeBoardWrap">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                                <td>1</td>
                        </tr>
                        <tr>
                            <th>카테고리</th>
                                <td>[예매/결제]</td>
                        </tr>
                        <th>등록인</th>
                        <td>관리자1</td>
                    </tr>
                        <tr>
                            <th>제목</th>
                                <td>회원 정보 관리 변경 안내</td>
                        </tr>
                        <tr>
                            <th>내용</th>
                                <td>    <!-- 내용 -->
                                    <div class="admin_noticeContent">
                                       안내드립니다
                                    </div></td>
                        </tr>
                    
                </thead> 
             </table>

         
 
                <!-- 페이지 네이션 -->

          <!-- 등록,수정,삭제 버튼 -->
          <div class="admin_noticeBtn">
            <button class="admin_noticeEndBtn">등록</button>
            <button class="admin_noticeEndBtn">수정</button>
            <button class="admin_noticeEndBtn">삭제</button>
        </div>
        
            

            </div>

        </div>



    </div>    


</body>
</html>