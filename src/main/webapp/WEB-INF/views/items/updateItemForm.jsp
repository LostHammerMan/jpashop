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

  <form:form modelAttribute="updateForm" method="post">
<%--      action 속성 사용하지 않는 경우 : 현재의 URL 기준으로 작동--%>

    <!-- id -->
    <form:hidden path="id" />
    <div class="form-group">
      <form:label path="name">상품명</form:label>
      <form:input path="name" class="form-control" placeholder="상품명을 입력하세요" />
    </div>
    <div class="form-group">
      <form:label path="price">가격</form:label>
      <form:input path="price" class="form-control" placeholder="가격을 입력하세요" />
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
      <form:input path="isbn" class="form-control" placeholder="ISBN을 입력하세요" />

    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
    <div><c:import url="/WEB-INF/views/fragments/footer.jsp"/></div>
</div> <!-- /container -->
</body>
</html>
