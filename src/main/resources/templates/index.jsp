<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>메인화면입니다.</title>
</head>
<style>
    .btn-primary:hover {
    color: #fff;
    background-color: #0069d9;
    border-color: #0062cc;
    }
    .btn:focus, .btn:hover {
    text-decoration: none;
    }
    .btn-primary {
    color: #fff;
    background-color: #007bff;
    border-color: #007bff;
    }
    .btn {
    display: inline-block;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    border: 1px solid transparent;
    padding: 0.5rem 0.75rem;
    font-size: 1rem;
    line-height: 1.25;
    border-radius: 0.25rem;
    transition: all .15s ease-in-out;
    }
</style>
<body>
 <h1>메인페이지입니다.</h1>
<button class="btn btn-primary"onclick="location.href='/logout'">로그아웃</button>
<button class="btn btn-primary" onclick="location.href='/comp'">comp</button>
<button class="btn btn-primary"onclick="location.href='/pid'">로그아웃</button>
</body>
</html>