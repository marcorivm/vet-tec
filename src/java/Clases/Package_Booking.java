/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

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
    
    
}
