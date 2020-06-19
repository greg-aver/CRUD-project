<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<form action="/users/delete" method="post">
<b>Delete user with id: <%= request.getParameter("id") %> </b>
<input type="hidden" name="id" value="<%= request.getParameter("id") %>">
<button>Delete</button>
</form>
<form action="/users/" method="get">
    <button>Cancel</button>
</form>
</body>
</html>
