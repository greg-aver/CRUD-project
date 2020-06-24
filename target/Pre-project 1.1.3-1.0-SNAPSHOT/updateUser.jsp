<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.UserService" %>
<%@ page import="model.User" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    User user = UserService.getService()
            .getUserById(id);
%>
<html>
<head>
    <title>Update</title>
</head>
<body>
</form><form method="post" action="user/update">
    <b>Update user with id: <%= id %></b>
    <input name="id" type="hidden" value="<%= id %>">
    <input required name="name" type="text" placeholder="<%= user.getName() %>">
    <input required name="surname" type="text" placeholder="<%= user.getSurname() %>">
    <input required name="age" type="text" placeholder="<%= user.getAge() %>">
    <input required type="submit" value="Update the user">
</form>
<form action="/users/" method="get">
    <button>Cancel</button>
</form>
</body>
</html>
