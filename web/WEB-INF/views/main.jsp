<%--
  Created by IntelliJ IDEA.
  User: cjswo
  Date: 2019-01-18
  Time: 오후 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>메인 페이지</title>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
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
                    <li class="active"><a href="/board/main">목록<span class="sr-only">(current)</span></a></li>
                    <li><a href="/board/write">글쓰기</a></li>
                    <c:if test="${sessionScope.userInfo == null}">
                        <li><a href="/board/register">회원가입</a></li>
                    </c:if>
                </ul>
                <form class="navbar-form navbar-left" role="search"  method="post" action="/board/main">
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
        <div class="col-md-12 col-md-offset-3">
            <h3>게시물 리스트 | 총 ${requestScope.count}건 | ${requestScope.p}페이지 (${requestScope.from} - ${requestScope.to})</h3>
        </div>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col" class="col-md-2">번호</th>
            <th scope="col" class="col-md-4">제목</th>
            <th scope="col" class="col-md-3">글쓴이</th>
            <th scope="col" class="col-md-2">작성일</th>
            <th scope="col" class="col-md-1">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="board">
            <tr>
                <th scope="row">${board.id}</th>
                <td><a href="/board/detail?id=${board.id}">${board.title}</a></td>
                <td>${board.nickname}</td>
                <td>${board.regdate}</td>
                <td>${board.readCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default pull-right"<c:if test="${sessionScope.userInfo == null}"> onclick="alert('로그인을 해야 글을 쓸 수 있습니다.')"</c:if> href="/board/write">글쓰기</a>
</div>
<nav class="text-center">
    <ul class="pagination">
        <li>
            <a href="/board/main?p=${requestScope.p-5}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li class="page-item">
            <a class="page-link" href="/board/main?p=${requestScope.p-1}">Previous</a>
        </li>
        <li><a href="/board/main?p=${requestScope.p}">${requestScope.p}</a></li>
        <li><a href="/board/main?p=${requestScope.p+1}">${requestScope.p+1}</a></li>
        <li><a href="/board/main?p=${requestScope.p+2}">${requestScope.p+2}</a></li>
        <li><a href="/board/main?p=${requestScope.p+3}">${requestScope.p+3}</a></li>
        <li><a href="/board/main?p=${requestScope.p+4}">${requestScope.p+4}</a></li>
        <li class="page-item">
            <a class="page-link" href="/board/main?p=${requestScope.p+1}">Next</a>
        </li>
        <li>
            <a href="/board/main?p=${requestScope.p+5}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>


<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript" src="http:code.jquery.com/jquery-1.8.3.min.js"></script>
</body>
</html>