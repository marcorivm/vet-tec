/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Flight_Booking;
import Clases.Hotel_Booking;
import Clases.Package_Booking;
import dbcp.ConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Game Maniac
 */
public class ReservarP extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Flight_Booking fbTo = (Flight_Booking) request.getSession().getAttribute("fbTo");
            String fbToId = "";
            Flight_Booking fbFrom = (Flight_Booking) request.getSession().getAttribute("fbFrom");
            String fbFromId = "";
            Hotel_Booking hotel = (Hotel_Booking) request.getSession().getAttribute("hb");
            String hotelId = "";

            double discount = 0.0;
            Calendar today = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
            String DoB = formater.format(today.getTime());
            String name = (String) request.getAttribute("name");
            String lastName = (String) request.getAttribute("lastName");
            String email = (String) request.getAttribute("email");

            Connection con;

            con = ConnectionManager.initTransaction();

            try {
                con = ConnectionManager.initTransaction();
                boolean isOk = true;


                if (fbTo != null) {
                    fbTo.setDateOfBooking(new java.sql.Date(today.getTimeInMillis()));
                    String key = Flight_Booking.getNextId();
                    fbTo.setBookingId(key);
                    fbToId = fbTo.getBookingId();
                    String[][] fields = {
                        {"BookingId", key},
                        {"FlightNo", fbTo.getFlight().getFlight_No() + ""},
                        {"CustomerId", fbTo.getCustomer().getCustomerID() + ""},
                        {"DateOfBooking", DoB},
                        {"DateOfJourney", fbTo.getDateOfJourney() + ""},
                        {"NoOfAdults", fbTo.getNoOfAdults() + ""},
                        {"NoOfChildren", fbTo.getNoOfChildren() + ""}
                    };

                    isOk = ConnectionManager.insert(fields, "Tbl_Flight_Booking_GroupNo", con);
                    if (!isOk) {
                        throw new SQLException("Error registering Flight_Booking To ");
                    }

                    // update flightseat_status
                    int remainingSeats = fbTo.getFlight().getRemainingSeats();
                    remainingSeats -= fbTo.getNoOfAdults() + fbTo.getNoOfChildren();
                    String fields2[][] = {
                        {"FlightNo", fbTo.getFlight().getFlight_No() + ""},
                        {"DateOfJourney", fbTo.getDateOfJourney() + ""},
                        {"RemainingSeats", remainingSeats + ""}
                    };

                    isOk = ConnectionManager.update(fields2, "Tbl_FlightSeat_Status_GroupNo",
                            "FlightNo = '" + fbTo.getFlight().getFlight_No() + "' AND DATE(DateOfJourney) = '"
                            + fbTo.getDateOfJourney() + "'", con);
                    if (!isOk) {
                        throw new SQLException("Error updating FlightSeat_Status");
                    }
                }



                if (fbFrom != null) {
                    fbFrom.setDateOfBooking(new java.sql.Date(today.getTimeInMillis()));
                    String key = Flight_Booking.getNextId();
                    fbFrom.setBookingId(key);
                    fbFromId = fbFrom.getBookingId();
                    String[][] fields = {
                        {"BookingId", key},
                        {"FlightNo", fbFrom.getFlight().getFlight_No() + ""},
                        {"CustomerId", fbFrom.getCustomer().getCustomerID() + ""},
                        {"DateOfBooking", DoB},
                        {"DateOfJourney", fbFrom.getDateOfJourney() + ""},
                        {"NoOfAdults", fbFrom.getNoOfAdults() + ""},
                        {"NoOfChildren", fbFrom.getNoOfChildren() + ""}
                    };

                    isOk = ConnectionManager.insert(fields, "Tbl_Flight_Booking_GroupNo", con);
                    if (!isOk) {
                        throw new SQLException("Error registering Flight_Booking From ");
                    }

                    // update flightseat_status
                    int remainingSeats = fbFrom.getFlight().getRemainingSeats();
                    remainingSeats -= fbFrom.getNoOfAdults() + fbFrom.getNoOfChildren();
                    String fields2[][] = {
                        {"FlightNo", fbFrom.getFlight().getFlight_No() + ""},
                        {"DateOfJourney", fbFrom.getDateOfJourney() + ""},
                        {"RemainingSeats", remainingSeats + ""}
                    };

                    isOk = ConnectionManager.update(fields2, "Tbl_FlightSeat_Status_GroupNo",
                            "FlightNo = '" + fbFrom.getFlight().getFlight_No() + "' AND DATE(DateOfJourney) = '"
                            + fbFrom.getDateOfJourney() + "'", con);
                    if (!isOk) {
                        throw new SQLException("Error updating FlightSeat_Status");
                    }
                }



                if (hotel != null) {
                    hotel.setDateOfBooking(new java.sql.Date(today.getTimeInMillis()));
                    String key = Hotel_Booking.getNextId();
                    hotel.setBookingId(key);
                    hotelId = hotel.getBookingId();
                    String[][] fields = {
                        {"BookingId", key},
                        {"HotelId", hotel.getHotel().getHotelId()},
                        {"CustomerId", hotel.getCustomer().getCustomerID() + ""},
                        {"DateOfBooking", DoB},
                        {"CheckInDate", formater.format(hotel.getCheckInDate())},
                        {"CheckOutDate", formater.format(hotel.getCheckInDate())},
                        {"NoOfDeluxRooms", hotel.getNoOfDeluxeRooms() + ""},
                        {"NoOfExeRooms", hotel.getNoOfExeRooms() + ""},};

                    isOk = ConnectionManager.insert(fields, "Tbl_Hotel_Booking_GroupNo", con);

                    if (!isOk) {
                        throw new SQLException("Error registering Hotel_Booking ");
                    }
                    
                    //Update Tbl_Hotel_Calendar
                    String fields3[][] = {
                        {"HotelId","HotelId"},
                        {"Dia", "Dia"},
                        {"CuartosExec", "CuartosExec + " + (hotel.getNoOfExeRooms()>0)},
                        {"CuartosDelux","CuartosDelux + " + (hotel.getNoOfDeluxeRooms()>0)},
                    };
                    
                    isOk = ConnectionManager.update(fields3, "Tbl_Hotel_Calendar",
                            "HotelId='"+hotel.getHotel().getHotelId()+"'", con);
                    if (!isOk) {
                        throw new SQLException("Error updating Tbl_Hotel_Calendar");
                    }
                    
                }

                Package_Booking pb = new Package_Booking();

                pb.setFlightFrom(fbFrom);
                pb.setFlightTo(fbTo);
                pb.setHotel(hotel);
                pb.setName(name);
                pb.setLastName(lastName);
                pb.setEmail(email);
                String disc = (String) request.getAttribute("discount");
                if (disc != null) {
                    discount = Double.parseDouble(disc);
                } else {
                    discount = 0.0;
                }
                pb.setDiscount(discount);

                String[][] fields = {
                    {"Tbl_Flight_Booking_GroupNo_BookingId", (fbToId.isEmpty()) ? "NULL" : "'" + fbToId + "'"},
                    {"Tbl_Flight_Booking_GroupNo_BookingId1", (fbFromId.isEmpty()) ? "NULL" : "'" + fbFromId + "'"},
                    {"Tbl_Hotel_Booking_GroupNo_BookingId", (hotelId.isEmpty()) ? "NULL" : "'" + hotelId + "'"},
                    {"Discount", discount + ""},
                    {"LastName", "'" + lastName + "'"},
                    {"Name", "'" + name + "'"},
                    {"Email", "'" + email + "'"},};
                
                
                /*
                String[][] fieldsFrom = {
                    {"PaymentId", (fbToId.isEmpty()) ? "NULL" : "'" + fbToId + "'"},
                    {"_bookingId", (fbToId.isEmpty()) ? "NULL" : "'" + fbToId + "'"},
                    {"TotalCharges", (hotelId.isEmpty()) ? "NULL" : "'" + hotelId + "'"},
                    {"TotalTaxAmount", flight.getDiscount(fbToId, ) ""},
                    {"DiscountAvailed", }};*/
                
                
                int id = ConnectionManager.insertAndGetKey(fields, "Tbl_Packages", con);
                
                //Se insertan tablas de los diferentes payments
               /* ConnectionManager.insert(fieldsFrom, "Tbl_Flight_Payment_GroupNo");
                ConnectionManager.insert(fieldsTo, "Tbl_Flight_Payment_GroupNo");
                ConnectionManager.insert(fieldsHotel, "Tbl_Hotel_Payment_GroupNo");*/
                
                isOk = (id > 0);
                

                if (!isOk) {
                    throw new SQLException("Error registering Package_Booking ");
                }
                ConnectionManager.commit(con);

                request.setAttribute("msg", "success");
                request.setAttribute("Package", pb);
                String folio = pb.getId() + pb.getLastName().hashCode();
                request.setAttribute("folio", folio);
                RequestDispatcher rd = request.getRequestDispatcher("Confirmacion.jsp");
                rd.forward(request, response);



            } catch (SQLException e) {
                ConnectionManager.rollback(con);
                request.setAttribute("msg", "No se pudo completar el registro. Intentelo m&aacute;s tarde");
                RequestDispatcher rd = request.getRequestDispatcher("Confirmacion.jsp");
                rd.forward(request, response);
            }



        } catch (SQLException ex) {
            Logger.getLogger(ReservarP.class.getName()).log(Level.SEVERE, null, ex);

        }



    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
