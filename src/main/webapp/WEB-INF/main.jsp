<%--
  Created by IntelliJ IDEA.
  User: Voodoo3000
  Date: 21.08.2017
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <a id="main_logo" title="Заказ воды - mointainspring.com" href="/">
        <img src="../static/pics/sticker2.jpg">
    </a>

    <p id="logo_title" align="center">
        "Have questions or want to order? Give us a call 7-707-300-2537"
    </p>

    <a href="#login_form" type="button" class="btn btn-primary" id="btn" data-toggle="button" aria-pressed="false" autocomplete="off">
        Sign In
    </a>
    <a href="#join_form" type="button" class="btn btn-primary" id="btn1" data-toggle="button" aria-pressed="false" autocomplete="off">
        Sign Up
    </a>
</div>

<a href="#x" class="overlay" id="login_form"></a>
<div class="popup">
    <h2>Welcome Guest!</h2>
    <p>Please enter your login and password here</p>
    <div>
        <label for="login">Login</label>
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
    <h1 id="main_title">100% NATURAL SPRING WATER.
    </br>AND PROUD OF IT.</h1>
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
    <p> &copy; 2017 Mountain Spring. All rights reserved.</br>
        EPAM Systems &copy; 2017</br>
        Tsay V.Y. &copy; 2017
    </p>
</div>

</body>
</html>
