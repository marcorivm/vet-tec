<%@page import="Clases.Flight"%>
<%@page import="Clases.City"%>
<%
    City[] cities;
    cities = (City[]) request.getAttribute("cities");

    if (cities == null) {
%> <jsp:forward page="Ciudades" >
    <jsp:param name="referer" value="index.jsp" />
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
        <title>Home</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/jquery.datepick.css" type="text/css" media="all">
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
    <body id="page1">
        <div class="extra">
            <div class="main">
                <!-- header -->
                <header>
                    <div class="wrapper">
                        <h1><a href="index.jsp" id="logo">Around the World</a></h1>
                    </div>
                    <jsp:include page="includes/navbar.jsp" />
                    <article class="col1">
                        <ul class="tabs">
                            <li><a href="#" class="active" onclick="mostrarForma('vuelo')" id="opcionVuelo">Vuelo</a></li>
                            <li class ="end"><a href="#" onclick ="mostrarForma('hotel')" id="opcionHotel">Hotel</a></li>
                        </ul>
                        <div class="tabs_cont">
                            <form id="form_1" style="display:" action="FlightServlet" method="post">
                                <input type="hidden" name="referer" value="vuelos.jsp"/>
                                <div class="bg">
                                    <div class="wrapper">
                                        <div class="radio">
                                            <input type="radio" name="isRoundTrip" value="true" checked onclick="mostrarRegreso()">Redondo
                                        </div>
                                        <div class="radio"><input type="radio" name="isRoundTrip" value="false" onclick="ocultarRegreso()">Simple</div>
                                    </div>
                                    <!-- div class="wrapper"><input name="source" type="text" class="input">Origen</div -->
                                    <div class="wrapper"><label for="source">Salida</label>
                                        <select name="source" id="source">
                                            <% if (cities != null)
                                                    for (City c : cities) {%>
                                            <option value="<%=c.getCityCode()%>"><%=c.getCityName()%></option>
                                            <% }%>
                                        </select></div>
                                    <!-- div class="wrapper"><input name="destiny" type="text" class="input">Destino</div -->
                                    <div class="wrapper"><label for="destination">Destino</label>
                                        <select name="destiny" id="destiny">
                                            <% if (cities != null)
                                                    for (City c : cities) {%>
                                            <option value="<%=c.getCityCode()%>"><%=c.getCityName()%></option>
                                            <% }%>
                                        </select></div>
                                    <!-- div class="wrapper"><input name="date1" type="text" class="input input2" value="04/11/2010"  onblur="if(this.value=='') this.value='04/11/2010'" onfocus="if(this.value =='04/11/2010' ) this.value=''">Salida (mm/dd/aa)</div -->
                                    <div class="wrapper"><label for="date1">Salida (dd/mm/aaaa)</label>
                                        <input type="text" name="date1" id="date1" /></div>
                                    <!-- div class="wrapper pad_bot1"><input name="date2"type="text" class="input input2" value="04/11/2010"  onblur="if(this.value=='') this.value='04/11/2010'" onfocus="if(this.value =='04/11/2010' ) this.value=''">Regreso  (mm/dd/aa)</div -->
                                    <div id="regreso" class="wrapper pad_bot1"><label for="date2">Regreso (dd/mm/aaaa)</label>
                                        <input type="text" name="date2" id="date2" /></div>                                    
                                    <div class="wrapper pad_bot1">
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
                                        <a href="#" class="button" onclick="document.getElementById('form_1').submit()">Buscar</a>
                                        Adultos<br />
                                        <select name="adults">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                        </select>
                                        Niños<br />
                                        <select name="kids">
                                            <option>0</option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                            <form  style="display: none" id="form_2" method="POST" action="HotelServlet" >
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
                                </article>
                                <article class="col1 pad_left1">
                                    <div class="text">
                                        <img src="images/text1.jpg" alt="">
                                        <h2>Las mejores Ofertas</h2>
                                        <p>AroundtheWorld es una empresa que se dedica al turismo. Tenemos las mejores ofertas del mercado en Vuelos y Hoteles.</p>
                                    </div>
                                </article>
                                <div class="img"><img src="images/img.jpg" alt=""></div>
                                </header><div class="ic">More Website Templates at TemplateMonster.com!</div>
                                <!-- / header -->
                                <!-- content -->
                                <section id="content">
                                    <article class="col1">
                                        <h3>Nuevos Destinos</h3>
                                        <div class="pad">
                                            <div class="wrapper under">
                                                <figure class="left marg_right1"><img src="images/page1_img1.jpg" alt=""></figure>
                                                <p class="pad_bot2"><strong>Las Vegas</strong></p>
                                                <p class="pad_bot2">M&aacute;s de 100 shows y m&aacute;s de 200 casinos, Las Vegas es el lugar ideal para disfrutar.</p>
                                                <a href="#" class="marker_1"></a>
                                            </div>
                                            <div class="wrapper under">
                                                <figure class="left marg_right1"><img src="images/page1_img2.jpg" alt=""></figure>
                                                <p class="pad_bot2"><strong>Canc&uacute;n</strong></p>
                                                <p class="pad_bot2">Disfruta de la relajaci&oacute;n de sus balnearios o la practica alguno de sus deportes acu&aacute;ticos</p>
                                                <a href="#" class="marker_1"></a>
                                            </div>
                                            <div class="wrapper">
                                                <figure class="left marg_right1"><img src="images/page1_img3.jpg" alt=""></figure>
                                                <p class="pad_bot2"><strong>Nueva York</strong></p>
                                                <p class="pad_bot2">Con sus m&aacute;s de 2000 tiendas, es el lugar perfecto para conseguir todo y conocer sus atracciones.</p>
                                                <a href="#" class="marker_1"></a>
                                            </div>
                                        </div>
                                    </article>
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
                            });
                        </script>
                        </body>
                        </html>