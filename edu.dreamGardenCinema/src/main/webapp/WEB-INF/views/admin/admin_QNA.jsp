<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>qna</title>
    <link rel="stylesheet" href="../admin/style.css">
    <link rel="stylesheet" href="/resources/css/admin/admin_QNA.css">
</head>
<body>
    <!-- 전체 감싸는 div -->
    <div class="admin_qnaWrap">
        
            <!-- 게시판 제목 -->
            <span id="admin_qnaTitle">1:1문의 관리</span>

        <!-- 검색창 -->
        <div class="admin_qnaSearchWrap">
               <!-- select랑 검색창만 감싸는 div -->
               <div  class="admin_userSearchWrap2">
            <select class="admin_qnaSelect" name="" id="">
                <option value="title">번호</option>
                <option value="director">이름</option>
                <option value="actor">이메일</option>
            </select>
            <!-- 검색 input 창 -->
        
                <input type="text" name="" id="" class="admin_qnaInput">
     
            </div>
            <!-- 검색 버튼 -->
            <div class="admin_qnaBtnWrap">
                <button type="button" class="admin_qnaSearchBtn">검색</button>
            </div>
        </div>
            <!-- 목록 count -->
            <div class="admin_qnaList">
                <span>목록</span>
                <span>( 총</span>
                <span class="admin_qnaCount">0</span>
                <span>개)</span>

            </div>
            
            <!-- 게시판 -->
            <div class="admin_qnaBoardWrap">
                <table>
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="" id="" class="admin_qnaCheckboxAll"></th>
                        <th>번호</th>
                        <th>제목</th>
                        <th>등록일</th>
                        <th>등록인</th>
                      
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="checkbox" name="" id="" class="admin_qnaCheckbox"></td>
                        <td>1</td>
                        <td>지갑을 잃어버렸어요</td>
                        <td>2023.06.01</td>
                        <td>typoon</td>
                   
                    </tr>
                    
                </tbody>

                </table>
            </div>

                <!-- 페이지 네이션 -->

                <!-- 등록,수정,삭제 버튼 -->
                <div class="admin_qnaBtn">
                    <button class="admin_qnaEndBtn">등록</button>
                    <button class="admin_qnaEndBtn">수정</button>
                    <button class="admin_qnaEndBtn">삭제</button>
                </div>
                

            
    </div>    


</body>
</html>