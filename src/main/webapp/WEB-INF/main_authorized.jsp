<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <p id="greetings_title" align="center">
            Добро пожаловать Владислав! На вашем счету: 50000тг.
        </p>

        <a href="#join_form" type="button" class="btn btn-primary" id="btn4" aria-pressed="false" autocomplete="off">
            <fmt:message key="default.signout"/>
        </a>
    </div>

    <div id="body" class="value_img">
        <h1 id="main_title"><fmt:message key="default.main_title1"/>
            </br><fmt:message key="default.main_title2"/></h1>
        <img alt="" class="body_lines" src="../static/pics/swirl.png">

        <div class="panel panel-default">
            <h3 id="panel-title"><fmt:message key="default.order_panel"/></h3>
            <div class="panel-body">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <fmt:message key="default.water_types"/>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#"><fmt:message key="default.type_spring"/></a></li>
                        <li><a href="#"><fmt:message key="default.type_sparkling"/></a></li>
                        <li><a href="#"><fmt:message key="default.type_distilled"/></a></li>
                    </ul>
                </div>
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <fmt:message key="default.bottle_value"/>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                        <li><a href="#"><fmt:message key="default.half_liter"/></a></li>
                        <li><a href="#"><fmt:message key="default.one_liter"/></a></li>
                        <li><a href="#"><fmt:message key="default.one_and_half_liters"/></a></li>
                        <li><a href="#"><fmt:message key="default.five_liters"/></a></li>
                        <li><a href="#"><fmt:message key="default.nineteen_liters"/></a></li>
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

                <div class="price_output">
                    <h5 id="amount-title"><fmt:message key="default.output_price"/></h5>
                    <output typeof="number" class="form-control" id="output" readonly="readonly"></output>

                    <a href="#" type="button" class="btn btn-primary" id="btn2" aria-pressed="false" autocomplete="off">
                        <fmt:message key="default.add_to_cart"/>
                    </a>

                    <a href="#" type="button" class="btn btn-default" id="btn3" aria-pressed="false" autocomplete="off">
                        <fmt:message key="default.get_order"/>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <t:footer/>

    </body>
    </html>
</fmt:bundle>