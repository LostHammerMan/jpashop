<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <c:import url="/WEB-INF/views/fragments/header.jsp" />
</head>
<body>
<div class="container">
  <div>
    <c:import url="/WEB-INF/views/fragments/bodyHeader.jsp" />
  </div>
  <form:form action="/items/new" modelAttribute="bookForm" method="post">
    <div class="form-group">
      <form:label path="name">상품명</form:label>
      <form:input path="name" class="form-control" placeholder="이름을 입력하세요" />
    </div>
    <div class="form-group">
      <form:label path="price">가격</form:label>
<%--      <input id="price" class="form-control" placeholder="가격 입력하세요">--%>
      <form:input path="price" class="form-control" placeholder="가격을 입력하세요."/>
  <%--    <input type="number" th:field="*{price}" class="form-control"
             placeholder="가격을 입력하세요">--%>
    </div>
    <div class="form-group">
      <form:label path="stockQuantity">수량</form:label>
      <form:input path="stockQuantity" class="form-control" placeholder="수량을 입력하세요" />
    </div>
    <div class="form-group">
      <form:label path="author">저자</form:label>
      <form:input path="author" class="form-control" placeholder="저자를 입력하세요" />
    </div>
    <div class="form-group">
      <form:label path="isbn">ISBN</form:label>
      <form:input path="isbn" class="form-control" placeholder="ISBN를 입력하세요" />

    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form:form>
  <br/>
  <div>
    <c:import url="/WEB-INF/views/fragments/footer.jsp" />
  </div>
</div> <!-- /container -->
</body>
</html>