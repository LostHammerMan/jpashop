<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<%--    <link rel="stylesheet" resource="/static/css/bootstrap.css" />--%>
   <c:import url="fragments/header.jsp" />
    <title>Welcome to my Hello Shop!</title>

</head>
<body>

    <c:import url="fragments/bodyHeader.jsp" />


<div class="jumbotron">
    <h1>HELLO SHOP</h1>
    <p class="lead">회원 기능</p>
    <p>
        <a class="btn btn-lg btn-secondary" href="${root}members/new">회원 가입</a>
        <a class="btn btn-lg btn-secondary" href="${root}members/lists">회원 목록</a>
    </p>
    <p class="lead">상품 기능</p>
    <p>
        <a class="btn btn-lg btn-dark" href="${root}items/new">상품 등록</a>
        <a class="btn btn-lg btn-dark" href="${root}items/lists">상품 목록</a>
    </p>
    <p class="lead">주문 기능</p>
    <p>
        <a class="btn btn-lg btn-info" href="${root}order">상품 주문</a>
        <a class="btn btn-lg btn-info" href="${root}orderList">주문 내역</a>
    </p>
</div>
<footer>
    <c:import url="fragments/footer.jsp" />
</footer>
</body>
</html>