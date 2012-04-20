/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Game Maniac
 */
public class Package_Booking {
    private int ID;
    private Flight_Booking flightTo;
    private Flight_Booking flightFrom;
    private Hotel_Booking hotel;
    private double discount;
    private String name;
    private String lastName;
    private String email;
    
    public Package_Booking(){
        this.ID = -1;
        this.flightTo = null;
        this.flightFrom = null;
        this.discount = -1;
        this.hotel = null;
        this.name = "";
        this.lastName = "";
        this.email = "";
    }
    
    public Package_Booking(int id, Flight_Booking fTo, Flight_Booking fFrom,
            Hotel_Booking h, double d, String n, String ln, String em){
        this.ID = id;
        this.flightTo = fTo;
        this.flightFrom = fFrom;
        this.discount = d;
        this.hotel = h;
        this.name = n;
        this.lastName = ln;
        this.email = em;
    }
    
    public static Package_Booking getPackageByID(String ID){
        /*TODO Add method logic and return value*/
        return null;
    }
    
    public static Package_Booking getPackageByName(String name){
        /*TODO Add method logic and return value*/
        return null;
    }
    
    public static Package_Booking getPackageByLast(String lastName){
        /*TODO Add method logic and return value*/
        return null;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the flightTo
     */
    public Flight_Booking getFlightTo() {
        return flightTo;
    }

    /**
     * @param flightTo the flightTo to set
     */
    public void setFlightTo(Flight_Booking flightTo) {
        this.flightTo = flightTo;
    }

    /**
     * @return the flightFrom
     */
    public Flight_Booking getFlightFrom() {
        return flightFrom;
    }

    /**
     * @param flightFrom the flightFrom to set
     */
    public void setFlightFrom(Flight_Booking flightFrom) {
        this.flightFrom = flightFrom;
    }

    /**
     * @return the hotel
     */
    public Hotel_Booking getHotel() {
        return hotel;
    }

    /**
     * @param hotel the hotel to set
     */
    public void setHotel(Hotel_Booking hotel) {
        this.hotel = hotel;
    }

    /**
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
