<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<b>Add new user!</b>
<form action="users/add" method="post">
    <input required type="text" name="name" placeholder="Name user">
    <input required type="text" name="surname" placeholder="Surname user">
    <input required type="text" name="age" placeholder="Age user">
    <input type="submit" value="Add">
</form>
<form action="/users/" method="get">
<button>Cancel</button>
</form>
</body>
</html>
