<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE HTML>
<head>
  <c:import url="${root}fragments/header.jsp" />

  <style>
  .fieldError {
    border-color: #bd2130;
  }
</style>
</head>
<body>
<div class="container">
  <div>
    <c:import url="${root}fragments/bodyHeader.jsp" />
  <form:form action="/members/new" modelAttribute="memberForm" method="post">

    <div class="form-group">
      <form:label path="name">이름</form:label>
      <form:input path="name" class="form-control" placeholder="이름 입력하세요" />
      <c:if test=""
      <form:errors path="name" class="form-control fieldError" />
<%--      <input type="text" th:field="*{name}" class="form-control"
             placeholder="이름을 입력하세요"
             th:class="${#fields.hasErrors('name')}? 'form-control
fieldError' : 'form-control'">--%>
      <p th:if="${#fields.hasErrors('name')}"
         th:errors="*{name}">Incorrect date</p>
    </div>
    <div class="form-group">
      <label th:for="city">도시</label>
      <input type="text" th:field="*{city}" class="form-control"
             placeholder="도시를 입력하세요">
    </div>
    <div class="form-group">
      <label th:for="street">거리</label>
      <input type="text" th:field="*{street}" class="form-control"
             placeholder="거리를 입력하세요">
    </div>
    <div class="form-group">
      <label th:for="zipcode">우편번호</label>
      <input type="text" th:field="*{zipcode}" class="form-control"
             placeholder="우편번호를 입력하세요">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form:form>
  <br/>
    <div><c:import url="${root}fragments/footer.jsp" /></div>
</div> <!-- /container -->
</body>
</html>