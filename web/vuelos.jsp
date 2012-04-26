<%@page import="java.util.Set"%>
<%@page import="Clases.Flight"%>
<%@page import="Clases.City"%>
<%@page import="java.util.ArrayList"%>
<%
    City[] cities;
    cities = (City[]) request.getAttribute("cities");
    if (cities == null) {
        request.setAttribute("referer", "vuelos.jsp");
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
        <title>Vuelos</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <style type="text/css">@import "css/jquery.datepick.css";</style>
        <script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
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
                                                        for (int i = 7; i < 23; i++) {
                                                    %>
                                                    <option value="<%=i%>"><%=i%></option>    
                                                    <%
                                                        }
                                                    %>
                                                    <option value="23" selected="selected">23</option>
                                                </select>
                                            </div>
                                            <div class="wrapper pad_bot1">
                                                Adultos<br />
                                                <select name="adults">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                </select><br />
                                                Ni&ntilde;os<br />
                                                <select name="kids">
                                                    <option value="0">0</option>
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
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
                        <form method="POST" action="reservar.jsp">
                            <input type="hidden" name="adultos" id="adultos" value="<%=request.getAttribute("adultos")%>"/>
                            <input type="hidden" name="ninos" id="ninos" value="<%=request.getAttribute("ninos")%>"/>
                            <input type="hidden" name="date1" id="date1" value="${date1}"/>
                            <input type="hidden" name="date2" id="date2" value="${date2}"/>
                            
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
                                            <td><input name="flightTo" type="radio" value="<%=f.getFlight_No()%>" /></td>
                                        </tr>
                                        <%
                                            }%>
                                    </table></div>                           
                            </div>
                            <% } else if (f2) {%>
                            <h3> Vuelos de Ida no Disponibles </h3>
                            <% } else {%>
                            <article class="col2 pad_left1">
                                <h2>Nuestros Vuelos</h2>
                                <div class="wrapper under">
                                    <figure class="left marg_right1"><img src="images/vuelos1.jpg" alt=""></figure>
                                    <p class="pad_bot2"><strong>Vuelos Directos a Turqu&iacute;a</strong></p>
                                    <p class="pad_bot2">Recientemente hemos adquirido más vuelos partiendo hacia y desde Turqu&iacute;a. Tenemos una gran variedad de vuelos con escalas, as&iacute; como vuelos directos.</p>
                                    <p class="pad_bot2">Si quieres visitar Turqu&iacute;a no puedes dejar pasar esta oportunidad. Reserva tu vuelo y de paso podr&aacute;s reservar en uno de los mejores hoteles para hacer de tus vacaciones una estancia inolvidable.</p>                          
                                </div>
                                <div class="wrapper">
                                    <figure class="left marg_right1"><img src="images/vuelos2.jpg" alt=""></figure>
                                    <p class="pad_bot2"><strong>Vuelos a Sudam&eacute;rica</strong></p>
                                    <p class="pad_bot2">Contamos con la mayor cantidad de vuelos con los destinos m&aacute;s populares en el continente americano. Destinos como Chile, Buenos Aires, Bogot&aacute;, Lima, Rio de Janeiro, entre otros est&aacute;n disponibles para que armes tu viaje perfecto.</p>
                                    <p class="pad_bot2">Consulte los vuelos que tenemos disponibles ahora mismo. Contamos con vuelos diarios a las principales ciudades sudamericanas.</p>                          
                                </div>
                            </article>
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
                                            <td><input name ="flightFrom" type="radio" value="<%=f.getFlight_No()%>" /></a></td>
                                        </tr>
                                        <%
                                            }%>
                                    </table></div>

                            </div>

                            <% } else if (f1 && flights2 != null) {%>
                            <h3> Vuelos de Retorno no Disponibles </h3>
                            <% }
                                if (f1 | f2) {%>
                            <input type="submit" class="button" value="Reservar" />
                            <%                                }%>
                        </form>
                        <!--<h2>Datos de Pago</h2>
                        <div class="wrapper">
                            Boleto de <span class="city">Bangalore</span> to <span class="city">Chennai</span><br />
                            Adultos <span class="bold">3</span><br />
                            Ni&ntilde;os <span class="bold">2</span><br />
                            Tarifa Total Adultos <span class="bold">$ 5000</span><br />
                            Tarifa Total Ni&ntilde;os <span class="bold">$ 4000</span><br />
                            Impuestos <span class="bold">$ 800</span><br />
                            Total <span class="bold">$ 9800</span><br />
                            <input type="button" class="button" value="Hacer Pago" onclick="(function() { alert('Payment Succesful!'); })();" /><br />
                        </div> -->                       
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
