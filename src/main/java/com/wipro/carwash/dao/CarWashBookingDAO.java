package com.wipro.carwash.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.wipro.carwash.bean.CarWashBookingBean;
import com.wipro.carwash.util.DBUtil;

public class CarWashBookingDAO {

    public String createRecord(CarWashBookingBean bean) {
        try (Connection con = DBUtil.getDBConnection()) {

            String sql = "INSERT INTO CARWASH_TB VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bean.getRecordId());
            ps.setString(2, bean.getCustomerName());
            ps.setString(3, bean.getVehicleNumber());
            ps.setString(4, bean.getWashType());
            ps.setDate(5, new java.sql.Date(bean.getBookingDate().getTime()));
            ps.setString(6, bean.getTimeSlot());
            ps.setString(7, bean.getRemarks());

            int rows = ps.executeUpdate();
            if (rows > 0)
                return bean.getRecordId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL";
    }

    public boolean recordExists(String vehicleNumber, Date bookingDate) {
        try (Connection con = DBUtil.getDBConnection()) {

            String sql = "SELECT * FROM CARWASH_TB WHERE VEHICLENUMBER=? AND BOOKING_DATE=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, vehicleNumber);
            ps.setDate(2, new java.sql.Date(bookingDate.getTime()));

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String generateRecordID(String vehicleNumber, Date bookingDate) {
        try (Connection con = DBUtil.getDBConnection()) {

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String datePart = format.format(bookingDate);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CARWASH_SEQ.NEXTVAL FROM DUAL");
            rs.next();
            int seq = rs.getInt(1);

            String vehiclePart = vehicleNumber.substring(0, 2).toUpperCase();

            return datePart + vehiclePart + seq;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CarWashBookingBean fetchRecord(String vehicleNumber, Date bookingDate) {
        try (Connection con = DBUtil.getDBConnection()) {

            String sql = "SELECT * FROM CARWASH_TB WHERE VEHICLENUMBER=? AND BOOKING_DATE=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, vehicleNumber);
            ps.setDate(2, new java.sql.Date(bookingDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CarWashBookingBean bean = new CarWashBookingBean();
                bean.setRecordId(rs.getString(1));
                bean.setCustomerName(rs.getString(2));
                bean.setVehicleNumber(rs.getString(3));
                bean.setWashType(rs.getString(4));
                bean.setBookingDate(rs.getDate(5));
                bean.setTimeSlot(rs.getString(6));
                bean.setRemarks(rs.getString(7));
                return bean;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarWashBookingBean> fetchAllRecords() {
        List<CarWashBookingBean> list = new ArrayList<>();

        try (Connection con = DBUtil.getDBConnection()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CARWASH_TB");

            while (rs.next()) {
                CarWashBookingBean bean = new CarWashBookingBean();
                bean.setRecordId(rs.getString(1));
                bean.setCustomerName(rs.getString(2));
                bean.setVehicleNumber(rs.getString(3));
                bean.setWashType(rs.getString(4));
                bean.setBookingDate(rs.getDate(5));
                bean.setTimeSlot(rs.getString(6));
                bean.setRemarks(rs.getString(7));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
