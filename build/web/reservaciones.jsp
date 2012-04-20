<%@page import="Clases.Flight"%>
<%@page import="Clases.Flight_Booking"%>
<%
   // char type = request.getParameter("type").charAt(0);
    Flight_Booking fb;
    fb = (Flight_Booking) request.getAttribute("reservacion");
     Flight flight = null;
    int adultos = 0;
    int ninos = 0;
    if(fb != null){
        flight = fb.getFlight();
        adultos = fb.getNoOfAdults();
        ninos = fb.getNoOfChildren();
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
                        <h3>Buscar Registro</h3>
                        <div class="pad">
                            <div class="wrapper under">
                                <form id="form_1" method="POST" action="Reservacion">
                                    <div class="tabs_cont">
                                        <div class="bg">
                                            <div class="wrapper">
                                                <input type="text" placeholder="No Reservacion" name="bookingId" />
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
                        <% if (flight != null) {

                        %>
                        <h3>Vuelos Disponibles Ida</h3>
                        <div class="wrapper under2" style="margin-bottom:2em;">
                            <form action="Cancelar" method="POST">
                                <input type="hidden" name="bookingId" value="<%=fb.getBookingId() %>" />
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
                                            <td><%=flight.getAirline_Name()%></td>
                                            <td><%=flight.getSource().getCityName()%></td>
                                            <td><%=flight.getDestination().getCityName()%></td>
                                            <td><%=flight.getAdult_Fare()%></td>
                                            <td><%=flight.getChild_Fare()%></td>
                                            <td><input type="submit" class="button" value="Cancelar" /></td>
                                        </tr>

                                    </table></div>
                            </form>
                        </div>                     
                        <% } else {%>
                        <h3> Ingrese un identificador de Registro </h3>
                        <% }
                        %>
                        




                        <h2>Revisar Vuelo</h2>
                        <div class="wrapper under">     
                            <% if (flight != null) { %>
                            <div id="flightDetails" class="under">
                                <h3>Revisar Detalles del Vuelo</h3>
                                <div><span class="city"><%=flight.getSource().getCityName() %></span> --> <span class="city"><%=flight.getDestination().getCityName() %></span></div>
                                <div>
                                    <table>
                                        <tr>
                                            <th>N&uacute;mero de Vuelo</th>
                                            <th>Aerol&iacute;nea</th>
                                            <th>Fecha de Salida</th>
                                            <th>Fecha de Llegada</th>
                                        </tr>
                                        <tr>
                                            <td><%=flight.getFlight_No() %></td>
                                            <td><%=flight.getAirline_Name() %></td>
                                            <td><%=flight.getDeparture_Time() %></td>
                                            <td><%=flight.getArrival_Time() %></td>
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
                                            <td><%=adultos %></td>
                                            <td>$ <%=flight.getAdult_Fare()*adultos %> (<%=flight.getAdult_Fare() %> x <%=adultos %>)</td>  
                                            <td>$ <%=flight.getAirport_Tax() %></td>
                                            <td>$ <%=(flight.getAdult_Fare()*adultos + flight.getAirport_Tax()) %></td>
                                        </tr>
                                        <tr>
                                            <td>Ni&ntilde;os</td>
                                            <td><%=ninos %></td>
                                            <td>$ <%=flight.getChild_Fare()*ninos %> (<%=flight.getChild_Fare() %> x <%=ninos %>)</td>  
                                            <td>$ <%=flight.getAirport_Tax() %></td>
                                            <td>$ <%=(flight.getChild_Fare()*ninos + flight.getAirport_Tax()) %></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div>
                                <span><input type="button" class="button" value="Reservar Vuelo" /></span>
                                <br />
                                <span class="floatRight">
                                    -------------------------<br />
                                    Total: $ <%=((flight.getAdult_Fare()*adultos + flight.getAirport_Tax()) + (flight.getChild_Fare()*ninos + flight.getAirport_Tax())) %><br />
                                    -------------------------
                                </span>
                            </div>
                                    <% } %>
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
                                <h3>Infante 1</h3>
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