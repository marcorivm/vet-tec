/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.City;
import Clases.Hotel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "HotelServlet", urlPatterns = {"/HotelServlet"})
public class HotelServlet extends HttpServlet {

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

        /*
         * Por motivmos de tiempo no hay revisiones de seguridad e integridad pero
         * se debe realizar si hay tiempo
         * Pametros esperados (GET/POST)
         * start_date - Fecha de inicio de la reservacion
         * finish_date - Fecha de finalización de la reservación
         * location - ubicación del hotel
         * room_type - tipo de habitación
         * room_size - tamaño de la habitación
         */
        
        String location = request.getParameter("location");
        String start_date = request.getParameter("start_date");
        String finish_date = request.getParameter("finish_date");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        String room_type = request.getParameter("room_type");
        String room_size = request.getParameter("room_size");
        
        
        request.setAttribute("city", location);
        request.setAttribute("date1", date1);
        request.setAttribute("date2", date2);
        request.setAttribute("type", room_type);
        request.setAttribute("tipoHabitacion", room_size);
        
        String habitacion = (room_type.equals("2"))? "Delujo " : "Ejecutiva ";
        habitacion += room_size;
        
        request.setAttribute("habitacion", habitacion);
        
        try {
            java.sql.Date start_date_s = new java.sql.Date(Long.parseLong(start_date));
            java.sql.Date finish_date_s = new java.sql.Date(Long.parseLong(finish_date));
            Hotel hoteles[] = Hotel.getHotels(City.getCity(location));
            ArrayList<Hotel> hoteles_disp = new  ArrayList<Hotel>();
            for(int i = 0; i < hoteles.length; i++) {
              if(hoteles[i].isRoomAvailable(start_date_s, finish_date_s, Integer.parseInt(room_type))) {
                 hoteles_disp.add(hoteles[i]);
              }
               
            }
            Hotel valids[] = new Hotel[hoteles_disp.size()];
            hoteles_disp.toArray(valids);
            request.setAttribute("hotels", valids);
        } catch (SQLException ex) {
            Logger.getLogger(HotelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //<editor-fold defaultstate="collapsed" desc="older">
        /*
         * String city = request.getParameter("location");
         * String date1 = request.getParameter("date1");
         * String date2 = request.getParameter("date2");
         * String tipo = request.getParameter("tipoHabitacion");
         * String cat = request.getParameter("type");
         * 
         * request.setAttribute("city", city);
         * request.setAttribute("date1", date1);
         * request.setAttribute("date2", date2);
         * request.setAttribute("type", cat);
         * request.setAttribute("tipoHabitacion", tipo);
         * 
         * String habitacion = (cat.equals("deluxe"))? "Delujo " : "Ejecutiva ";
         * habitacion += tipo;
         * 
         * request.setAttribute("habitacion", habitacion);
         * //String arriva1 = request.getParameter("arrival");
         * //String departure = request.getParameter("departure");
         * 
         * //int people = Integer.parseInt(request.getParameter("people"));
         * try {
         * 
         * City location = City.getCity(city);
         * Hotel[] hs = Hotel.getHotels(location);
         * request.setAttribute("hotels", hs);
         * 
         * } catch (SQLException ex) {
         * Logger.getLogger(FlightServlet.class.getName()).log(Level.SEVERE, null, ex);
         * System.out.println("Error (HotelServlet; " + ex.getMessage());
         * }
         */
        //</editor-fold>

        RequestDispatcher rd = request.getRequestDispatcher("Hotel.jsp");
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
