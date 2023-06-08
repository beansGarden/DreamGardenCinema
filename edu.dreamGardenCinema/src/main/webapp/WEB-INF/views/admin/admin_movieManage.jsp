<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>movieManage</title>
    <link rel="stylesheet" href="../admin/style.css">
    <link rel="stylesheet" href="\resources\css\admin\admin_movieManage.css">

</head>
<body>
    <!-- 전체 감싸는 div -->
    <div class="admin_movieManageWrap">

              <!-- 게시판 제목 -->
              <span id="admin_movieManageTitle">영화 관리</span>

        <!-- 검색창 -->
        <div class="admin_movieSearchWrap">
            <div  class="admin_moiveSearchWrap2"> 
            <select class="admin_movieManageSelect" name="" id="">
                <option value="title">제목</option>
                <option value="director">감독</option>
                <option value="actor">배우</option>
            </select>
            <!-- 검색 input 창 -->
                <input type="text" name="" id="" class="admin_movieManageInput">
           </div>
            <!-- 검색 버튼 -->
            <div class="admin_movieManageBtnWrap">
                <button type="button" class="admin_movieSearchBtn">검색</button>
            </div>
        </div>
            <!-- 목록 count -->
            <div class="admin_movieManageList">
                <span>목록</span>
                <span>( 총</span>
                <span class="admin_movieManageCount">0</span>
                <span>개)</span>

            </div>
            <!-- 게시판 -->
            <div class="admin_movieManageBoardWrap">
                <table>
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="" id="" class="admin_movieCheckboxAll"></th>
                        <th>번호</th>
                        <th>제목</th>
                        <th>감독</th>
                        <th>개봉일</th>
                        <th>매출</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="checkbox" name="" id="" class="admin_movieCheckbox"></td>
                        <td>1</td>
                        <td>범죄도시3</td>
                        <td>이상용</td>
                        <td>2023.05.31</td>
                        <td>10,000,000</td>
                    </tr>
                </tbody>

                </table>
            </div>

                <!-- 페이지 네이션 -->

                <!-- 등록,수정,삭제 버튼 -->
                <div class="admin_movieBtn">
                    <button class="admin_movieEndBtn">등록</button>
                    <button class="admin_movieEndBtn">수정</button>
                    <button class="admin_movieEndBtn">삭제</button>
                </div>
            

           

       



    </div>    


</body>
</html>