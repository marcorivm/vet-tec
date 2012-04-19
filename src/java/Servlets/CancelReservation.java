/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Flight_Booking;
import dbcp.ConnectionManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Administrador
 */
public class CancelReservation extends HttpServlet {

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
        //PrintWriter out = response.getWriter();
        
        //  remove item
        String idreservacion = request.getParameter("reservacion");
        HttpSession session = request.getSession();
        boolean exitoso;
        
        // Get the cart
        Flight_Booking mi_vueloReservado = (Flight_Booking) session.
            getAttribute("vueloReservado");

       if (mi_vueloReservado == null){
		  mi_vueloReservado = new Flight_Booking();
		  session.setAttribute("vueloReservado", mi_vueloReservado);
       }
       else{
           try{
               ConnectionManager.init();
                 
               if(idreservacion.equals(mi_vueloReservado.getBookingId())){
                    exitoso = ConnectionManager.delete("Tbl_Flight_Booking_GroupNo",
                        "BookingId = '" + idreservacion+"'");
                    
                    request.setAttribute("Cancelacion exitosa", exitoso);
                    String url="/vuelos.jsp";
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher(url);
                    rd.forward(request, response);
                       
               }
                
           } catch (ArrayIndexOutOfBoundsException e){}
       }
       
       
        

        /*
        try {
            
             * TODO output your page here. You may use following sample code.
             
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CancelReservation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelReservation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        } */
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
            Logger.getLogger(CancelReservation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CancelReservation.class.getName()).log(Level.SEVERE, null, ex);
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
