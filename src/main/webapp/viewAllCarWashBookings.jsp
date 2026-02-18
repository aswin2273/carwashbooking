<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View All Bookings</title>
</head>
<body>

<h2>View All Car Wash Bookings</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewAllRecords"/>

    <input type="submit" value="View All Bookings"/>

</form>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
