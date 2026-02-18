<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Car Wash Booking</title>
</head>
<body>

<h2>View Car Wash Booking</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewRecord"/>

    Vehicle Number:
    <input type="text" name="vehicleNumber" required/>
    <br><br>

    Booking Date:
    <input type="date" name="bookingDate" required/>
    <br><br>

    <input type="submit" value="View Booking"/>

</form>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
