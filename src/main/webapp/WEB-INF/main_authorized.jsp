<%--
  Created by IntelliJ IDEA.
  User: Voodoo3000
  Date: 07.09.2017
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="en_EN"/>
<fmt:bundle basename="i18n.message">
    <html>
    <head>
        <title>mountainspring.com</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../static/css/mainPageStyle.css" rel="stylesheet" type="text/css" />
        <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="../static/css/cssmodal.css" rel="stylesheet" type="text/css" />
        <script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
    </head>
    <body>

    <div id="header" align="center">
        <a id="main_logo" title="<fmt:message key="default.logo"/>" href="/">
            <img src="../static/pics/sticker2.jpg">
        </a>

        <p id="logo_title" align="center">
            <fmt:message key="default.logo_title"/>
        </p>

    </div>

    <div id="body" class="value_img">
        <h1 id="main_title"><fmt:message key="default.main_title1"/>
            </br><fmt:message key="default.main_title2"/></h1>
        <img alt="" class="body_lines" src="../static/pics/swirl.png">
    </div>

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Panel title</h3>
        </div>
        <div class="panel-body">
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Water Types
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="#">Spring</a></li>
                    <li><a href="#">Sparkling</a></li>
                    <li><a href="#">Distilled</a></li>
                </ul>
            </div>
        </div>
        <div class="panel-body">
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Bottle value
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <li><a href="#">0.5L</a></li>
                    <li><a href="#">1L</a></li>
                    <li><a href="#">1.5L</a></li>
                    <li><a href="#">5L</a></li>
                    <li><a href="#">19L</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="footer" align="center">
        <p><fmt:message key="default.footer_title1"/>.</br>
            EPAM Systems &copy; 2017</br>
            <fmt:message key="default.footer_title2"/>
        </p>
    </div>

    </body>
    </html>
</fmt:bundle>
