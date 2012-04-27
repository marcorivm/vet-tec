<%@page import="Clases.Hotel_Payment"%>
<%@page import="Clases.FlightSeat_Status"%>
<%@page import="Clases.City"%>
<%@page import="Clases.Hotel_Booking"%>
<%@page import="Clases.Hotel"%>
<%@page import="Clases.Flight"%>
<%@page import="Clases.Flight_Booking"%>
<%@page import="Clases.Package_Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%
    Package_Booking booking = null;
    Flight_Booking flightToBooking = null;
    Flight_Booking flightFromBooking = null;
    Hotel_Booking hotelBooking = null;
    Flight flightTo = null;
    Flight flightFrom = null;
    Hotel hotel = null;
    FlightSeat_Status FSS = null;
    String folio = "";
    String firstName = "";
    String lastName = "";
    int noPersonas;
    int precio;
    String nomHotel = "";
    String fecha;
    int idTempVuelo = 0;
    String source = "";
    String destination = "";
    int vueloIda = 0;
    int vueloVuelta = 0;
    int adultosTo = 1;
    int adultosFrom = 1;
    int ninosTo = 0;
    int ninosFrom = 0;
        
    if (request.getAttribute("booking") != null) {
        booking = (Package_Booking) request.getAttribute("booking");
        RequestDispatcher rd = request.getRequestDispatcher("");
        rd.forward(request, response);
    }
        
    if (booking != null) {
        flightToBooking = booking.getFlightTo();
        flightFromBooking = booking.getFlightFrom();
        hotelBooking = booking.getHotel();
    }
        
    if (flightToBooking != null) {
        flightTo = flightToBooking.getFlight();
        adultosTo = flightToBooking.getNoOfAdults();
        ninosTo = flightToBooking.getNoOfChildren();
    }
    if (flightFromBooking != null) {
        flightFrom = flightFromBooking.getFlight();
        adultosFrom = flightFromBooking.getNoOfAdults();
        ninosFrom = flightFromBooking.getNoOfChildren();
    }
    if (booking.getHotel().getDateOfBooking() == null) {
        idTempVuelo = flightFrom.getFlight_No();
        FSS = FlightSeat_Status.getFlightSeat_Status(idTempVuelo);
        fecha = FSS.getDateOfJourney().toString();
    } else {
        fecha = booking.getHotel().getDateOfBooking().toString();
    }
    folio = booking.getId();
    //fecha lista
    firstName = booking.getName();
    lastName = booking.getLastName();
    source = flightFrom.getSource().getCityName();
    destination = flightTo.getDestination().getCityName();
    vueloIda = booking.getFlightFrom().getFlight().getFlight_No();
    vueloVuelta = booking.getFlightTo().getFlight().getFlight_No();
    if (hotelBooking.getHotel().getHotelName() == null) {
        nomHotel = "No hay";
    } else {
        nomHotel = hotelBooking.getHotel().getHotelName();
    }
    //noPersonas listo
        if (flightFromBooking == null) {
            if (hotelBooking.getHotel().getNoOfDeluxRooms() == 0) {
                noPersonas = hotelBooking.getHotel().getNoOfEXERooms();
            } else {
                noPersonas = hotelBooking.getHotel().getNoOfDeluxRooms();
            }
        } else {
            noPersonas = adultosFrom + ninosFrom;
        }
    String idTempHotel = hotelBooking.getHotel().getHotelId();
    //hp = Hotel_Payment.getHotel_Payment(_paymentId)
    //precio = Hp.
    
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>About</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <style type="text/css">@import "css/jquery.datepick.css";</style>
        <script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_600.font.js"></script>
        <script type="text/javascript" src="js/jquery.datepick.js"></script>
        <script type="text/javascript" src="js/vet.js"></script>
        <!--[if lt IE 9]>
                <script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
                <script type="text/javascript" src="js/html5.js"></script>
        <![endif]-->
    </head>
    <body id="page2">
        <div class="extra">
            <div class="main">
                <!-- header -->
                <header>
                    <div class="wrapper">
                        <h1><a href="index.html" id="logo">Vuelos</a></h1>                     
                    </div>
                    <jsp:include page="includes/navbar.jsp" />
                </header>
                <!-- / header -->
                <!-- content -->
                <section id="content">
                    <!-- columna izq -->
                    <article class="col1">
                        <h3>Buscar</h3>
                        <div class="pad">
                            <div class="wrapper under">
                                <form id="form_1" method="POST" action="Reservaciones">
                                    <div class="tabs_cont">
                                        <div class="bg">
                                            <div class="wrapper">
                                                <input type="text" placeholder="No. de Reservacion" name="bookingId" /><br />
                                                <hr />
                                                <input type="text" placeholder="Apellido" name="lastName" />
                                                <input type="text" placeholder="Nombre" name="firstName" />
                                                <input type="text" placeholder="Correo Electrónico" name="email" />
                                            </div>                                           


                                            <div>
                                                <input type="submit" class="button" value="Buscar" />                                            
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- div class="wrapper">
                                <figure class="left marg_right1"><img src="images/page1_img3.jpg" alt=""></figure>
                                <p class="pad_bot2"><strong>Cruise<br>Holidays</strong></p>
                                <p class="pad_bot2">Lorem ipsum dolor sit amet, consect etuer adipiscing.</p>
                                <a href="#" class="marker_1"></a>
                            </div -->
                        </div>
                    </article>
                    <!-- columna derecha -->
                        <!-- aqui iba la comlumna derecha -->
                        
                        <table>
                            <tr>
                                <td><%=folio%></td>
                                <td><%=lastName%></td>
                                <td><%=firstName%></td>
                                <td><%=fecha%></td>
                                <td><%=destination%></td>
                            </tr>
                            <tr>
                                <td><%=vueloIda%></td>
                                <td><%=vueloVuelta%>)</td>
                                <td><%=nomHotel%></td>
                                <td><%=noPersonas%></td>
                                <td>Precio Total</td>
                            </tr>
                        </table>
                </section>
                <!-- / content -->
            </div>
            <div class="block"></div>
        </div>
        <div class="body1">
            <div class="main">
                <jsp:include page="includes/footer.jsp" />
            </div>
        </div>
        <script type="text/javascript">
            Cufon.now();            
        </script>
    </body>
</html>