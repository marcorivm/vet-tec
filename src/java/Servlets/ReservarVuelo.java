package Servlets;



import Clases.City;
import Clases.Customer;
import Clases.Flight;
import Clases.Flight_Booking;
import dbcp.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * @author Ruben Ibarra Pastor
 */
@WebServlet(name = "ReservarVuelo", urlPatterns = {"/ReservarVuelo"})
public class ReservarVuelo extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error tanto en entrada como salida
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
//Al llamar al servlet se actualizan los datos
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); //Se deja para que los de aquellos encargados de la interfaz decidan como se imprimirá
        ResultSet resultados;
        //Obtenemos la conexión e iniciamos la transacción IMPORTANTE cerrar conexión (commit o rollback)
        Connection con = ConnectionManager.initTransaction();
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
            
            boolean isRound = (request.getParameter("isRoundTrip").equalsIgnoreCase("true"));
            Flight flightTo = Flight.getFlight(Integer.parseInt(request.getParameter("flightTo")));
            request.getSession().setAttribute("flightTo", flightTo);
            
            Flight_Booking fbTo = new Flight_Booking(Flight_Booking.getNextId(), Date.valueOf((sdf.format(cal.getTime()))),
                                Date.valueOf(request.getParameter("DateOfJourney")), Customer.getCustomer(Integer.parseInt(request.getParameter("CustomerId"))),
                                flightTo, Integer.parseInt(request.getParameter("NoOfAdults")), Integer.parseInt(request.getParameter("NoOfChildren")));
            // Inicializar la variable de control para el segundo vuelo
            request.getSession().setAttribute("fbTo", fbTo);
            boolean vuelo_2 = true; 
            if(isRound)
            {
                
                Flight flightFrom = Flight.getFlight(Integer.parseInt(request.getParameter("flightFrom")));
                request.getSession().setAttribute("flightFrom", flightFrom);
                Flight_Booking fbFrom = new Flight_Booking(Flight_Booking.getNextId(), Date.valueOf((sdf.format(cal.getTime()))),
                                Date.valueOf(request.getParameter("DateOfJourney")), Customer.getCustomer(Integer.parseInt(request.getParameter("CustomerId"))),
                                flightFrom, Integer.parseInt(request.getParameter("NoOfAdults")), Integer.parseInt(request.getParameter("NoOfChildren")));
                request.getSession().setAttribute("fbFrom", fbFrom);
            }
            RequestDispatcher rd = request.getRequestDispatcher("PrepareOfr");
            rd.forward(request, response);
    }

 

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReservarVuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReservarVuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public String getServletInfo() {
        return "Reservar Vuelo";
    }
}
