<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    <%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="xui">xui</a>
<form action="/" id="form">
    <input type="text" name="x" id="x"/>
    <input type="text" name="y" id="y"/>
    <input type="text" name="r" id="r"/>
    <button type="submit">send</button>
</form>
<%--<script src="./js/script.js"></script>--%>
</body>
</html>