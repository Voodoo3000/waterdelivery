<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.message">
    <html>
    <head>
        <title>mountainspring.com</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../static/css/mainPageStyle.css" rel="stylesheet" type="text/css" media="screen"/>
        <link href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>' rel="stylesheet" media="screen">
        <link href="../static/css/cssmodal.css" rel="stylesheet" type="text/css" media="screen"/>
        <link href='<c:url value="/static/css/bootstrap-select.css"/>' rel="stylesheet" media="screen">
        <script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
    </head>
    <body>

    <div id="header" align="center">

        <t:main_logo/>

        <c:if test="${user.role == 'CLIENT'}">
            <t:logo_title/>
        </c:if>

        <c:if test="${user != null}">
            <t:greetings_title/>
            <t:signout_btn/>
        </c:if>

        <c:if test="${user == null}">
            <t:reg_login_btn_form/>
        </c:if>

        <t:locale_changer/>

    </div>

    <div id="body" class="value_img">

        <c:if test="${errormsg != null}">
            <h5 id="errormsg" align="center">${errormsg}</h5>
        </c:if>

        <c:if test="${user == null || user.role == 'CLIENT'}">
            <t:title_bodylines/>
            <t:order_panel/>
        </c:if>

        <c:if test="${user.role == 'ADMIN'}">
            <t:admin_buttons/>
            <t:admin_edit_users/>
        </c:if>

    </div>

    <c:if test="${user == null || user.role == 'CLIENT'}">
        <t:footer/>
    </c:if>

    </body>
    </html>
</fmt:bundle>