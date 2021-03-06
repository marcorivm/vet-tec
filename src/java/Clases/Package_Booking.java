/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marco
 */
public class Package_Booking {

    private String id;
    private Flight_Booking flightTo;
    private Flight_Booking flightFrom;
    private Hotel_Booking hotel;
    private double discount;
    private String name;
    private String lastName;
    private String email;

    public Package_Booking(String id, Flight_Booking flightTo, Flight_Booking flightFrom, Hotel_Booking hotel, double discount, String name, String lastName, String email) {
        this.id = id;
        this.flightTo = flightTo;
        this.flightFrom = flightFrom;
        this.hotel = hotel;
        this.discount = discount;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Package_Booking() {
        this.id = null;
        this.flightTo = null;
        this.flightFrom = null;
        this.hotel = null;
        this.discount = 0.0;
        this.name = null;
        this.lastName = null;
        this.email = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Flight_Booking getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(Flight_Booking flightFrom) {
        this.flightFrom = flightFrom;
    }

    public Flight_Booking getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(Flight_Booking flightTo) {
        this.flightTo = flightTo;
    }

    public Hotel_Booking getHotel() {
        return hotel;
    }

    public void setHotel(Hotel_Booking hotel) {
        this.hotel = hotel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that returns a Package_Booking object based on the id passed as a
     * parameter
     *
     * @param id ID used to look up a Package_Booking
     * @return
     * @throws SQLException
     */
    public static Package_Booking getPackage_Booking(String id) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Packages", "idTbl_Packages='" + id + "'");
        if (rs.next()) {
            Package_Booking pb = new Package_Booking(id, Flight_Booking.getFlightBooking(rs.getString("Tbl_Flight_Booking_GroupNo_BookingId")), Flight_Booking.getFlightBooking(rs.getString("Tbl_Flight_Booking_GroupNo_BookingId1")), Hotel_Booking.getHotelBooking(rs.getInt("Tbl_Hotel_Booking_GroupNo_BookingId")), rs.getFloat("Discount"), rs.getString("Name"), rs.getString("LastName"), rs.getString("Email"));
            return pb;
        } else {
            return null;
        }
    }

    /**
     * Method that searches for and returns a Package_Booking object using an
     * e-mail as reference
     *
     * @param mail The mail address used for the search
     * @return Package_Booking object
     * @throws SQLException
     */
    public static Package_Booking[] getPackage_BookingMail(String mail) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Packages", "Email='" + mail + "'");
        
        if (rs.next()) {
            ArrayList pbArrayList = new ArrayList();
            Package_Booking pb[];
            do {
                pbArrayList.add(new Package_Booking(rs.getString("idTbl_Packages"), Flight_Booking.getFlightBooking(rs.getString("Tbl_Flight_Booking_GroupNo_BookingId")), Flight_Booking.getFlightBooking(rs.getString("Tbl_Flight_Booking_GroupNo_BookingId1")), Hotel_Booking.getHotelBooking(rs.getInt("Tbl_Hotel_Booking_GroupNo_BookingId")), rs.getFloat("Discount"), rs.getString("Name"), rs.getString("LastName"), mail));
            } while (rs.next());
            if(pbArrayList.size()>0){
                pb = new Package_Booking[pbArrayList.size()];
                pbArrayList.toArray(pb);
                return pb;
            }            
        }
        return null;
    }

    /**
     * Method that searches and returns a Package_Booking object using the
     * person's last name and first name to search in the database.
     *
     * @param lastName Last name used in search
     * @param firstName Fisrt name used in search
     * @return Package_Booking object that matched search
     * @throws SQLException
     */
    public static Package_Booking[] getPackage_Booking(String lastName, String firstName) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Packages", "Name='" + firstName + "' AND LastName ='" + lastName + "'");
        
        if (rs.next()) {
            ArrayList pbArrayList = new ArrayList();
            Package_Booking pb[];
            do {
                pbArrayList.add(new Package_Booking(rs.getString("idTbl_Packages"), Flight_Booking.getFlightBooking(rs.getString("Tbl_Flight_Booking_GroupNo_BookingId")), Flight_Booking.getFlightBooking(rs.getString("Tbl_Flight_Booking_GroupNo_BookingId1")), Hotel_Booking.getHotelBooking(rs.getInt("Tbl_Hotel_Booking_GroupNo_BookingId")), rs.getFloat("Discount"), rs.getString("Name"), rs.getString("LastName"), rs.getString("Email")));
            } while (rs.next());
            if(pbArrayList.size()>0){
                pb = new Package_Booking[pbArrayList.size()];
                pbArrayList.toArray(pb);
                return pb;
            }            
        }
        return null;
    }
    
}
