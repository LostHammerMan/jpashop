<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE HTML>
<html>
<head>
  <c:import url="/WEB-INF/views/fragments/header.jsp" />
</head>
<body>
<div class="container">
  <div><c:import url="/WEB-INF/views/fragments/bodyHeader.jsp" /></div>

  <form action="${pageContext.request.contextPath}/order" method="post">
    <div class="form-group">
      <label for="member">주문회원</label>
      <select name="memberId" id="member" class="form-control">
        <option value="">회원선택</option>
        <c:forEach var="member" items="${members}">
          <option value="${member.id}">${member.name}</option>

       <%-- <option th:each="member : ${members}"
                th:value="${member.id}"
                th:text="${member.name}" />--%>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label for="item">상품명</label>
      <select name="itemId" id="item" class="form-control">
        <option value="">상품선택</option>
        <c:forEach var="item" items="${items}">
          <option value="${item.id}">${item.id}</option>
        <%--<option th:each="item : ${items}"
                th:value="${item.id}"
                th:text="${item.name}" />--%>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label for="count">주문수량</label>
      <input type="number" name="count" class="form-control" id="count"
             placeholder="주문 수량을 입력하세요">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <br/>
  <div><c:import url="/WEB-INF/views/fragments/footer.jsp" /></div>

</div> <!-- /container -->
</body>
</html>