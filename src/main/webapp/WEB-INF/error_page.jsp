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
        <link href="../static/css/mainPageStyle.css" rel="stylesheet" type="text/css"/>
        <link href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>' rel="stylesheet" media="screen">
        <link href="../static/css/cssmodal.css" rel="stylesheet" type="text/css"/>
        <link href='<c:url value="/static/css/bootstrap-select.css"/>' rel="stylesheet" media="screen">
        <script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
    </head>
    <body>


    <div>
        <c:if test="${error404 != null}">
            <img id="error404_logo" src="<c:url value="../static/pics/404.jpg"/>"/>
        </c:if>
        <c:if test="${error500 != null}">
            <img id="error404_logo" src="<c:url value="../static/pics/500.jpg"/>"/>
        </c:if>
        <h3 id="error_page_msg" align="center">${error404} ${error500}</h3>
        <a type="button" class="btn btn-primary" id="btn6" onclick='location.href="<c:url value="/"/>"'>
            <fmt:message key="error.main_page"/>
        </a>
    </div>

    </body>
    </html>
</fmt:bundle>