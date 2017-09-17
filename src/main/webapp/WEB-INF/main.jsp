<%--
  Created by IntelliJ IDEA.
  User: Voodoo3000
  Date: 21.08.2017
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.message">
    <html>
    <head>
        <title>mountainspring.com</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../static/css/mainPageStyle.css" rel="stylesheet" type="text/css"/>
        <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../static/css/cssmodal.css" rel="stylesheet" type="text/css"/>
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

        <div id="locale_changer" class="dropdown">
            <button class="btn btn-default dropdown-toggle" id="dropdownMenu3" data-toggle="dropdown">
                <c:if test="${locale.language=='ru'}"><img src='<c:url value="/static/pics/RU.png"/>'/></c:if>
                <c:if test="${locale.language=='en'}"><img src='<c:url value="/static/pics/EN.png"/>'/></c:if>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu3">
                <li role="option"><a role="menuitem" tabindex="-1" href="locale?locale=ru"><img
                        src='<c:url value="/static/pics/RU.png"/>'/>Русский</a></li>
                <li role="option"><a role="menuitem" tabindex="-1" href="locale?locale=en"><img
                        src='<c:url value="/static/pics/EN.png"/>'/>English</a></li>
            </ul>
        </div>

        <a href="authorized" type="button" class="btn btn-primary" id="btn" aria-pressed="false" autocomplete="off">
            <fmt:message key="default.signin"/>
        </a>
        <a href="#join_form" type="button" class="btn btn-primary" id="btn1" aria-pressed="false" autocomplete="off">
            <fmt:message key="default.signup"/>
        </a>
    </div>

    <a href="#x" class="overlay" id="login_form"></a>
    <div class="popup">
        <h2>Welcome Guest!</h2>
        <p>Please enter your login and password here</p>
        <div>
            <label for="login">Login(Email)</label>
            <input type="text" id="login" value=""/>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" value=""/>
        </div>
        <input type="button" value="Log In"/>
    </div>

    <a href="#x" class="overlay" id="join_form"></a>
    <div class="popup">
        <h2>Sign Up</h2>
        <p>Please enter your details here</p>
        <div>
            <label for="email">Login (Email)</label>
            <input type="text" id="email" value=""/>
        </div>
        <div>
            <label for="pass">Password</label>
            <input type="password" id="pass" value=""/>
        </div>
        <div>
            <label for="firstname">First name</label>
            <input type="text" id="firstname" value=""/>
        </div>
        <div>
            <label for="lastname">Last name</label>
            <input type="text" id="lastname" value=""/>
        </div>
        <input type="button" value="Sign Up"/>&nbsp;&nbsp;&nbsp;or&nbsp;&nbsp;&nbsp;<a href="#login_form"
                                                                                       id="login_pop">Log In</a>
    </div>

    <div id="body" class="value_img">
        <h1 id="main_title"><fmt:message key="default.main_title1"/>
            </br><fmt:message key="default.main_title2"/></h1>
        <img alt="" class="body_lines" src="../static/pics/swirl.png">

        <div class="panel panel-default">
            <h3 id="panel-title">Order title</h3>
            <div class="panel-body">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Water types
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#">Spring</a></li>
                        <li><a href="#">Sparkling</a></li>
                        <li><a href="#">Distilled</a></li>
                    </ul>
                </div>
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
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

                </p>
                <form class="form-inline">
                    <span class="btn btn-primary" id="minus">-</span>
                    <input type="number1" class="form-control" id="input" value="0" readonly="readonly">
                    <span class="btn btn-primary" id="plus">+</span>
                </form>
                <p>
                    <script>
                        $(document).ready(function () {
                            $('#minus').click(function () {
                                var $input = $(this).parent().find('input');
                                var count = parseInt($input.val()) - 1;
                                count = count < 1 ? 0 : count;
                                $input.val(count);
                                $input.change();
                                return false;
                            });
                            $('#plus').click(function () {
                                var $input = $(this).parent().find('input');
                                $input.val(parseInt($input.val()) + 1);
                                $input.change();
                                return false;
                            });
                        });
                    </script>

                <div class="amount_output">
                <h5 id="amount-title">Amount</h5>
                <output typeof="number" class="form-control" id="output" readonly="readonly"></output>

                <a href="#" type="button" class="btn btn-default" id="btn2" aria-pressed="false" autocomplete="off">
                    GET ORDER
                </a>
            </div>
            </div>
        </div>
    </div>

    <div id="footer" align="center">
        <p><fmt:message key="default.footer_title1"/> </br>
            EPAM Systems &copy; 2017</br>
            <fmt:message key="default.footer_title2"/>
        </p>
    </div>

    </body>
    </html>
</fmt:bundle>