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
public class Flight_Travellers {
    String firstName, lastName, travellerType;
    Flight_Booking booking;

    public Flight_Travellers() {
    }

    public Flight_Travellers(Flight_Booking booking, String firstName, String lastName, String travellerType) {
        this.booking = booking;
        this.firstName = firstName;
        this.lastName = lastName;
        this.travellerType = travellerType;
    }

    public Flight_Booking getBooking() {
        return booking;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTravellerType() {
        return travellerType;
    }

    public void setBooking(Flight_Booking booking) {
        this.booking = booking;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTravellerType(String travellerType) {
        this.travellerType = travellerType;
    }
     /**
     * Returns a Flight_Travellers object by receiving a BookingID
     *
     * @param BookingID BookingID of the object
     * @return Flight_Travellers object
     * @throws SQLException
     */
    public static Flight_Travellers getFlightTravellers(String id) throws SQLException{
        dbcp.ConnectionManager.init();
        ResultSet rs = dbcp.ConnectionManager.selectAllColumns("Tbl_Flight_Travellers_GroupNo", "BookingId = "+id);
        if(rs.next()){
            Flight_Travellers ft = new Flight_Travellers(Flight_Booking.getFlightBooking(rs.getString("BookingId")),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("TravellerType"));
            return ft;
        }else{
            return null;
        }
    }
}
