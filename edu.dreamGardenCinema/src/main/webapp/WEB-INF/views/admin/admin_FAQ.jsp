<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>faq</title>
    <link rel="stylesheet" href="../admin/style.css">
    <link rel="stylesheet" href="\resources\css\admin\admin_FAQ.css">

</head>
<body>
    <!-- 전체 감싸는 div -->
    <div class="admin_faqWrap">

              <!-- 게시판 제목 -->
              <span id="admin_faqTitle">FAQ 관리</span>


        <!-- 검색창 -->
        <div class="admin_faqSearchWrap">
            <div  class="admin_faqSearchWrap2"> 
            <select class="admin_faqSelect" name="" id="">
                <option value="title">번호</option>
                <option value="director">이름</option>
                <option value="actor">이메일</option>
            </select>
            <!-- 검색 input 창 -->
                <input type="text" name="" id="" class="admin_faqInput">
        </div>
            <!-- 검색 버튼 -->
            <div class="admin_faqBtnWrap">
                <button type="button" class="admin_faqSearchBtn">검색</button>
            </div>
        </div>
            <!-- 목록 count -->
            <div class="admin_faqList">
                <span>목록</span>
                <span>( 총</span>
                <span class="admin_faqCount">0</span>
                <span>개)</span>

            </div>
            <!-- 게시판 -->
            <div class="admin_faqBoardWrap">
                <table>
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="" id="" class="admin_faqCheckboxAll"></th>
                        <th>번호</th>
                        <th>카테고리</th>
                        <th>제목</th>
                        <th>등록인</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="checkbox" name="" id="" class="admin_faqCheckbox"></td>
                        <td>1</td>
                        <td>[예매/결제]</td>
                        <td>인터넷 예매 후 현장에서 취소 가능한가요?</td>
                        <td>2023.05.31</td>

                    </tr>
                </tbody>

                </table>
            </div>

                <!-- 페이지 네이션 -->

                 <!-- 등록,수정,삭제 버튼 -->
                 <div class="admin_faqBtn">
                    <button class="admin_faqEndBtn">등록</button>
                    <button class="admin_faqEndBtn">수정</button>
                    <button class="admin_faqEndBtn">삭제</button>
                </div>

    </div>    


</body>
</html>