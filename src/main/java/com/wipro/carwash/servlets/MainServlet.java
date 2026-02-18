package com.wipro.carwash.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wipro.carwash.bean.CarWashBookingBean;
import com.wipro.carwash.service.Administrator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Administrator admin = new Administrator();

    // ================= ADD RECORD =================
    public String addRecord(HttpServletRequest request) {

        try {
            CarWashBookingBean bean = new CarWashBookingBean();

            bean.setCustomerName(request.getParameter("customerName"));
            bean.setVehicleNumber(request.getParameter("vehicleNumber"));
            bean.setWashType(request.getParameter("washType"));
            bean.setTimeSlot(request.getParameter("timeSlot"));
            bean.setRemarks(request.getParameter("remarks"));

            String dateStr = request.getParameter("bookingDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            bean.setBookingDate(date);

            return admin.addRecord(bean);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    // ================= VIEW SINGLE RECORD =================
    public CarWashBookingBean viewRecord(HttpServletRequest request) {

        try {
            String vehicleNumber = request.getParameter("vehicleNumber");
            String dateStr = request.getParameter("bookingDate");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date bookingDate = sdf.parse(dateStr);

            return admin.viewRecord(vehicleNumber, bookingDate);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= VIEW ALL RECORDS =================
    public List<CarWashBookingBean> viewAllRecords(HttpServletRequest request) {
        return admin.viewAllRecords();
    }

    // ================= CONTROLLER METHOD =================
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if (operation.equals("newRecord")) {

            String result = addRecord(request);

            if (result.equals("FAIL") || result.equals("INVALID INPUT")
                    || result.equals("INVALID VEHICLE NUMBER")
                    || result.equals("INVALID WASH TYPE")
                    || result.equals("ALREADY EXISTS")) {

                response.sendRedirect("error.html");

            } else {
                response.sendRedirect("success.html");
            }

        } else if (operation.equals("viewRecord")) {

            CarWashBookingBean bean = viewRecord(request);

            if (bean == null) {

                request.setAttribute("message",
                        "No matching records exists! Please try again!");

                RequestDispatcher rd = request.getRequestDispatcher(
                        "displayCarWashBooking.jsp");
                rd.forward(request, response);

            } else {

                request.setAttribute("booking", bean);

                RequestDispatcher rd = request.getRequestDispatcher(
                        "displayCarWashBooking.jsp");
                rd.forward(request, response);
            }

        } else if (operation.equals("viewAllRecords")) {

            List<CarWashBookingBean> list = viewAllRecords(request);

            if (list == null || list.isEmpty()) {

                request.setAttribute("message",
                        "No records available!");

                RequestDispatcher rd = request.getRequestDispatcher(
                        "displayAllCarWashBookings.jsp");
                rd.forward(request, response);

            } else {

                request.setAttribute("bookingList", list);

                RequestDispatcher rd = request.getRequestDispatcher(
                        "displayAllCarWashBookings.jsp");
                rd.forward(request, response);
            }
        }
    }
}
