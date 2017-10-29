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
        <a id="main_logo" title="<fmt:message key="default.logo"/>" href='<c:url value="${refresh}"/>'>
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

        <a href="#login_form" type="button" class="btn btn-primary" id="btn" aria-pressed="false" autocomplete="off">
            <fmt:message key="default.signin"/>
        </a>
        <a href="#join_form" type="button" class="btn btn-primary" id="btn1" aria-pressed="false" autocomplete="off">
            <fmt:message key="default.signup"/>
        </a>
    </div>

    <a href="#x" class="overlay" id="login_form"></a>
    <div class="popup">
        <form id="login-form" method="post" action="signin">
            <h2><fmt:message key="default.greetings"/></h2>
            <p><fmt:message key="default.user_verification"/></p>
            <div>
                <label for="email"><fmt:message key="default.user_login"/></label>
                <input type="email" id="login" name="email" required/>
            </div>
            <div>
                <label for="password"><fmt:message key="default.user_pass"/></label>
                <input type="password" id="password" name="password" required/>
            </div>
            <input type="submit" value=<fmt:message key="default.signin"/>>
        </form>
    </div>

    <a href="#x" class="overlay" id="join_form"></a>
    <div class="popup">
        <form id="registration-form" method="post" action="signup">
            <h2><fmt:message key="default.signup_title"/></h2>
            <p><fmt:message key="default.user_details"/></p>
            <div>
                <label for="email"><fmt:message key="default.user_login"/></label>
                <input type="email" id="email" name="email" required>
            </div>
            <div>
                <label for="pass"><fmt:message key="default.user_pass"/></label>
                <input type="password" id="pass" name="password" required>
            </div>
            <div>
                <label for="pass"><fmt:message key="default.user_repeat_pass"/></label>
                <input type="password" id="pass1" name="password" required>
            </div>
            <div>
                <label for="firstname"><fmt:message key="default.user_firstname"/></label>
                <input type="text" id="firstname" name="firstname" required>
            </div>
            <div>
                <label for="lastname"><fmt:message key="default.user_lastname"/></label>
                <input type="text" id="lastname" name="lastname" required>
            </div>
            <input type="submit" value="<fmt:message key="default.signup_button"/>"/>&nbsp;&nbsp;&nbsp;
            <fmt:message key="default.signup_or"/>&nbsp;&nbsp;&nbsp;
            <a href="#login_form" id="login_pop"><fmt:message key="default.signin"/></a>
        </form>
    </div>

    <div id="body" class="value_img">
        <h1 id="main_title"><fmt:message key="default.main_title1"/>
            </br><fmt:message key="default.main_title2"/></h1>
        <img alt="" class="body_lines" src="../static/pics/swirl.png">

        <t:order_panel/>

    </div>

    <t:footer/>

    </body>
    </html>
</fmt:bundle>