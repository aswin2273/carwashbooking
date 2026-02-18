<%@ page import="com.wipro.carwash.bean.CarWashBookingBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Details</title>
</head>
<body>

<h2>Booking Details</h2>

<%
    CarWashBookingBean bean =
        (CarWashBookingBean) request.getAttribute("booking");

    String message = (String) request.getAttribute("message");

    if (bean != null) {
%>

Record ID: <%= bean.getRecordId() %><br>
Customer Name: <%= bean.getCustomerName() %><br>
Vehicle Number: <%= bean.getVehicleNumber() %><br>
Wash Type: <%= bean.getWashType() %><br>
Booking Date: <%= bean.getBookingDate() %><br>
Time Slot: <%= bean.getTimeSlot() %><br>
Remarks: <%= bean.getRemarks() %><br>

<%
    } else if (message != null) {
%>

<%= message %>

<%
    }
%>

<br><br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
