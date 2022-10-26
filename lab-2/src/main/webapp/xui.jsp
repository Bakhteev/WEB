<%@ page import="com.example.lab2.models.Point" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.example.lab2.state.StateBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.toString();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="/">back</a>
</div>
<div>
    <% if (response.getStatus() == 400) { %>
    <h2 style="font-size: 30px; color: red; text-align: center">
        ${error}
    </h2>
    <% }%>
</div>
<%
    StateBean points = (StateBean) request.getAttribute("points");
    for (Point point : points.getList()) {
%>
<div style="display:flex;">
    <div style="margin-right: 10px">
        <%=point.getX()%>
    </div>
    <div style="margin-right: 10px">
        <%=point.getY()%>
    </div>
    <div style="margin-right: 10px">
        <%=point.getR()%>
    </div>
    <div style="margin-right: 10px">
        <%=point.isHitted() ? "true" : "false"%>
    </div>
</div>
<%}%>
</body>
</html>
