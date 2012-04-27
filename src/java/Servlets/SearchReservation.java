/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Package_Booking;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alduhoo
 */
public class SearchReservation extends HttpServlet {

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

        String id = request.getParameter("bookingId");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String mail = request.getParameter("mail");

        Package_Booking booking;

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/reservaciones.jsp");

        if (id != null && !id.equals("")) {
            // buscar por ID
            Package_Booking pb = Package_Booking.getPackage_Booking(id);
            request.setAttribute("booking", pb);
            // redireccionar a jsp
            rd.forward(request, response);
        } else if (mail != null && !mail.equals("")) {
            // buscar por mail
            Package_Booking pb = Package_Booking.getPackage_BookingMail(mail);
            request.setAttribute("booking", pb);
            // redireccionar a jsp
            rd.forward(request, response);
        } else if (lastName != null && firstName != null) {
            // buscar por nombre completo            
            Package_Booking pb = Package_Booking.getPackage_Booking(lastName, firstName);
            request.setAttribute("booking", pb);
            // redireccionar a jsp
            rd.forward(request, response);
        } else {
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
            Logger.getLogger(SearchReservation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchReservation.class.getName()).log(Level.SEVERE, null, ex);
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
