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
public class DBInsert extends HttpServlet {

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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DBTester</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DBTester at " + request.getContextPath() + "</h1>");
            dbcp.ConnectionManager.init();
            String[] fields = {"1", "Monterrey"};
            if (dbcp.ConnectionManager.insert(fields, "Tbl_City_GroupNo")) {
                out.println("Se ejecutó Insert");
            }
            String[][] pairs_1 = {{"PaymentId", "BookingId", "TotalCharges", "TotalTaxAmount", "DiscountAvailable"}, {"", "1", "123.52", "159,41", "2"}};
            if (dbcp.ConnectionManager.insert(pairs_1, "Tbl_Vehicle_Payment_GroupNo")) {
                out.println("Se ejecute Insert");
            }
            String[][] pairs_2 = {{"PaymentId", "BookingId", "TotalCharges", "TotalTaxAmount", "DiscountAvailable"}, {"", "1", "123.52", "159,41", "2"}};
            int llave1 = dbcp.ConnectionManager.insertAndGetKey(pairs_2, "Tbl_Vehicle_Payment_GroupNo");
            out.println("Llave 1: " + llave1);
            String[] fields_1 = {"", "1", "12-01-2012"};
            if (dbcp.ConnectionManager.insert(fields_1, "Tbl_Customer_Hotel_Discounts_GroupNo")) {
                out.println("Se ejecute Insert");
            }
            String[][] pairs_3 = {{"FirstName", "LastName"}, {"Mario", "Castellanos"}};
            if (dbcp.ConnectionManager.insert(pairs_3, "Tbl_Customer_GroupNo")) {
                out.println("Se ejecute Insert");
            }
            String[][] pairs_4 = {{"FirstName", "LastName"}, {"Mario", "Castellanos"}};
            int llave2 = dbcp.ConnectionManager.insertAndGetKey(pairs_4, "Tbl_Customer_GroupNo");
            if (dbcp.ConnectionManager.update("NoOfDeluxRooms", "29", "Tbl_Hotel_Details_GroupNo", "")) {
                out.println("Funciona Update");
            }
            if (dbcp.ConnectionManager.update("NoOfDeluxRooms", "29", "Tbl_Hotel_Details_GroupNo", "NumberofDeluxRooms = 6")) {
                out.println("Funciona Update con Query");
            }
            String campos[][] = {{"NoOfDeluxRooms", "65"}, {"NoOfEXERooms", "99"}, {"Location", "Aqui"}};
            if (dbcp.ConnectionManager.update(campos, "Tbl_Hotel_Details_GroupNo", "WHERE NoofDeluxRooms = 29")) {
                out.println("Funciona Update con Queries");
            }
            if (dbcp.ConnectionManager.update(campos, "Tbl_Hotel_Details_GroupNo", "")) {
                out.println("Funciona Update sin Queries");
            }

            out.println("Se crearon las llaves: " + llave1 + " y " + llave2);
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
