<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin_DashBoard</title>
    <link rel="stylesheet" href="\resources\css\admin\admin_dashboard.css">
    <script src="\resources\js\admin\admin_dashboard.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
</head>
<body>
    <!-- 전체 감싸는 div -->
    <div class="admin_dashboardWrap">
        <div class="admin_dashboardSalesforMovie">
            <span>영화별 총 매출</span>
            <div class="admin_dashboardSalesforMovie-in">
            <select>
                <option value="">범죄도시3</option>
                <option value="">바비</option>
                <option value="">인어공주</option>
            </select>
            <span>￦5,000,000</span>
        </div>
        </div>

        <div class="admin_dashboardSalesforQuater">
            <span>1분기 총 매출</span>
            <div class="admin_dashboardSalesforQuater-in">
            <span>￦50,000,000</span>
        </div>
    </div>

        <!-- 1:1 문의 List -->
        <div class="admin_dashboardQnaList">    
            <span>1:1 문의 리스트</span>
            <div class="admin_dashboardQnaList-in">
            <table>
                <tbody>
                    <tr>
                        <th>제목</th>
                        <th>회원</th>
                    </tr>
                    <tr>
                            <td>지갑 잃어버렸어요</td>
                            <td>유저일</td>
                     </tr>
                     <tr>
                            <td>할인 쿠폰 어떻게 적용하죠?</td>
                            <td>유저이</td>
                    </tr>
                     <tr>
                            <td>할인 쿠폰 어떻게 적용하죠?</td>
                            <td>유저이</td>
                    </tr>
                     <tr>
                            <td>할인 쿠폰 어떻게 적용하죠?</td>
                            <td>유저이</td>
                    </tr>
                     <tr>
                            <td>할인 쿠폰 어떻게 적용하죠?</td>
                            <td>유저이</td>
                    </tr>
                     <tr>
                            <td>할인 쿠폰 어떻게 적용하죠?</td>
                            <td>유저이</td>
                    </tr>
            </tbody>
            </table>
            </div>
        </div>

        
        <div class="admin_dashboardSalesforLastweek">
            <span>지난주 요일별 매출</span>
            <div class="admin_dashboardSalesforLastweek-in">
                <div>
                    <!--차트가 그려질 부분-->
                    <canvas id="myChart" style="height: 200px; width: 200px;"></canvas>
                </div>
        </div>
     </div>
    </div>


    <script src="../admin/admin_dashboard.js"></script>

</body>
</html>
