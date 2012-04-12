package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hotel_Payment {

    private int _paymentId;
    private Hotel_Booking _bookingId;
    private double _totalCharges;
    private double _totalTaxAmount;
    private int _discountAvailed;

    public Hotel_Payment(int _paymentId, Hotel_Booking _bookingId, double _totalCharges, double _totalTaxAmount, int _discountAvailed) {
        this._paymentId = _paymentId;
        this._bookingId = _bookingId;
        this._totalCharges = _totalCharges;
        this._totalTaxAmount = _totalTaxAmount;
        this._discountAvailed = _discountAvailed;
    }

    
    public int getPaymentId() {
        return this._paymentId;
    }

    public void setPaymentId(int aPaymentId) {
        this._paymentId = aPaymentId;
    }

    public Hotel_Booking getBookingId() {
        return this._bookingId;
    }

    public void setBookingId(Hotel_Booking aBookingId) {
        this._bookingId = aBookingId;
    }

    public double getTotalCharges() {
        return this._totalCharges;
    }

    public void setTotalCharges(double aTotalCharges) {
        this._totalCharges = aTotalCharges;
    }

    public double getTotalTaxAmount() {
        return this._totalTaxAmount;
    }

    public void setTotalTaxAmount(double aTotalTaxAmount) {
        this._totalTaxAmount = aTotalTaxAmount;
    }

    public int getDiscountAvailed() {
        return this._discountAvailed;
    }

    public void setDiscountAvailed(int aDiscountAvailed) {
        this._discountAvailed = aDiscountAvailed;
    }

    /**
     * Method that receives _bookingId as parameter and returns an array of
     * Hotel_Payment
     *
     * @param _bookingId to receive _bookingId from Hotel_Booking
     * @return hp array that contains Hotel_Payment
     * @throws SQLException
     */
    public static Hotel_Payment[] getHotel_Payment(Hotel_Booking _bookingId) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Hotel_Booking", "BookingId= " + _bookingId);
        if (rs.next()) {
            ArrayList Hotel_PaymentArrayList = new ArrayList();
            Hotel_Payment hp[];
            do {
                Hotel_PaymentArrayList.add(new Hotel_Payment(rs.getInt("PaymentId"), Hotel_Booking.getHotelBooking(rs.getInt("_bookingID")), rs.getDouble("TotalCharges"), rs.getDouble("TotalTaxAmount"), rs.getInt("DiscountAvailed")));
            } while (rs.next());
            hp = (Hotel_Payment[]) Hotel_PaymentArrayList.toArray();
            return hp;
        } else {
            return null;
        }

    }

    /**
     * Method that receives _paymentId and returns an object of Hotel_Payment
     *
     * @param _paymentID that contains the primary key of Hotel_Payment
     * @return hp object of Hotel_Payment
     * @throws SQLException
     */
    public static Hotel_Payment getHotel_Payment(int _paymentId) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Hotel_Payment", "PaymentId= " + _paymentId);
        if (rs.next()) {
            Hotel_Payment hp = new Hotel_Payment(rs.getInt("PaymentId"), Hotel_Booking.getHotelBooking(rs.getInt("_bookingID")), rs.getInt("TotalCharges"), rs.getInt("TotalTaxAmount"), rs.getInt("DiscountAvailed"));
            return hp;
        } else {
            return null;
        }

    }
}