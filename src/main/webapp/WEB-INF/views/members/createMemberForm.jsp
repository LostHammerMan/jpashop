<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html>
<head>
<%--  절대경로로 설정해야 함--%>
  <c:import url="/WEB-INF/views/fragments/header.jsp" />

  <style>
    .fieldError {
      border-color: #bd2130;
    }
  </style>
  <title>회원가입</title>
</head>
<body>

<div class="container">
  <div>
      <c:import url="/WEB-INF/views/fragments/bodyHeader.jsp" />
    <form:form action="/members/new" modelAttribute="memberForm" method="post">

      <div class="form-group">
        <form:label path="name">이름</form:label>
        <form:input path="name" class="form-control" placeholder="이름 입력하세요" />
        <form:errors path="name" class="form-control fieldError" />
  <%--      <input type="text" th:field="*{name}" class="form-control"
               placeholder="이름을 입력하세요"
               th:class="$}{#fields.hasErrors('name')? 'form-control
  fieldError' : 'form-control'">--%>
       <%-- <p th:if="${#fields.hasErrors('name')}"
           th:errors="*{name}">Incorrect date</p>--%>
      </div>
      <div class="form-group">
        <form:label path="city">도시</form:label>
        <form:input path="city" class="form-control" placeholder="도시를 입력하세요" />

      <%--        <label th:for="city">도시</label>--%>
<%--        <input type="text" th:field="*{city}" class="form-control"--%>
<%--               placeholder="도시를 입력하세요">--%>
      </div>
      <div class="form-group">
        <form:label path="street">거리</form:label>
        <form:input path="street" class="form-control" placeholder="거리를 입력하세요" />
<%--        <label th:for="street">거리</label>--%>
<%--        <input type="text" th:field="*{street}" class="form-control"--%>
<%--               placeholder="거리를 입력하세요">--%>
      </div>
      <div class="form-group">
        <form:label path="zipcode">우편번호</form:label>
        <form:input path="zipcode" class="form-control" placeholder="우편번호를 입력하세요" />
<%--        <label th:for="zipcode">우편번호</label>--%>
<%--        <input type="text" th:field="*{zipcode}" class="form-control"--%>
<%--               placeholder="우편번호를 입력하세요">--%>
      </div>
<%--      <button type="submit" class="btn btn-primary">Submit</button>--%>
      <form:button class="btn btn-primary">Submit</form:button>
    </form:form>

    <br />
    <c:import url="/WEB-INF/views/fragments/footer.jsp" />

  </div> <!-- /container -->

  </div>
</body>
</html>