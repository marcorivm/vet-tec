/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbcp.ConnectionManager;
/**
 *
 * @author Luis Fernando
 */
public class Flight_Booking {
    String bookingId; 
    Date dateOfBooking, dateOfJourney;
    int noOfAdults, noOfChildren;
    Customer customer; 
    Flight flight;

    public Flight_Booking() {
        dbcp.ConnectionManager.init();
    }

    public Flight_Booking(String bookingId, Date dateOfBooking, Date dateOfJourney, Customer customer, Flight flight, int noOfAdults, int noOfChildren) {
        this.bookingId = bookingId;
        this.dateOfBooking = dateOfBooking;
        this.dateOfJourney = dateOfJourney;
        this.customer = customer;
        this.flight = flight;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public Date getDateOfJourney() {
        return dateOfJourney;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public void setDateOfJourney(Date dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }
    
     /**
     * Returns a Flight_Booking object by receiving the bookingID
     *
     * @param BookingID BookingID of the object
     * @return Flight_Booking object
     * @throws SQLException
     */
    public static Flight_Booking getFlightBooking(String id) throws SQLException{
        dbcp.ConnectionManager.init();
        ResultSet rs = dbcp.ConnectionManager.selectAllColumns("Tbl_Flight_Booking_GroupNo", "BookingId = "+id);
        
        if(rs.next()){
            Flight_Booking fb = new Flight_Booking(id,rs.getDate("DateOfBooking"),rs.getDate("DateOfJourney"), Customer.getCustomer(rs.getInt("CustomerId")),Flight.getFlight(rs.getInt("FlightNo")),rs.getInt("NoOfAdults"),rs.getInt("NoOfChildren"));
            return fb;
        }else{
            return null;
        }
    }
    
     /**
     * Returns a Flight_Booking object by receiving the bookingID and CustomerID
     *
     * @param BookingID BookingID of the object
     * @param CustomerID CustomerID of the object
     * @return Flight_Booking object
     * @throws SQLException
     */
    public static Flight_Booking getFlightBooking(String id, int customerid) throws SQLException{
        dbcp.ConnectionManager.init();
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Flight_Booking_GroupNo", "BookingId = "+id+ " CustomerId = "+customerid);
        
        if(rs.next()){
            Flight_Booking fb = new Flight_Booking(id,rs.getDate("DateOfBooking"),rs.getDate("DateOfJourney"), Customer.getCustomer(rs.getInt("CustomerId")),Flight.getFlight(rs.getInt("FlightNo")),rs.getInt("NoOfAdults"),rs.getInt("NoOfChildren"));
            return fb;
        }else{
            return null;
        }
    }
    
    /**
     * Method that returns the next available id for a Flight Booking.
     * @return next available id B0000
     * @throws SQLException 
     */
    public static String getNextId() throws SQLException {
        ResultSet rd = ConnectionManager.select("CONCAT(  'B', (MID( BookingId, 2 ) +1 ))", "FROM  `Tbl_Flight_Booking_GroupNo` ", "1 ORDER BY BookingId DESC LIMIT 1");
        if(rd.next()){
            return  rd.getString(1);
        } else {
            return null; 
        }
    }
}
