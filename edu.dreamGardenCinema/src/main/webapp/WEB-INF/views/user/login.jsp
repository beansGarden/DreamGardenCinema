<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" href="/resources/css/user/login.css">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
    <div id="login-box-modal">
        <form action="/user/login" method="post">
            <p class="login-text">로그인</p>

            <span class="first-Visit-text">처음 방문하셧나요?</span>

            <a href="/user/signUp" class="sign-up-text">계정 만들기</a>

            <input type="text" placeholder="ID" id="userId" name="userId">

            <input type="password" placeholder="PW" id="userPw" name="userPw">

            <div class="login-check">
                <label>
                    <input type="checkbox" name="saveId" id="saveId">
                    <label for="saveId" color><span></span>아이디 저장</label>
                </label>
            </div>

            <button class="login-btn">로그인</button>
            
            <a href="/user/accountFind" class="find-user-text">ID/PW 찾기</a>
        </form>
    </div>
</body>
</html>