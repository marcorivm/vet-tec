/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Fernando
 */
public class PrepareOfr extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd;
        PrintWriter out = response.getWriter();

        // Con los dos bookings mandar a package
        if (request.getSession().getAttribute("fbTo") != null && request.getSession().getAttribute("HB") != null) {
            rd = request.getRequestDispatcher("Package.jsp");
            rd.forward(request, response);
        }

        if (request.getSession().getAttribute("fbTo") != null) {
            // recibi un vuelo, checar que hoteles hay en esa ciudad
            Flight_Booking fbTo = (Flight_Booking) request.getSession().getAttribute("fbTo");
            Flight flight = fbTo.getFlight();
            City destino = fbTo.getFlight().getDestination();

            /**
             * Buscar Hoteles con misma ciudad destino
             */
            Hotel hotels[] = Hotel.getHotels(destino);
            request.setAttribute("hotels", hotels);

            rd = request.getRequestDispatcher("/offerHotel.jsp");
            rd.forward(request, response);

            /**
             * End buscar hoteles
             */
            /*
             * NOTA!!!! CHECAR QUE EL HOTEL BOOKING ESTE GUARDADO EN SESSION
             * BAJO EL NOMBRE HB!!!!
             */
        } else if (request.getSession().getAttribute("HB") != null) {
            // recibi reservacion hotel, buscar vuelos hacia esa ciudad
            Hotel_Booking hb = (Hotel_Booking) request.getSession().getAttribute("HB");
            City destino = hb.getHotel().getLocation();
            String code = destino.getCityCode();

            Flight ida[] = Flight.getFlightsTo(destino);
            Flight regreso[] = Flight.getFlightsFrom(destino);
            
            request.setAttribute("flights", ida);
            request.setAttribute("flights2", regreso);
            
            rd = request.getRequestDispatcher("/offerFlight.jsp");
            rd.forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PrepareOfr.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PrepareOfr.class.getName()).log(Level.SEVERE, null, ex);
        }
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
