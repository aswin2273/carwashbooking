<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Car Wash Booking</title>
</head>
<body>

<h2>Add Car Wash Booking</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="newRecord"/>

    Customer Name:
    <input type="text" name="customerName" required/>
    <br><br>

    Vehicle Number:
    <input type="text" name="vehicleNumber" required/>
    <br><br>

    Wash Type:
    <select name="washType">
        <option value="Basic">Basic</option>
        <option value="Deluxe">Deluxe</option>
        <option value="Premium">Premium</option>
    </select>
    <br><br>

    Booking Date:
    <input type="date" name="bookingDate" required/>
    <br><br>

    Time Slot:
    <input type="text" name="timeSlot"/>
    <br><br>

    Remarks:
    <input type="text" name="remarks"/>
    <br><br>

    <input type="submit" value="Add Booking"/>

</form>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
