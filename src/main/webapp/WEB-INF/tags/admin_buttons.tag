<%@tag description="admin_buttons" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
    <div class="btn-group" id="btn-group-admin-edit">
        <a id="bt_users" class="btn btn-default" href='<c:url value="/do/main"/>'><fmt:message key="default.users"/></a>
        <a id="bt_orders" class="btn btn-default" href='<c:url value="/do/admin_orders"/>'><fmt:message key="default.orders"/></a>
        <a id="bt_water" class="btn btn-default" href='<c:url value="/do/admin_water"/>'><fmt:message key="default.water"/></a>
        <a id="bt_bottle_value" class="btn btn-default" href='<c:url value="/do/admin_bottle"/>'><fmt:message key="default.bottle_value"/></a>
    </div>
</div>