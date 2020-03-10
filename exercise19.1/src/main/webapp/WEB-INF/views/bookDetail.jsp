<%--
  Created by IntelliJ IDEA.
  User: jimka
  Date: 3/9/2020
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add a Book</title>
</head>
<body>
<form action="../books/${book.id}" method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" value="${book.title}" /> </td>
        </tr>
        <tr>
            <td>ISBN:</td>
            <td><input type="text" name="isbn" value="${book.isbn}" /> </td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><input type="text" name="author" value="${book.author}" /> </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" value="${book.price}" /> </td>
        </tr>
    </table>
    <input type="submit" value="update"/>
</form>
<form action="delete?carId=${book.id}" method="post">
    <button type="submit">Delete</button>
</form>
</body>
</html>