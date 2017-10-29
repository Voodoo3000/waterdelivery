<%@tag description="user edit form" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-striped" id="order_content">
    <thead>
    <tr class="success">
        <th>#</th>
        <th><fmt:message key="default.water"/></th>
        <th><fmt:message key="default.bottle_value"/></th>
        <th><fmt:message key="default.quantity"/></th>
        <th><fmt:message key="default.output_price"/></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${contentList}" var="content" varStatus="i">
        <tbody>
        <tr>
            <td>i</td>
            <td><fmt:message key="default.type_${content.water.type}"/></td>
            <td><fmt:message key="default.size_${content.bottleSize.size}"/></td>
            <td>${content.quantity}</td>
            <td>${content.quantity*content.bottleSize.size*content.water.pricePerLiter}</td>
            <td> <a type="button" class="btn btn-danger"  href='<c:url value="/do/open_customer_cart"/>'>
                <fmt:message key="default.remove"/>
            </a></td>
        </tr>
        </tbody>
    </c:forEach>
    <tfoot>
    <tr class="info">
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th>Total amount:${totalAmount}</th>
        <th><a href="#checkout_form" type="button" class="btn btn-primary" aria-pressed="false">
            <fmt:message key="default.checkout"/>
        </a></th>
    </tr>
    </tfoot>
</table>