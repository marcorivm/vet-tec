/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luis Fernando
 */
public class Flight_Payment {
    int paymentId, adultCharges, childCharges, totalTaxAmount;
    Flight_Booking booking;

    public Flight_Payment() {
        dbcp.ConnectionManager.init();
    }

    public Flight_Payment(int paymentId, int adultCharges, int childCharges, int totalTaxAmount, Flight_Booking booking) {
        this.paymentId = paymentId;
        this.adultCharges = adultCharges;
        this.childCharges = childCharges;
        this.totalTaxAmount = totalTaxAmount;
        this.booking = booking;
    }

    public int getAdultCharges() {
        return adultCharges;
    }

    public Flight_Booking getBooking() {
        return booking;
    }

    public int getChildCharges() {
        return childCharges;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setAdultCharges(int adultCharges) {
        this.adultCharges = adultCharges;
    }

    public void setBooking(Flight_Booking booking) {
        this.booking = booking;
    }

    public void setChildCharges(int childCharges) {
        this.childCharges = childCharges;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setTotalTaxAmount(int totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }
     /**
     * Returns a Flight_Payment object by receiving a PaymentID
     *
     * @param PaymentID PaymentID of the object
     * @return Flight_Payment object
     * @throws SQLException
     */
    public static Flight_Payment getFlightPayment(int id) throws SQLException{
        dbcp.ConnectionManager.init();
        ResultSet rs = dbcp.ConnectionManager.selectAllColumns("Tbl_Flight_Payment_GroupNo", "PaymentId = "+id);
        if(rs.next()){
            Flight_Payment fp = new Flight_Payment(rs.getInt("PaymentId"),rs.getInt("AdultCharges"),rs.getInt("ChildCharges"),rs.getInt("TotalTaxAmount"),Flight_Booking.getFlightBooking(rs.getString("BookingId")));
            return fp;
        }else{
            return null;
        }
        
    }
     /**
     * Returns a Flight_Payment object by receiving a bookingID
     *
     * @param BookingID BookingID of the object
     * @return Flight_Payment object
     * @throws SQLException
     */
    public static Flight_Payment getFlightPayment(String id) throws SQLException{
        ResultSet rs = dbcp.ConnectionManager.selectAllColumns("Tbl_Flight_Payment_GroupNo", "BookingId = "+id);
        if(rs.next()){
            Flight_Payment fp = new Flight_Payment(rs.getInt("PaymentId"),rs.getInt("AdultCharges"),rs.getInt("ChildCharges"),rs.getInt("TotalTaxAmount"),Flight_Booking.getFlightBooking(rs.getString("BookingId")));
            return fp;
        }else{
            return null;
        }
        
    }
}
