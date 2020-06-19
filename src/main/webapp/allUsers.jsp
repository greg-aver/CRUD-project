<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of all users</title>
</head>
<body>
<p><b>List of all users</b></p>

<form>
    <table border="2">
        <tr>
            <td></td>
            <td> ID </td>
            <td> Name </td>
            <td> Surname </td>
            <td> Age </td>
        </tr>
        <c:forEach items="${users}" var="user">
            <label>
            <tr>
                <td><input name="id" type="radio" value="${user.getId()}"></td>
                <td>${user.getId()}</td>
                <td>${user.getName()}</td>
                <td>${user.getSurname()}</td>
                <td>${user.getAge()}</td>
            </tr>
            </label>
        </c:forEach>
    </table>
    <p><button formmethod="post" formaction="/updateUser.jsp" type="submit">Update</button></p>
    <p><button formmethod="post" formaction="/deleteUser.jsp" type="submit">Delete</button></p>
</form>
<form>
<button formaction="/addUser.jsp" type="submit">Add new user</button></p>
</form>
</body>
</html>
