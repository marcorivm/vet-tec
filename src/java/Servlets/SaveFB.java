/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Customer;
import Clases.Flight;
import Clases.Flight_Booking;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Game Maniac
 */
public class SaveFB extends HttpServlet {

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
            String customerId = request.getParameter("customerId");
            Customer customer = null;
            String flightToNo = request.getParameter("flightTo");
            Flight flightTo = null;
            String flightFromNo = request.getParameter("flightFrom");
            Flight flightFrom = null;
            String NoA = request.getParameter("adultos");
            String NoK = request.getParameter("ninos");
            String date1 = request.getParameter("date1");
            String date2 = request.getParameter("date2");

            if ((customerId != null) && (!customerId.isEmpty())) {
                customer = Customer.getCustomer(Integer.parseInt(customerId));
            } else {
                customer = Customer.getCustomer(1);
            }

            if ((flightToNo != null) && (!flightToNo.isEmpty())) {
                flightTo = Flight.getFlight(Integer.parseInt(flightToNo));
            }

            if ((flightFromNo != null) && (!flightFromNo.isEmpty())) {
                flightFrom = Flight.getFlight(Integer.parseInt(flightFromNo));
            }

            Flight_Booking fbTo = new Flight_Booking();

            fbTo.setCustomer(customer);
            fbTo.setNoOfAdults(Integer.parseInt(NoA));
            fbTo.setNoOfChildren(Integer.parseInt(NoK));
            fbTo.setFlight(flightTo);

            String[] dates = date1.split("-");
            Date DoJTo = Date.valueOf(dates[2] + "-" + dates[1] + "-" + dates[0]);
            fbTo.setDateOfJourney(DoJTo);
            dates = date2.split("-");
            DoJTo = Date.valueOf(dates[2] + "-" + dates[1] + "-" + dates[0]);
            fbTo.setDateOfBooking(DoJTo);

            Flight_Booking fbFrom = null;

            if (flightFrom != null) {
                fbFrom = new Flight_Booking();

                fbFrom.setCustomer(customer);
                fbFrom.setNoOfAdults(Integer.parseInt(NoA));
                fbFrom.setNoOfChildren(Integer.parseInt(NoK));
                fbFrom.setFlight(flightFrom);
                dates = date2.split("-");
                Date DoJFrom = Date.valueOf(dates[2] + "-" + dates[1] + "-" + dates[0]);
                fbFrom.setDateOfJourney(DoJFrom);
            }

            request.getSession().setAttribute("fbTo", fbTo);

            if (fbFrom != null) {
                request.getSession().setAttribute("fbFrom", fbFrom);
            }

            RequestDispatcher rd = request.getRequestDispatcher("PrepareOfr");
            rd.forward(request, response);


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
