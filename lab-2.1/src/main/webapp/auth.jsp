<%-- Created by IntelliJ IDEA. User: Bogdan Date: 31.10.2022 Time: 20:55 To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>${title}</title>
    <link rel="stylesheet" href="./css/auth.css" />
  </head>
  <body>
    <div class="row center-xs middle-xs auth my-container">
      <div class="col-xs-6 auth__wrapper">
        <h1 class="auth__title">${title}</h1>
        <form method="POST" class="auth__form form" action="${formTo}" id="auth-form">
          <div class="row form__wrapper">
            <label class="form__label label col-xs-12">
              <input type="text" class="form__input input" name="email" />
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
          <input type="hidden" value="${command}" />
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
    <script src="./js/pages/auth/index.js" type="module"></script>
  </body>
</html>
