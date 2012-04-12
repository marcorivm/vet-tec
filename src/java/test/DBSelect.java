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
public class DBSelect extends HttpServlet {
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
            ResultSet rs = dbcp.ConnectionManager.selectAllRows("Tbl_City_GroupNo");
            out.println("<table>");
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>"+rs.getString("CityCode") +"</td>");
                out.println("<td>"+rs.getString("CityName") +"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            String[] fields = {"HotelId", "HotelName", "Location"};
            rs = dbcp.ConnectionManager.select(fields, "Tbl_Hotel_Details_GroupNo", "");
            out.println("<table>");
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>"+rs.getInt("HotelId") +"</td>");
                out.println("<td>"+rs.getString("HotelName") +"</td>");
                out.println("<td>"+rs.getString("Location") +"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            String[] fields_1 = {"BookingId","TravelType","JourneyStartDate","FirstName","LastName"};
            
            rs = dbcp.ConnectionManager.select(fields_1, "Tbl_Vehicle_Booking_GroupNo v,Tbl_Customer_GroupNo c", "v.CustomerId = c.CustomerId");
            out.println("<table>");
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>"+rs.getInt("BookingId") +"</td>");
                out.println("<td>"+rs.getString("TravelType") +"</td>");
                out.println("<td>"+rs.getString("JourneyStartDate") +"</td>");
                out.println("<td>"+rs.getString("FirstName") +"</td>");
                out.println("<td>"+rs.getString("LastName") +"</td>");
                out.println("</tr>");
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
            Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
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
