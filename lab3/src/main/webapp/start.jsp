<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/start.css">
</head>
<body>
<div style="position: absolute; top: 20px; left: 20px; z-index: 10">

<div class="clock">
    <div class="outer-clock-face">
        <div class="marking marking-one"></div>
        <div class="marking marking-two"></div>
        <div class="marking marking-three"></div>
        <div class="marking marking-four"></div>
        <div class="inner-clock-face">
            <div class="hand hour-hand"></div>
            <div class="hand min-hand"></div>
            <div class="hand second-hand"></div>
        </div>
    </div>
</div>
</div>



<div class="row center-xs middle-xs auth my-container">
    <div class="col-xs-6 auth__wrapper">
        <h1 class="auth__title">${title}</h1>
        <form method="POST" class="auth__form form" action="${formTo}" id="auth-form">
            <div class="row form__wrapper">
                <label class="form__label label col-xs-12">
                    <input type="text" class="form__input input" name="email"/>
                    <span class="form__placeholder">email</span>
                </label>
                <label class="form__label label col-xs-12">
                    <input
                            type="password"
                            class="form__input input"
                            name="password"
                    />
                    <span class="form__placeholder">password</span>
                </label>
            </div>
            <input type="hidden" value="${command}"/>
            <div class="row center-xs middle-xs between-xs">
                <button class="form__btn btn" type="submit">Submit</button>
                <a href="${linkTo}" class="form__link btn">${linkText}</a>
            </div>
        </form>
    </div>
</div>
<div class="popup disabled">
    <span class="popup__close">&times;</span>
    <h2 class="popup__title"></h2>
</div>
<script src="${pageContext.request.contextPath}/resources/js/start.js"></script>
</body>
</html>
