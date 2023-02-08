<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.jpabook.jpashop.domain.*" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE HTML>
<html>
<head>
    <c:import url="/WEB-INF/views/fragments/header.jsp" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
  <div><c:import url="/WEB-INF/views/fragments/bodyHeader.jsp" />
  </div>
  <div>
    <div>
      <form:form modelAttribute="orderSearch" class="form-inline">
<%--          /orderList?memberName={memberName}&orderStatus={orderStatus}--%>
<%--      <form th:object="${orderSearch}" class="form-inline">--%>
        <div class="form-group mb-2">
          <form:input path="memberName" class="form-control" placeholder="회원명" />
<%--          <input type="text" th:field="*{memberName}" class="formcontrol" />--%>
        </div>
        <div class="form-group mx-sm-1 mb-2">
          <form:select path="orderStatus" class="form-control">
<%--          <select th:field="*{orderStatus}" class="form-control">--%>
            <form:option value="">주문상태</form:option>
            <c:forEach var="status" items="${orderStatus}">
                <form:option value="${status}">${status}</form:option>
             <%-- <option th:each=
                              "status : ${T(jpabook.jpashop.domain.OrderStatus).values()}"
                      th:value="${status}"
                      th:text="${status}">option
              </option>--%>
            </c:forEach>
          </form:select>
            </div>
        <form:button class="btn btn-primary mb-2">검색</form:button>
<%--        <button type="submit" class="btn btn-primary mb-2">검색</button>--%>
        </form:form>
    </div>
    <table class="table table-striped">
      <thead>
      <tr>
        <th>#</th>
        <th>회원명</th>
        <th>대표상품 이름</th>
        <th>대표상품 주문가격</th>
        <th>대표상품 주문수량</th>
        <th>상태</th>
        <th>일시</th>
        <th></th>
      </tr>
      </thead>
      <tbody>

      <c:forEach var="item" items="${orders}">
        <tr>
          <td>${item.id}</td>
          <td>${item.member.name}</td>
          <td>${item.orderItems[0].item.name}</td>
          <td>${item.orderItems[0].orderPrice}</td>
          <td>${item.orderItems[0].count}</td>
          <td>${item.status}</td>
          <td>${item.orderDate}</td>
          <td>
            <c:if test="${item.status.name() == 'ORDER'}">
<%--            <a href="'javascript:cancel('+${item.id}+')'" class="btn btn-danger">cancel</a>--%>
            <a href="#" id="cancelBtn" class="btn btn-danger" onclick="cancel(${item.id})">cancel</a>
            </c:if>
          </td>
          <%--<td th:text="${item.id}"></td>
          <td th:text="${item.member.name}"></td>
          <td th:text="${item.orderItems[0].item.name}"></td>
          <td th:text="${item.orderItems[0].orderPrice}"></td>
          <td th:text="${item.orderItems[0].count}"></td>
          <td th:text="${item.status}"></td>
          <td th:text="${item.orderDate}"></td>
          <td>
            <a th:if="${item.status.name() == 'ORDER'}" href="#"
               th:href="'javascript:cancel('+${item.id}+')'"
               class="btn btn-danger">CANCEL</a>
          </td>--%>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <div><c:import url="/WEB-INF/views/fragments/footer.jsp" /></div>

</div> <!-- /container -->
</body>
<script>
  function cancel(id) {
    console.log(id);
    let form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "${root}orderList/" + id + "/cancel");
    document.body.appendChild(form);
    form.submit();
  }
</script>
<script>
 $(document).ready(function (){




 })
</script>
</html>