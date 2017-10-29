<%@tag description="user edit form" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="#x" class="overlay" id="checkout_form"></a>
<div class="popup">
    <form id="checkout-form" method="get" action="order">
        <h2><fmt:message key="default.ordering"/></h2>
        <c:if test="${totalAmount > user.wallet}">
            <h4 class="alert-danger"><fmt:message key="default.not_enough_money"/></h4>
        </c:if>
        <div>
            <label><fmt:message key="default.address"/></label>
            <input type="text" class="form-control" name="address" value="${order.address}" required>
        </div>
        <input type="submit" class="btn btn-primary" value=<fmt:message key="default.order"/>>
    </form>
</div>