<%@page import="Clases.Hotel"%>
<%@page import="Clases.City"%>
<%@page import="java.util.ArrayList"%>
<%
    City[] cities = City.getCities();
    Hotel hotels[];
    hotels = (Hotel[]) request.getAttribute("hotels");
    int cuartosDel = 0;
    int cuartosExe = 0;
    if (request.getAttribute("cuartosDel") != null) {
        cuartosDel = Integer.parseInt((String) request.getAttribute("cuartosDel"));
    }
    if (request.getAttribute("cuartosExe") != null) {
        cuartosExe = Integer.parseInt((String) request.getAttribute("cuartosExe"));
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Hoteles</title>
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
                        <h3>Buscar Hotel</h3>
                        <div class="pad">
                            <div class="wrapper under">
                                <form id="form_1" method="POST" action="HotelServlet">
                                    <input type="hidden" name="referer" id="referer" value="Hotel.jsp" />
                                    <div class="tabs_cont">
                                        <div class="bg">

                                            <div class="wrapper"><label for="city">Ciudad</label>
                                                <select name="city" id="city">
                                                    <% if (cities != null)
                                                            for (City c : cities) {%>
                                                    <option value="<%=c.getCityCode()%>"><%=c.getCityName()%></option>
                                                    <% }%>
                                                </select></div>

                                            <div class="wrapper"><label for="date1">Llegada (dd/mm/aaaa)</label>
                                                 <input type="text" name="date1" id="date1" /> <input type="hidden" name="start_date" id="start_date" /></div>
                                            <div id="regreso" class="wrapper"><label for="finish_date">Salida (dd/mm/aaaa)</label>
                                                <input type="text" name="date2" id="date2" /><input type="hidden" name="finish_date" id="finish_date" /></div>
                                            <div class="wrapper">
                                                Tipo de Habitaci&oacute;n<br />
                                                <select name="type">
                                                    <option value="sencilla">Sencilla</option>
                                                    <option value="doble">Doble</option>
                                                    <option value="triple">Triple</option>
                                                    <option value="cuadruple">Cu&aacute;druple</option>
                                                </select><br />
                                            </div>
                                            <div class="wrapper">
                                                <input type ="radio" name="tipoHabitacion" id="deluxe" value="2"/>                                                
                                                <label for="deluxe">Cuartos Deluxe</label> <br/>
                                                <input type="radio" name ="tipoHabitacion" id="exe" value="1" checked="checked" />
                                                <label for="exe">Cuartos Exe</label>
                                            </div>
                                            <div>
                                                <input type="submit" class="button" value="Buscar" />
                                            </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </article>
                    <!-- columna derecha -->
                    <article class="col2 pad_left1">
                        <% if (request.getAttribute("consulta") != null) {
                                if (hotels != null && hotels.length > 0) {%>
                                <form name="hotelSelection" action="HotelReservation.jsp" method="post">
                                    <input type="hidden" value="${city}" name="city" id="city" />
                                    <input type="hidden" value="${date1}" name="date1" id="date1" />
                                    <input type="hidden" value="${date2}" name="date2" id="date2" />
                                    <input type="hidden" value="${type}" name="type" id="type" />
                                    <input type="hidden" value="${tipoHabitacion}" name="tipoHabitacion" id="tipoHabitacion" />
                        <h2>Revisar Hotel</h2>
                        <div class="wrapper under">
                            <div id="hotelDetails" class="under">
                                <h3>Revisar Detalles del Hotel</h3>
                                <h4>
                                    <div>En <span class="city"><%= City.getCity((String)request.getAttribute("city")).getCityName()%></span> del <span class="city">${date1}</span> al <span class="city">${date2}</span></div>
                                <div>En <span class="city">${habitacion}</span></div>
                                </h4>
                                <div>
                                    <table>
                                        <tr>
                                            <th>Hotel</th>
                                            <th>Fecha Llegada</th>
                                            <th>Fecha Salida</th>
                                            <th>Tarifa Delujo por noche</th>
                                            <th>Tarifa Ejecutiva por noche</th>
                                        </tr>
                                        <% for (Hotel h : hotels) {%>
                                        <tr>
                                            <td><%=h.getHotelName()%></td>
                                            <td>FECHA LLEGADA</td>
                                            <td>FECHA SALIDA</td>
                                            <td><b><%=h.getDeluxRoomFare_PerDay()%></b></td>
                                            <td><b><%=h.getEXERoomFarePerDay()%></b></td>
                                        </tr>
                                        <% }%>
                                    </table>
                                </div>
                            </div>
                            <div id="fareDetails">
                                <h3>Revisar Detalles de la Tarifa</h3>
                                <div>
                                    <table>
                                        <tr>
                                            <th>Hotel</th>
                                            <th>Tipo de Habitación</th>
                                            <th>Tarifa Delujo por noche</th>
                                            <th>Tarifa Ejecutiva por noche</th>
                                            <th>Habitaciones Deluxe</th>
                                            <th>Habitaciones Exe</th>
                                            <th>Tarifa</th>
                                            <th>Impuestos</th>
                                            <th>Total</th>
                                            <th>Reservar</th>
                                        </tr>
                                        <% for (Hotel h : hotels) {%>
                                        <tr>
                                            <td><%=h.getHotelName()%></td>
                                            <td>Sencilla/Doble/Quad</td>
                                            <td><%=h.getDeluxRoomFare_PerDay()%></td>
                                            <td><%=h.getEXERoomFarePerDay()%></td>
                                            <td><%=cuartosDel%></td>
                                            <td><%=cuartosExe%></td>
                                            <td><%=(((h.getDeluxRoomFare_PerDay()) * cuartosDel) + ((h.getEXERoomFarePerDay()) * cuartosExe))%></td>
                                            <td><%=h.getHotelTax()%></td>
                                            <td><%=(((h.getDeluxRoomFare_PerDay()) * cuartosDel) + ((h.getEXERoomFarePerDay()) * cuartosExe) + h.getHotelTax())%></td>
                                            <td><a href="/Reservar?hotelNo=<%=h.getHotelId()%>"><button class="button" value="Reservar" /></a></td>
                                        </tr>
                                        <% }%>
                                    </table>
                                </div>
                            </div>
                        </form>
                        <% } else { %>
                        <h3>No hay resultados</h3>
                        <article class="col2 pad_left1">
                            <h2>Hoteles Populares</h2>
                            <div class="wrapper under">
                                <figure class="left marg_right1"><img src="images/page1_img4.jpg" alt=""></figure>
                                <p class="pad_bot2"><strong>Helmsley Park Lane</strong></p>
                                <p class="pad_bot2">Helmsley Park Lane ® es un hotel de lujo de 46 historias con las vistas panorámicas de Central Park  y el horizonte de la Ciudad de Nueva York . Sus espacios son amplios, elegantes y sus suites ofrecen ventanales y vistas magníficas. Se disfruta de un ambiente clásico, comodidad y conveniencia para una experiencia &uacute;nica en la ciudad líder mundial más extraordinaria.</p>
                                <p class="pad_bot2"> Localizada en el Central Park en Nueva York, justo en el centro del distrito de negocios, este hotel esta a una corta distancia de la 5ta Avenida, Broadway, Radio City, entre otras atracciones.</p>
                                <a href="#" class="marker_2"></a>
                            </div>
                            <div class="wrapper">
                                <figure class="left marg_right1"><img src="images/page1_img5.jpg" alt=""></figure>
                                <p class="pad_bot2"><strong>Belmont</strong></p>
                                <p class="pad_bot2">El hotel Belmont es un establecimiento distendido de 3 estrellas. Dotado de un notable confort tecnológico y unos espacios sorprendentes, es un remanso de paz con una atmósfera relajante. Nuestro amable personal le informará sobre las visitas más interesantes de su barrio de los Campos Elíseos para lograr que su estancia le resulte inolvidable.</p>
                                <p class="pad_bot2">Situado a un centenar de metros de los Campos Elíseos, el hotel Belmont le ofrece un acceso inmediato a las lujosas boutiques de las avenidas Montaigne y George V.</p>
                                <a href="#" class="marker_2"></a>
                            </div>
                        </article>
                        <% }
                                } else {%>
                        <article class="col2 pad_left1">
                            <h2>Hoteles Populares</h2>
                            <div class="wrapper under">
                                <figure class="left marg_right1"><img src="images/page1_img4.jpg" alt=""></figure>
                                <p class="pad_bot2"><strong>Helmsley Park Lane</strong></p>
                                <p class="pad_bot2">Helmsley Park Lane ® es un hotel de lujo de 46 historias con las vistas panorámicas de Central Park  y el horizonte de la Ciudad de Nueva York . Sus espacios son amplios, elegantes y sus suites ofrecen ventanales y vistas magníficas. Se disfruta de un ambiente clásico, comodidad y conveniencia para una experiencia &uacute;nica en la ciudad líder mundial más extraordinaria.</p>
                                <p class="pad_bot2"> Localizada en el Central Park en Nueva York, justo en el centro del distrito de negocios, este hotel esta a una corta distancia de la 5ta Avenida, Broadway, Radio City, entre otras atracciones.</p>
                                <a href="#" class="marker_2"></a>
                            </div>
                            <div class="wrapper">
                                <figure class="left marg_right1"><img src="images/page1_img5.jpg" alt=""></figure>
                                <p class="pad_bot2"><strong>Belmont</strong></p>
                                <p class="pad_bot2">El hotel Belmont es un establecimiento distendido de 3 estrellas. Dotado de un notable confort tecnológico y unos espacios sorprendentes, es un remanso de paz con una atmósfera relajante. Nuestro amable personal le informará sobre las visitas más interesantes de su barrio de los Campos Elíseos para lograr que su estancia le resulte inolvidable.</p>
                                <p class="pad_bot2">Situado a un centenar de metros de los Campos Elíseos, el hotel Belmont le ofrece un acceso inmediato a las lujosas boutiques de las avenidas Montaigne y George V.</p>
                                <a href="#" class="marker_2"></a>
                            </div>
                        </article>
                        <% }%>
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
                minDate: new Date(),
                altFormat: '@',
                altField: '#start_date'
            });
            $("#date2").datepick({
                dateFormat: 'dd-mm-yyyy',
                minDate: new Date(),
                altFormat: '@',
                altField: '#finish_date'
            });
		</script>
    </body>
</html>
