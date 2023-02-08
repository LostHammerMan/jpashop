<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE HTML>
<html>
<head>
  <c:import url="/WEB-INF/views/fragments/header.jsp" />
</head>
<body>
<div class="container">
  <div><c:import url="/WEB-INF/views/fragments/bodyHeader.jsp" /></div>
  <div>
    <table class="table table-striped">
      <thead>
      <tr>
        <th>#</th>
        <th>이름</th>
        <th>도시</th>
        <th>주소</th>
        <th>우편번호</th>
      </tr>
      </thead>
      <tbody>
      <%--<tr th:each="member : ${members}">
        <td th:text="${member.id}"></td>
        <td th:text="${member.name}"></td>
        <td th:text="${member.address?.city}"></td>
        <td th:text="${member.address?.street}"></td>
        <td th:text="${member.address?.zipcode}"></td>
      </tr>--%>
      <c:forEach var="member" items="${members}">
        <tr>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.address.city}</td>
            <td>${member.address.street}</td>
            <td>${member.address.zipcode}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
    <div><c:import url="/WEB-INF/views/fragments/footer.jsp"/></div>
</div> <!-- /container -->
</body>
</html>
