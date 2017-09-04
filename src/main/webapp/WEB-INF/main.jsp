<%--
  Created by IntelliJ IDEA.
  User: Voodoo3000
  Date: 21.08.2017
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_EN"/>
<fmt:bundle basename="i18n.message">
<html>
<head>
    <title>mountainspring.com</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../static/css/mainPageStyle.css" rel="stylesheet" type="text/css" />
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="../static/css/cssmodal.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="header" align="center">
    <a id="main_logo" title="<fmt:message key="default.logo"/>" href="/">
        <img src="../static/pics/sticker2.jpg">
    </a>

    <p id="logo_title" align="center">
        <fmt:message key="default.logo_title"/>
    </p>

    <a href="#login_form" type="button" class="btn btn-primary" id="btn" data-toggle="button" aria-pressed="false" autocomplete="off">
        <fmt:message key="default.signin"/>
    </a>
    <a href="#join_form" type="button" class="btn btn-primary" id="btn1" data-toggle="button" aria-pressed="false" autocomplete="off">
        <fmt:message key="default.signup"/>
    </a>
</div>

<a href="#x" class="overlay" id="login_form"></a>
<div class="popup">
    <h2>Welcome Guest!</h2>
    <p>Please enter your login and password here</p>
    <div>
        <label for="login">Login(Email)</label>
        <input type="text" id="login" value="" />
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" id="password" value="" />
    </div>
    <input type="button" value="Log In" />
</div>

<a href="#x" class="overlay" id="join_form"></a>
<div class="popup">
    <h2>Sign Up</h2>
    <p>Please enter your details here</p>
    <div>
        <label for="email">Login (Email)</label>
        <input type="text" id="email" value="" />
    </div>
    <div>
        <label for="pass">Password</label>
        <input type="password" id="pass" value="" />
    </div>
    <div>
        <label for="firstname">First name</label>
        <input type="text" id="firstname" value="" />
    </div>
    <div>
        <label for="lastname">Last name</label>
        <input type="text" id="lastname" value="" />
    </div>
    <input type="button" value="Sign Up" />&nbsp;&nbsp;&nbsp;or&nbsp;&nbsp;&nbsp;<a href="#login_form" id="login_pop">Log In</a>
</div>

<div id="body" class="value_img">
    <h1 id="main_title"><fmt:message key="default.main_title1"/>
    </br><fmt:message key="default.main_title2"/></h1>
    <img alt="" class="body_lines" src="../static/pics/swirl.png">
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Panel title</h3>
    </div>
    <div class="panel-body">
        Panel content
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