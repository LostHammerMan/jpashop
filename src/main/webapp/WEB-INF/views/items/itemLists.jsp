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
        <th>상품명</th>
        <th>가격</th>
        <th>재고수량</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="items" items="${itemList}">
        <tr>
          <td>${items.id}</td>
          <td>${items.name}</td>
          <td>${items.price}</td>
          <td>${items.stockQuantity}</td>
          <td><a href="/items/lists/${items.id}/edit" role="button" class="btn btn-primary">수정</a>
          </td>
          <%--<td>
            <a href="#" th:href="@{/items/{id}/edit (id=${item.id})}"
               class="btn btn-primary" role="button">수정</a>
          </td>--%>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
    <div><c:import url="/WEB-INF/views/fragments/footer.jsp"/></div>
</div> <!-- /container -->
</body>
</html>
