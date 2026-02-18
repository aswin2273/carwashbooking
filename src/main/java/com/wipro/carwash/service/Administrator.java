package com.wipro.carwash.service;

import java.util.Date;
import java.util.List;

import com.wipro.carwash.bean.CarWashBookingBean;
import com.wipro.carwash.dao.CarWashBookingDAO;
import com.wipro.carwash.util.InvalidInputException;

public class Administrator {

    CarWashBookingDAO dao = new CarWashBookingDAO();

    public String addRecord(CarWashBookingBean bean) {

        try {

            if (bean == null || bean.getCustomerName() == null || bean.getBookingDate() == null)
                throw new InvalidInputException();

            if (bean.getVehicleNumber().length() < 5)
                return "INVALID VEHICLE NUMBER";

            if (bean.getWashType() == null || bean.getWashType().isEmpty())
                return "INVALID WASH TYPE";

            if (dao.recordExists(bean.getVehicleNumber(), bean.getBookingDate()))
                return "ALREADY EXISTS";

            String id = dao.generateRecordID(bean.getVehicleNumber(), bean.getBookingDate());
            bean.setRecordId(id);

            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    public CarWashBookingBean viewRecord(String vehicleNumber, Date bookingDate) {
        return dao.fetchRecord(vehicleNumber, bookingDate);
    }

    public List<CarWashBookingBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
