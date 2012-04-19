package Servlets;



import Clases.Flight_Booking;
import dbcp.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
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
        try {
    /*
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
            
	    String campos[][] = {{"FlightNo",request.getParameter("FlightNo")},{"AirlinesName",request.getParameter("AirlinesName")},{"Source",request.getParameter("Source")},{"Destination",request.getParameter("Destination")},{"DepartureTime",request.getParameter("DepartureTime")},{"ArrivalTime",request.getParameter("ArrivalTime")},{"TotalSeats",request.getParameter("TotalSeats")},{"AdultFare",request.getParameter("AdultFare")},{"ChildFare",request.getParameter("ChildFare")}};//Introducciòn de parametros
            String isRound = request.getParameter("isRound");
            int ciudades = dbcp.ConnectionManager.insertAndGetKey(campos,"Tbl_Flight_GroupNo");//Se inserta el dato y se guarda la reservaciòn
            String camposregistro[][] = {{"BookingId",Flight_Booking.getNextID()},{"FlightNo","" + ciudades},{"CustomerId",request.getParameter("CustomerId")},{"DateofBooking",(sdf.format(cal.getTime()))},{"DateOfJourney",request.getParameter("DateOfJourney")},{"NoOfAdults",request.getParameter("NoOfAdults")},{"NoOfChildren",request.getParameter("NoOfChildren")}};
      	    boolean insercion = dbcp.ConnectionManager.insert(camposregistro,"Tbl_Flight_Booking_GroupNo");
            
            if(isRound)
            {
                String vuelo2[][] = 
            }
       */
        } finally {            
            out.close();
        }
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
