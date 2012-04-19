<%@page import="Clases.Flight"%>
<%@page import="Clases.City"%>
<%@page import="java.util.ArrayList"%>
<%
    City[] cities;
    cities = (City[]) request.getAttribute("cities");
    if (cities == null) {
%> <jsp:forward page="Ciudades" >
    <jsp:param name="referer" value="vuelos.jsp" />
</jsp:forward><%

        RequestDispatcher rd = request.getRequestDispatcher("Ciudades");
        rd.forward(request, response);
    }
    Flight flights[];
    flights = (Flight[]) request.getAttribute("flights");
    Flight flights2[];
    flights2 = (Flight[]) request.getAttribute("flights2");

    boolean f1, f2 = false;

    f1 = (flights != null) && (flights.length > 0);
    f2 = (flights2 != null) && (flights2.length > 0);
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
                                <form id="form_1" method="POST" action="FlightServlet">
                                    <div class="tabs_cont">
                                        <div class="bg">
                                            <div class="wrapper">
                                                <div class="radio">
                                                    <input type="radio" name="isRoundTrip" value="true" checked onclick="mostrarRegreso()">Redondo
                                                </div>
                                                <div class="radio"><input type="radio" name="isRoundTrip" value="false" onclick="ocultarRegreso()">Simple</div>
                                            </div>                                           
                                            <div class="wrapper"><label for="source">Origen</label>
                                                <select name="source" id="source">
                                                    <% if (cities != null)
                                                            for (City c : cities) {%>
                                                    <option value="<%=c.getCityCode()%>"><%=c.getCityName()%></option>
                                                    <% }%>
                                                </select></div>                                           
                                            <div class="wrapper"><label for="destiny">Destino</label>
                                                <select name="destiny" id="destiny">
                                                    <% if (cities != null)
                                                            for (City c : cities) {%>
                                                    <option value="<%=c.getCityCode()%>"><%=c.getCityName()%></option>
                                                    <% }%>
                                                </select></div>
                                            <div class="wrapper"><label for="date1">Salida (dd/mm/aaaa)</label>
                                                <input type="text" name="date1" id="date1" /></div>
                                            <div id="regreso" class="wrapper pad_bot1"><label for="date2">Regreso (dd/mm/aaaa)</label>
                                                <input type="text" name="date2" id="date2" /></div>                                    
                                            <div class="wrapper pad_bot1d">
                                                <label for="timeFrom">Hora</label>
                                                <select name="timeFrom" id="timeFrom">
                                                    <%
                                                        for (int i = 6; i <= 22; i++) {
                                                    %>
                                                    <option value="<%=i%>"><%=i%></option>    
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                                <br />
                                                <label for="timeTo">a</label>
                                                <select name="timeTo" id="timeTo">
                                                    <%
                                                        for (int i = 7; i <= 23; i++) {
                                                    %>
                                                    <option value="<%=i%>"><%=i%></option>    
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="wrapper pad_bot1">
                                                Adultos<br />
                                                <select name="adults">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option value="35">35</option>
                                                </select><br />
                                                Niños<br />
                                                <select name="kids">
                                                    <option>0</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select><br />
                                            </div>
                                            <div>
                                                <input type="submit" class="button" value="Buscar" />                                            
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
                        <% if (f1) {

                        %>
                        <h3>Vuelos Disponibles Ida</h3>
                        <div class="wrapper under2" style="margin-bottom:2em;">                            
                                <div><table>
                                        <tr>
                                            <th>Aerol&iacute;nea</th>
                                            <th>Or&iacute;gen</th>
                                            <th>Destino</th>
                                            <th>Tarifa Adulto</th>
                                            <th>Tarifa Ni&ntilde;o</th>
                                            <th>Reservar</th>
                                        </tr>
                                        <%
                                            for (Flight f : flights) {
                                        %>
                                        <tr>
                                            <td><%=f.getAirline_Name()%></td>
                                            <td><%=f.getSource().getCityName()%></td>
                                            <td><%=f.getDestination().getCityName()%></td>
                                            <td><%=f.getAdult_Fare()%></td>
                                            <td><%=f.getChild_Fare()%></td>
                                            <td><a href="/Reservar?flightNo=<%=f.getFlight_No() %>"><button class="button" value="Reservar" /></a></td>
                                        </tr>
                                        <%
                                            }%>
                                    </table></div>                           
                        </div>
                        <% } else if (f2) {%>
                        <h3> Vuelos de Ida no Disponibles </h3>
                        <% } else {%>
                        <h3> No Hay Vuelos Disponibles </h3>
                        <% }
                            if (f2) {

                        %>
                        <h3>Vuelos Disponibles Retorno</h3>
                        <div class="wrapper under">
                            
                                <div><table>
                                        <tr>
                                            <th>Aerol&iacute;nea</th>
                                            <th>Or&iacute;gen</th>
                                            <th>Destino</th>
                                            <th>Tarifa Adulto</th>
                                            <th>Tarifa Ni&ntilde;o</th>
                                            <th>Reservar</th>
                                        </tr>
                                        <%
                                            for (Flight f : flights2) {
                                        %>
                                        <tr>
                                            <td><%=f.getAirline_Name()%></td>
                                            <td><%=f.getSource().getCityName()%></td>
                                            <td><%=f.getDestination().getCityName()%></td>
                                            <td><%=f.getAdult_Fare()%></td>
                                            <td><%=f.getChild_Fare()%></td>
                                            <td><a href="/Reservar?flightNo=<%=f.getFlight_No() %>"><button class="button" value="Reservar" /></a></td>
                                        </tr>
                                        <%
                                            }%>
                                    </table></div>
                           
                        </div>
                        <% } else if (f1 && flights2 != null) {%>
                        <h3> Vuelos de Retorno no Disponibles </h3>
                        <% }%>
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
            $("#date1").datepick({
                dateFormat: 'dd-mm-yyyy',
                minDate: new Date()
            });
            $("#date2").datepick({
                dateFormat: 'dd-mm-yyyy',
                minDate: new Date()
            });
        </script>
    </body>
</html>