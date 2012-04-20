<%@page import="Clases.Hotel_Booking"%>
<%@page import="Clases.Hotel"%>
<%@page import="Clases.Flight"%>
<%@page import="Clases.Flight_Booking"%>
<%
    Package_Booking booking = null;
    Flight_Booking flightToBooking = null;
    Flight_Booking flightFromBooking = null;
    Hotel_Booking hotelBooking = null;
    Flight flightTo = null;
    Flight flightFrom = null;
    Hotel hotel = null;
    int adultosTo = 1;
    int adultosFrom = 1;
    int ninosTo = 0;
    int ninosFrom = 0;

    if (request.getAttribute("booking") != null) {
        booking = (Flight_Booking) request.getAttribute("booking");
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
                        <div class="right">
                            <div class="wrapper">
                                <form id="search" action="" method="post">
                                    <div class="bg">
                                        <input type="submit" class="submit" value="">
                                        <input type="text" class="input">
                                    </div>
                                </form>
                            </div>
                            <div class="wrapper">
                                <nav>
                                    <ul id="top_nav">
                                        <li><a href="#">Register</a></li>
                                        <li><a href="#">Log In</a></li>
                                        <li><a href="#">Help</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <jsp:include page="includes/navbar.jsp" />
                </header>
                <!-- / header -->
                <!-- content -->
                <section id="content">
                    <!-- columna izq -->
                    <article class="col1">
                        <h3>Buscar Vuelo</h3>
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
                    <article class="col2 pad_left1">
                        <% if (flightTo != null) {

                        %>
                        <h3>Vuelo Ida Reservado</h3>
                        <div class="wrapper under2" style="margin-bottom:2em;">
                            <form action="Cancelar" method="POST">
                                <input type="hidden" name="flightToBooking" value="<%=flightToBooking.getBookingId()%>" />
                                <div><table>
                                        <tr>
                                            <th>Aerol&iacute;nea</th>
                                            <th>Or&iacute;gen</th>
                                            <th>Destino</th>
                                            <th>Tarifa Adulto</th>
                                            <th>Tarifa Ni&ntilde;o</th>
                                            <th>Cancelar</th>
                                        </tr>

                                        <tr>
                                            <td><%=flightTo.getAirline_Name()%></td>
                                            <td><%=flightTo.getSource().getCityName()%></td>
                                            <td><%=flightTo.getDestination().getCityName()%></td>
                                            <td><%=flightTo.getAdult_Fare()%></td>
                                            <td><%=flightTo.getChild_Fare()%></td>
                                            <td><input type="submit" class="button" value="Cancelar" /></td>
                                        </tr>

                                    </table></div>
                            </form>
                        </div>                     
                        <% } else {%>
                        <h3> No Hay Vuelo de Ida Reservado </h3>
                        <% }
                        %>                       

                        <h2>Detalles Vuelo Ida</h2>
                        <div class="wrapper under">     
                            <% if (flightTo != null) {%>
                            <div id="flightDetails" class="under">
                                <h3>Revisar Detalles del Vuelo</h3>
                                <div><span class="city"><%=flightTo.getSource().getCityName()%></span> --> <span class="city"><%=flightTo.getDestination().getCityName()%></span></div>
                                <div>
                                    <table>
                                        <tr>
                                            <th>N&uacute;mero de Vuelo</th>
                                            <th>Aerol&iacute;nea</th>
                                            <th>Fecha de Salida</th>
                                            <th>Fecha de Llegada</th>
                                        </tr>
                                        <tr>
                                            <td><%=flightTo.getFlight_No()%></td>
                                            <td><%=flightTo.getAirline_Name()%></td>
                                            <td><%=flightTo.getDeparture_Time()%></td>
                                            <td><%=flightTo.getArrival_Time()%></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="fareDetails">
                                <h3>Revisar Detalles de la Tarifa</h3>
                                <div>
                                    <table>
                                        <tr>
                                            <th>Tipo de Tarifa</th>
                                            <th>Asientos</th>
                                            <th>Tarifa Base</th>
                                            <th>Impuestos</th>
                                            <th>Total incluyendo Impuestos</th>
                                        </tr>
                                        <tr>
                                            <td>Adultos</td>
                                            <td><%=adultosTo%></td>
                                            <td>$ <%=flightTo.getAdult_Fare() * adultosTo%> (<%=flightTo.getAdult_Fare()%> x <%=adultosTo%>)</td>  
                                            <td>$ <%=flightTo.getAirport_Tax()%></td>
                                            <td>$ <%=(flightTo.getAdult_Fare() * adultosTo + flightTo.getAirport_Tax())%></td>
                                        </tr>
                                        <tr>
                                            <td>Ni&ntilde;os</td>
                                            <td><%=ninosTo%></td>
                                            <td>$ <%=flightTo.getChild_Fare() * ninosTo%> flightToflight.getChild_Fare()%> x <%=ninosTo%>)</td>  
                                            <td>$ <%=flightTo.getAirport_Tax()%></td>
                                            <td>$ <%=(flightTo.getChild_Fare() * ninosTo + flightTo.getAirport_Tax())%></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div>
                                <span><input type="button" class="button" value="Reservar Vuelo" /></span>
                                <br />
                                <span class="floatRight">
                                    -------------------------<br />
                                    Total: $ <%=((flightTo.getAdult_Fare() * adultosTo + flightTo.getAirport_Tax()) + (flightTo.getChild_Fare() * ninosTo + flightTo.getAirport_Tax()))%><br />
                                    -------------------------
                                </span>
                            </div>
                            <% }%>
                        </div>
                        <h2>Nombres</h2>
                        <!-- adultos -->
                        <div class="wrapper under">
                            <div class="wrapper">
                                <h3>Adulto 1: Pasajero Principal</h3>
                                <div class="form">
                                    <label for="title1">T&iacute;tulo </label>
                                    <select name="title1" id="title1">
                                        <option>Sr.</option>
                                        <option>Sra.</option>
                                        <option>Srta.</option>
                                    </select>
                                </div>
                                <div class="form">
                                    <label for="fname1">Nombre: </label>
                                    <input type="text" name="fname1" id="fname1" />
                                </div>
                                <div class="form">
                                    <label for="lname1">Apellidos: </label>
                                    <input type="text" name="lname1" id="lname1" />
                                </div>
                            </div>
                        </div>
                        <!-- ninos -->
                        <div class="wrapper under">
                            <div class="wrapper">
                                <h3>Ni;ntilde;o 1</h3>
                                <div class="form">
                                    <label for="child-fname1">Nombre: </label>
                                    <input type="text" name="child-fname1" id="child-fname1" />
                                </div>
                                <div class="form">
                                    <label for="child-lname1">Apellidos: </label>
                                    <input type="text" name="child-lname1" id="child-lname1" />
                                </div>
                            </div>
                        </div>
                        <h2>Datos de Pago</h2>
                        <div class="wrapper">
                            Boleto de <span class="city">Bangalore</span> to <span class="city">Chennai</span><br />
                            Adultos <span class="bold">3</span><br />
                            Ni&ntilde;os <span class="bold">2</span><br />
                            Tarifa Total Adultos <span class="bold">$ 5000</span><br />
                            Tarifa Total Ni&ntilde;os <span class="bold">$ 4000</span><br />
                            Impuestos <span class="bold">$ 800</span><br />
                            Total <span class="bold">$ 9800</span><br />
                            <input type="button" class="button" value="Hacer Pago" onclick="(function() { alert('Payment Succesful!'); })();" /><br />
                        </div>
                        <!-- vuelo de regreso -->
                        <% if (flightFrom != null) {

                        %>
                        <h3>Vuelo Regreso Reservado</h3>
                        <div class="wrapper under2" style="margin-bottom:2em;">
                            <form action="Cancelar" method="POST">
                                <input type="hidden" name="flightFromBooking" value="<%=flightFromBooking.getBookingId()%>" />
                                <div><table>
                                        <tr>
                                            <th>Aerol&iacute;nea</th>
                                            <th>Or&iacute;gen</th>
                                            <th>Destino</th>
                                            <th>Tarifa Adulto</th>
                                            <th>Tarifa Ni&ntilde;o</th>
                                            <th>Cancelar</th>
                                        </tr>

                                        <tr>
                                            <td><%=flightFrom.getAirline_Name()%></td>
                                            <td><%=flightFrom.getSource().getCityName()%></td>
                                            <td><%=flightFrom.getDestination().getCityName()%></td>
                                            <td><%=flightFrom.getAdult_Fare()%></td>
                                            <td><%=flightFrom.getChild_Fare()%></td>
                                            <td><input type="submit" class="button" value="Cancelar" /></td>
                                        </tr>

                                    </table></div>
                            </form>
                        </div>                     
                        <% } else {%>
                        <h3> No Hay Vuelo de Regreso Reservado </h3>
                        <% }
                        %>                       

                        <h2>Detalles Vuelo Regreso</h2>
                        <div class="wrapper under">     
                            <% if (flightFrom != null) {%>
                            <div id="flightDetails2" class="under">
                                <h3>Revisar Detalles del Vuelo</h3>
                                <div><span class="city"><%=flightFrom.getSource().getCityName()%></span> --> <span class="city"><%=flightFrom.getDestination().getCityName()%></span></div>
                                <div>
                                    <table>
                                        <tr>
                                            <th>N&uacute;mero de Vuelo</th>
                                            <th>Aerol&iacute;nea</th>
                                            <th>Fecha de Salida</th>
                                            <th>Fecha de Llegada</th>
                                        </tr>
                                        <tr>
                                            <td><%=flightFrom.getFlight_No()%></td>
                                            <td><%=flightFrom.getAirline_Name()%></td>
                                            <td><%=flightFrom.getDeparture_Time()%></td>
                                            <td><%=flightFrom.getArrival_Time()%></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="fareDetails2">
                                <h3>Revisar Detalles de la Tarifa</h3>
                                <div>
                                    <table>
                                        <tr>
                                            <th>Tipo de Tarifa</th>
                                            <th>Asientos</th>
                                            <th>Tarifa Base</th>
                                            <th>Impuestos</th>
                                            <th>Total incluyendo Impuestos</th>
                                        </tr>
                                        <tr>
                                            <td>Adultos</td>
                                            <td><%=adultosFrom%></td>
                                            <td>$ <%=flightFrom.getAdult_Fare() * adultosFrom%> (<%=flightFrom.getAdult_Fare()%> x <%=adultosFrom%>)</td>  
                                            <td>$ <%=flightFrom.getAirport_Tax()%></td>
                                            <td>$ <%=(flightFrom.getAdult_Fare() * adultosFrom + flightFrom.getAirport_Tax())%></td>
                                        </tr>
                                        <tr>
                                            <td>Ni&ntilde;os</td>
                                            <td><%=ninosFrom%></td>
                                            <td>$ <%=flightFrom.getChild_Fare() * ninosFrom%> (<%=flightFrom.getChild_Fare()%> x <%=ninosFrom%>)</td>  
                                            <td>$ <%=flightFrom.getAirport_Tax()%></td>
                                            <td>$ <%=(flightFrom.getChild_Fare() * ninosFrom + flightFrom.getAirport_Tax())%></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div>
                                <span><input type="button" class="button" value="Reservar Vuelo" /></span>
                                <br />
                                <span class="floatRight">
                                    -------------------------<br />
                                    Total: $ <%=((flightFrom.getAdult_Fare() * adultosFrom + flightFrom.getAirport_Tax()) + (flightFrom.getChild_Fare() * ninosFrom + flightFrom.getAirport_Tax()))%><br />
                                    -------------------------
                                </span>
                            </div>
                            <% }%>
                        </div>
                        <h2>Nombres</h2>
                        <!-- adultos -->
                        <div class="wrapper under">
                            <div class="wrapper">
                                <h3>Adulto 1: Pasajero Principal</h3>
                                <div class="form">
                                    <label for="title1">T&iacute;tulo </label>
                                    <select name="title1" id="title1">
                                        <option>Sr.</option>
                                        <option>Sra.</option>
                                        <option>Srta.</option>
                                    </select>
                                </div>
                                <div class="form">
                                    <label for="fname1">Nombre: </label>
                                    <input type="text" name="fname1" id="fname1" />
                                </div>
                                <div class="form">
                                    <label for="lname1">Apellidos: </label>
                                    <input type="text" name="lname1" id="lname1" />
                                </div>
                            </div>
                        </div>
                        <!-- ninos -->
                        <div class="wrapper under">
                            <div class="wrapper">
                                <h3>Ni;ntilde;o 1</h3>
                                <div class="form">
                                    <label for="child-fname1">Nombre: </label>
                                    <input type="text" name="child-fname1" id="child-fname1" />
                                </div>
                                <div class="form">
                                    <label for="child-lname1">Apellidos: </label>
                                    <input type="text" name="child-lname1" id="child-lname1" />
                                </div>
                            </div>
                        </div>
                        <h2>Datos de Pago</h2>
                        <div class="wrapper">
                            Boleto de <span class="city">Bangalore</span> to <span class="city">Chennai</span><br />
                            Adultos <span class="bold">3</span><br />
                            Ni&ntilde;os <span class="bold">2</span><br />
                            Tarifa Total Adultos <span class="bold">$ 5000</span><br />
                            Tarifa Total Ni&ntilde;os <span class="bold">$ 4000</span><br />
                            Impuestos <span class="bold">$ 800</span><br />
                            Total <span class="bold">$ 9800</span><br />
                            <input type="button" class="button" value="Hacer Pago" onclick="(function() { alert('Payment Succesful!'); })();" /><br />
                        </div> 
                        <!-- detalles hotel -->
                        <% if (hotel != null) {

                        %>
                        <h3>Detalles Hotel Reservado</h3>
                        <div class="wrapper under2" style="margin-bottom:2em;">
                            <form action="Cancelar" method="POST">
                                <input type="hidden" name="hotelBooking" value="<%=hotelBooking.getBookingId()%>" />
                                <div><table>
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Ciudad</th>
                                            <th>Cuartos Delujo</th>
                                            <th>Cuartos Ejecutivos</th>
                                            <th>Tarifa Delujo Diaria</th>
                                            <th>Tarifa Ejecutiva Diaria</th>
                                            <th>Impuestos</th>
                                            <th>Cancelar</th>
                                        </tr>

                                        <tr>
                                            <th><%=hotel.getHotelName() %></th>
                                            <th><%=hotel.getLocation().getCityName() %></th>
                                            <th><%=hotel.getNoOfDeluxRooms() %></th>                                            
                                            <th><%=hotel.getNoOfEXERooms() %></th>                                            
                                            <th><%=hotel.getDeluxRoomFare_PerDay() %></th>                                            
                                            <th><%=hotel.getEXERoomFarePerDay() %></th>                                            
                                            <th><%=hotel.getHotelTax() %></th>                                            
                                            <td><input type="submit" class="button" value="Cancelar" /></td>
                                        </tr>

                                    </table></div>
                            </form>
                        </div>                     
                        <% } else {%>
                        <h3> No Hay Hotel Reservado </h3>
                        <% }
                        %>     
                    </article>
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