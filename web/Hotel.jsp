<%@page import="Clases.Hotel"%>
<%@page import="Clases.City"%>
<%
    City[] cities;
    cities = (City[]) request.getAttribute("cities");
    Hotel[] hotels;
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
                        <h1><a href="index.html" id="logo">Around the World</a></h1>
                        
                    </div>
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
                                                <input type="text" name="date1" id="date1" /></div>
                                            <div id="regreso" class="wrapper"><label for="date2">Salida (dd/mm/aaaa)</label>
                                                <input type="text" name="date2" id="date2" /></div>
                                            <div class="wrapper">
                                                Tipo de Habitaci&oacute;n<br />
                                                <select name="tipoHabitacion">
                                                    <option value="sencilla">Sencilla</option>
                                                    <option value="doble">Doble</option>
                                                    <option value="triple">Triple</option>
                                                    <option value="cuadruple">Cu&aacute;druple</option>
                                                </select><br />
                                            </div>
                                            <div class="wrapper">
                                                <input type ="radio" name="type" id="type" value="deluxe"/>                                                
                                                <label for="deluxe">Cuartos Deluxe</label> <br/>
                                                <input type="radio" name ="type" id="type" value="exe" />
                                                <i<label for="exe">Cuartos Exe</label>
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
                        <% if (hotels != null) {%>
                        <h2>Revisar Hotel</h2>
                        <div class="wrapper under">
                            <div id="hotelDetails" class="under">
                                <h3>Revisar Detalles del Hotel</h3>
                                <div><span class="city">Chennai</span></div>
                                <div>
                                    <table>
                                        <tr>
                                            <th>Hotel</th>
                                            <th>Fecha Llegada</th>
                                            <th>Fecha Salida</th>
                                            <th>Tarifa Delujo Diaria</th>
                                            <th>Tarifa Ejecutiva Diaria</th>
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
                                            <th>Tarifa Delujo Diaria</th>
                                            <th>Tarifa Ejecutiva Diaria</th>
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
                        </div>
                        <% }%>
                    </article>
                </section>
                <!-- / content -->
            </div>
            <div class="block"></div>
        </div>
        <div class="body1">
            <div class="main">
                <!-- footer -->
                <footer>
                    
                </footer>
                <!-- / footer -->
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
            });</script>
    </body>
</html>