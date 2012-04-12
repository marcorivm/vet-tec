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
public class DBUpdates extends HttpServlet {

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
            if (dbcp.ConnectionManager.update("NoOfDeluxRooms", "29", "Tbl_Hotel_Details_GroupNo", ""))  {
                out.println("Funciona Update de NoOfDeluxeRooms a toda la tabla en 29");
            } else {
                out.println("No funciona Update de NoOfDeluxeRooms a toda la tabla en 29");
            }
            if (dbcp.ConnectionManager.update("NoOfDeluxRooms", "31", "Tbl_Hotel_Details_GroupNo", "NumberofDeluxRooms = 6"))  {
                out.println("Funciona Update de NoOfDeluxeRooms a toda la tabla en 31 donde NumberofDeluxRooms = 6");
            } else {
                out.println("No funciona Update de NoOfDeluxeRooms a toda la tabla en 31 donde NumberofDeluxRooms = 6");
            }
            String[][] campos = {{"NoOfDeluxRooms", "65"}, {"NoOfEXERooms", "99"}, {"Location", "Aqui"}};
            if (dbcp.ConnectionManager.update(campos, "Tbl_Hotel_Details_GroupNo", "WHERE NoofDeluxRooms = 29")) {
                out.println("Funciona Update por medio de arreglo bidimensional condicionado");
            } else {
                out.println("No funciona Update por medio de arreglo bidimensional condicionado");
            }
            if (dbcp.ConnectionManager.update(campos, "Tbl_Hotel_Details_GroupNo","")) {
                out.println("Funciona Update por medio de arreglo bidimensional");
            } else {
                out.println("No Funciona Update por medio de arreglo bidimensional");
            }
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
