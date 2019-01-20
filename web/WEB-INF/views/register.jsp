<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cjswo
  Date: 2019-01-19
  Time: 오후 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원가입</title>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<script language="JavaScript">
    function check_onclick(){
        theFrom = document.register_form;

        if(theFrom.inputName.value==""){
            alert("이름이 비어있습니다. 2자 이상을 넣어주세요.");
            return location.href='/board/register';
        }else if((theFrom.inputName.value).length < 2){
            alert("이름을 2자 이상 넣어주세요.");
            return location.href='/board/register';
        }
        if(theFrom.nickName.value==""){
            alert("닉네임이 비어있습니다.");
            return location.href='/board/register';
        }
        if(theFrom.inputEmail.value==""){
            alert("이메일이 비어있습니다.");
            return location.href='/board/register';
        }
        if(theFrom.inputPassword.value==""){
            alert("비밀번호가 비어있습니다.");
            return location.href='/board/register';
        }
        if(theFrom.inputPasswordCheck.value==""){
            alert("비밀번호확인이 비어있습니다.");
            return location.href='/board/register';
        }else if(theFrom.inputPasswordCheck.value !== theFrom.inputPassword.value){
            alert("비밀번호와 확인의 값이 다릅니다.");
            return location.href='/board/register';
        }
    }
</script>
<body>
<article class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">게시판</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/board/main">목록<span class="sr-only">(current)</span></a></li>
                    <li><a href="/board/write">글쓰기</a></li>
                    <c:if test="${sessionScope.userInfo == null}">
                        <li><a href="/board/register" class="active">회원가입</a></li>
                    </c:if>
                </ul>
                <form class="navbar-form navbar-left" name = "register_form" role="search"  method="post" action="/board/main">
                    <div class="form-group">
                        <input type="text" name="search" class="form-control" placeholder="제목 검색">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <c:if test="${sessionScope.userInfo != null}">
                    <ul class="nav navbar-right">
                        <li><a href="/board/logout">${sessionScope.userInfo.nickname}님 환영합니다.</a></li>
                    </ul>
                </c:if>

            </div>
        </div><!-- /.container-fluid -->
    </nav>
    <div class="page-header">
        <div class="col-md-6 col-md-offset-3">
            <h3>회원가입</h3>
        </div>
    </div>
    <div class="col-sm-6 col-md-offset-3">
        <form role="form" method="post" name = "register_form" action="/board/register">
            <div class="form-group">
                <label for="inputName">성명</label>
                <input type="text" class="form-control" name="inputName" id="inputName" placeholder="이름을 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="nickName">닉네임</label>
                <input type="text" class="form-control" name="nickName" id="nickName" placeholder="휴대폰번호를 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="inputEmail">이메일 주소</label>
                <input type="email" class="form-control" name="inputEmail" id="inputEmail" placeholder="이메일 주소를 입력해주세요">
            </div>
            <div class="form-group">
                <label for="inputPassword">비밀번호</label>
                <input type="password" class="form-control" name="inputPassword" id="inputPassword" placeholder="비밀번호를 입력해주세요">
            </div>
            <div class="form-group">
                <label for="inputPasswordCheck">비밀번호 확인</label>
                <input type="password" class="form-control" name="inputPasswordCheck" id="inputPasswordCheck" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
            </div>
            <div class="form-group text-center">
                <a class="btn btn-default pull-right" href="/login">취소</a>
                <button type="submit" class="btn btn-default pull-right" onclick="check_onclick()">가입</button>
            </div>
        </form>
    </div>

</article>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript" src="http:code.jquery.com/jquery-1.8.3.min.js"></script>
</body>
</html>

