package Clases;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luis Fernando
 */
public class Hotel_Booking {
    int bookingId, noOfDeluxeRooms, noOfExeRooms;
    Hotel hotel;
    Customer customer;
    Date dateOfBooking, checkInDate, checkOutDate;

    public Hotel_Booking(int bookingId, int noOfDeluxeRooms, int noOfExeRooms, Hotel hotel, Customer customer, Date dateOfBooking, Date checkInDate, Date checkOutDate) {
        this.bookingId = bookingId;
        this.noOfDeluxeRooms = noOfDeluxeRooms;
        this.noOfExeRooms = noOfExeRooms;
        this.hotel = hotel;
        this.customer = customer;
        this.dateOfBooking = dateOfBooking;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getNoOfDeluxeRooms() {
        return noOfDeluxeRooms;
    }

    public void setNoOfDeluxeRooms(int noOfDeluxeRooms) {
        this.noOfDeluxeRooms = noOfDeluxeRooms;
    }

    public int getNoOfExeRooms() {
        return noOfExeRooms;
    }

    public void setNoOfExeRooms(int noOfExeRooms) {
        this.noOfExeRooms = noOfExeRooms;
    }
    
    /**
     * Returns an array of Hotel_Booking objects by receiving the customerID
     *
     * @param customerID customerID of the object
     * @return array of Hotel_Booking objects
     * @throws SQLException
     */
    public static Hotel_Booking[] getHotelBookings(int customerId) throws SQLException{
        dbcp.ConnectionManager.init();
        ResultSet rs = dbcp.ConnectionManager.selectAllColumns("Tbl_Hotel_Booking_GroupNo", "CustomerId = "+customerId);
        Hotel_Booking[] hb;
        
        if(rs.getFetchSize() > 0){
            hb = new Hotel_Booking[rs.getFetchSize()];
            for(int i = 0; i < hb.length; i++){
                rs.next();
                hb[i] = new Hotel_Booking(rs.getInt("BookingId"), rs.getInt("NoOfDeluxeRooms"), 
                                          rs.getInt("NoOfExeRooms"), Hotel.getHotel(rs.getString("HotelId")), 
                                          Customer.getCustomer(rs.getInt("CustomerId")), rs.getDate("DateOfBooking"), 
                                          rs.getDate("CheckInDate"), rs.getDate("CheckOutDate"));
                
            }
            return hb;
        }else{
            return null;
        }
    }
    
    /**
     * Returns an array of Hotel_Booking objects by receiving the hotelID
     *
     * @param hotelID hotelID of the object
     * @return array of Hotel_Booking objects
     * @throws SQLException
     */
    public static Hotel_Booking[] getHotelBookings(String hotelId) throws SQLException{
        dbcp.ConnectionManager.init();
        ResultSet rs = dbcp.ConnectionManager.selectAllColumns("Tbl_Hotel_Booking_GroupNo", "HotelId = "+hotelId);
        Hotel_Booking[] hb;
        
        if(rs.getFetchSize() > 0){
            hb = new Hotel_Booking[rs.getFetchSize()];
            for(int i = 0; i < hb.length; i++){
                rs.next();
                hb[i] = new Hotel_Booking(rs.getInt("BookingId"), rs.getInt("NoOfDeluxeRooms"), 
                                          rs.getInt("NoOfExeRooms"), Hotel.getHotel(rs.getString("HotelId")), 
                                          Customer.getCustomer(rs.getInt("CustomerId")), rs.getDate("DateOfBooking"), 
                                          rs.getDate("CheckInDate"), rs.getDate("CheckOutDate"));
                
            }
            return hb;
        }else{
            return null;
        }
    }
    
     /**
     * Returns a Hotel_Booking object by receiving the bookingID
     *
     * @param BookingID BookingID of the object
     * @return Hotel_Booking object
     * @throws SQLException
     */
    public static Hotel_Booking getHotelBooking(int bookingId) throws SQLException{
        dbcp.ConnectionManager.init();
        ResultSet rs = dbcp.ConnectionManager.selectAllColumns("Tbl_Hotel_Booking_GroupNo", "BookingId = "+bookingId);
        Hotel_Booking hb;
        
        if(rs.next()){
            hb = new Hotel_Booking(rs.getInt("BookingId"), rs.getInt("NoOfDeluxeRooms"), 
                                          rs.getInt("NoOfExeRooms"), Hotel.getHotel(rs.getString("HotelId")), 
                                          Customer.getCustomer(rs.getInt("CustomerId")), rs.getDate("DateOfBooking"), 
                                          rs.getDate("CheckInDate"), rs.getDate("CheckOutDate"));
            return hb;
        }else{
            return null;
        }
    }
}
