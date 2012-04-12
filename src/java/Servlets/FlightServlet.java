/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Flight;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erika
 */
@WebServlet(name = "FlightServlet", urlPatterns = {"/FlightServlet"})
public class FlightServlet extends HttpServlet {

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

        boolean isRoundTrip = request.getParameter("isRoundTrip").equalsIgnoreCase("true");
        String source = request.getParameter("source");
        String destiny = request.getParameter("destiny");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        int adults = Integer.parseInt(request.getParameter("adults"));
        int kids = Integer.parseInt(request.getParameter("kids"));
        try {

            Flight[] fs = Flight.getFlights(source, destiny, adults + kids);
            request.setAttribute("flights", fs);
            if (isRoundTrip) {
                Flight[] fs2 = Flight.getFlights(destiny, source, adults + kids);
                request.setAttribute("flights2", fs2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error (FlightServlet, Flight.getFlights(source, destiny, adults+kids); " + ex.getMessage());
        }

        RequestDispatcher rd = request.getRequestDispatcher("Ciudades");
        rd.forward(request, response);


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
