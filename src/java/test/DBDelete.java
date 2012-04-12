/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marco
 */
public class DBDelete extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DBTester</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DBTester at " + request.getContextPath() + "</h1>");
            dbcp.ConnectionManager.init();
            if (dbcp.ConnectionManager.delete("Tbl_City_GroupNo", "CityCode = 1")) {
                out.println("Borra columnas donde CityCode = 1");
            } else {
                out.println("No borra columnas donde CityCode = 1");
            }
            if (dbcp.ConnectionManager.delete("Tbl_Vehicle_Payment_GroupNo", "BookingId = 1")) {
                out.println("Borra columnas donde BookingId = 1");
            } else {
                out.println("No borra columnas donde BookingId = 1");
            }
            if (dbcp.ConnectionManager.delete("Tbl_Customer_Hotel_Discounts_GroupNo", "DateofBooking = 12-01-2012")) {
                out.println("Borra columnas donde DateofBooking = 12-01-2012");
            } else {
                out.println("No borra columnas donde DateofBooking = 12-01-2012");
            }
            if (dbcp.ConnectionManager.delete("Tbl_Customer_GroupNo", "FirstName = 'Mario'")) {
                out.println("Borra columnas con Mario como primer nombre");
            } else {
                out.println("No borra columnas con Mario como primer nombre");
            }
            if (dbcp.ConnectionManager.deleteAll("Tbl_Customer_GroupNo")) {
                out.println("Se ejecuto todo el delete");
            } else {
                out.println("No se ejecuto todo el delete");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
            Logger.getLogger(DBSelect.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBSelect.class.getName()).log(Level.SEVERE, null, ex);
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
