package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Customer_Discounts {

    private Customer _customer_Id;
    private Date _dateOfAvailing;
    private Time _timeOfAvailing;

    public Customer_Discounts(Customer _customer_Id, Date _dateOfAvailing, Time _timeOfAvailing) {
        this._customer_Id = _customer_Id;
        this._dateOfAvailing = _dateOfAvailing;
        this._timeOfAvailing = _timeOfAvailing;
    }

    public Customer getCustomer_Id() {
        return this._customer_Id;
    }

    public void setCustomer_Id(Customer aCustomer_Id) {
        this._customer_Id = aCustomer_Id;
    }
    
    public Date getDateOfAvailing() {
        return this._dateOfAvailing;
    }

    public void setDateOfAvailing(Date aDateOfAvailing) {
        this._dateOfAvailing = aDateOfAvailing;
    }

    public Time getTimeOfAvailing() {
        return this._timeOfAvailing;
    }

    public void setTimeOfAvailing(Time aTimeOfAvailing) {
        this._timeOfAvailing = aTimeOfAvailing;
    }

  
    /**
     * Method that returns an object cd from Customer_Discounts that receives _customerID as parameter
     * @param _customerID that contains the primary key of Customer
     * @return object cd from type Customer_Discounts
     * @throws SQLException  
     */
    public static Customer_Discounts getCustomerDiscounts(int _customerID) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Customer_Discounts", "Customer_Id= " + _customerID);
        if (rs.next()) {
            Customer_Discounts cd = new Customer_Discounts(Customer.getCustomer(rs.getInt("Customer_id")), (java.util.Date)(rs.getDate("DateOfAvailing")), rs.getTime("TimeOfAvailing"));
            return cd;
        } else {
            return null;
        }
    }


    /**
     * Method that returns an object cd from Customer_Discounts that receives _customerID and _bookingID as parameter
     * @param _customerID that contains the primary key of Customer
     * @param _bookingID to take bookingID
     * @return object cd from type Customer_Discounts
     * @throws SQLException  
     */
    public static Customer_Discounts getCustomerDiscounts(int _customerID, int _bookingID) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Customer_Discounts", "Customer_Id= " + _customerID+","+ "Booking_Id=" + _bookingID);
        if (rs.next()) {
            Customer_Discounts cd = new Customer_Discounts(Customer.getCustomer(rs.getInt("Customer_id")), (java.util.Date)(rs.getDate("DateOfAvailing")), rs.getTime("TimeOfAvailing"));
            return cd;
        } else {
            return null;
        }
    }
}