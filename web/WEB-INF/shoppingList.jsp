<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}</p>
        <a href="shoppingList?action=logout">Logout</a>
        <h2>List</h2>
        <form action="shoppingList" method="POST">
            <label>Add item:</label>
            <input type="text" name="item" value="">
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <c:if test="${not empty itemList}">
            <form action="shoppingList" method="POST">
                <c:forEach var="itemList" items="${itemList}">
                    <input type="radio" name="item" value="${itemList}">${itemList}<br>
                </c:forEach>
                <br>
                <input type="submit" value="Delete">
                <input type="hidden" name="action" value="delete">
            </form>
        </c:if>
    </body>
</html>
