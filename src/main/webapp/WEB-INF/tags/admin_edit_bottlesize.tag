<%@tag description="admin_edit_bottlesize" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="admin_bottle_table">
    <table class="table table-striped">
        <div class="msg-error">${user_change_error}</div>
        <thead>
        <tr align="center" class="success">
            <th>#</th>
            <th><fmt:message key="default.bottle_value"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${bottleSizes}" var="bottle" varStatus="i">
            <form method="post" action="change_bottle_size">
                <tr>
                    <td>${i.count}</td>
                    <td align="left"><input required type="text" class="form-control" name="size"
                               value="${bottle.size}"/>
                    </td>
                    <td align="left"><fmt:message key="default.size_liter"/></td>
                    <td>
                        <button class="btn btn-primary" name="id" value="${bottle.id}" type="submit">
                            <fmt:message key="default.dosave"/></button>
                    </td>
                </tr>
            </form>
        </c:forEach>
        <tfoot>
        <tr align="center" class="info">
            <td></td>
            <td></td>
            <td></td>
            <td>
                <div align="left">
                    <button class="btn btn-success" name="add-user" type="button" data-toggle="modal"
                            data-target="#modalCreateUser"><fmt:message key="default.add"/></button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</div>

<div class="modal fade" id="modalCreateUser" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                </button>
                <h4 class="modal-title" align="center"><fmt:message key="default.add"/> <fmt:message
                        key="default.water'"/></h4>
            </div>
            <div class="msg-error" id="msg_u">${create_error}</div>
            <div class="modal-body">
                <form id="create-user-form" class="createForm" method="post" action="create_size">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.bottle_value"/></span>
                                <input type="text" class="form-control" name="size"
                                       value="${size}" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <button type="submit" class="btn btn-success"><fmt:message key="default.add"/></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                        key="default.cancel"/></button>
            </div>
        </div>
    </div>
</div>