<%@page import="Clases.Customer"%>
<%@page import="Clases.Flight"%>
<%@page import="Clases.City"%>
<%@page import="java.util.ArrayList"%>
<%
    int adultos = 1;
    int ninos = 0;
    Flight flightida = null;
    Flight flightregreso = null;
    int flightNo1 = -1;
    int flightNo2 = -1;

    if (request.getParameter("adultos") != null) {
        adultos = Integer.parseInt(request.getParameter("adultos"));
    }
    if (request.getParameter("ninos") != null) {
        ninos = Integer.parseInt(request.getParameter("ninos"));
    }

    if (request.getParameter("flight1") != null) {
        flightNo1 = Integer.parseInt(request.getParameter("flight1"));
        flightida = Flight.getFlight(flightNo1);
    }
    if (request.getParameter("flight2") != null) {
        flightNo2 = Integer.parseInt(request.getParameter("flight2"));
        flightregreso = Flight.getFlight(flightNo2);
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
                    <!-- columna derecha -->
                    <article class="col2 pad_left1">
                        <form method="POST" action="ReservarVuelo">
                            <h2>Reservar Vuelo</h2>
                            <% if (flightida != null) {%>
                            <div class="wrapper under">
                                <div id="flightDetails" class="under">
                                    <h3>Detalles del Vuelo de Ida</h3>
                                    <div><span class="city"><%=flightida.getSource().getCityName()%></span> --> <span class="city"><%=flightida.getDestination().getCityName()%></span></div>
                                    <div>
                                        <table>
                                            <tr>
                                                <th>N&uacute;mero de Vuelo</th>
                                                <th>Aerol&iacute;nea</th>
                                                <th>Fecha de Salida</th>
                                                <th>Fecha de Llegada</th>
                                            </tr>
                                            <tr>
                                                <td><%=flightida.getFlight_No()%></td>
                                                <td><%=flightida.getAirline_Name()%></td>
                                                <td><%=flightida.getDeparture_Time()%></td>
                                                <td><%=flightida.getArrival_Time()%></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div id="fareDetails">
                                    <h3>Detalles de la Tarifa de Vuelo de Ida</h3>
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
                                                <td><%=adultos%></td>
                                                <td>$ <%=flightida.getAdult_Fare() * adultos%> (<%=flightida.getAdult_Fare()%> x <%=adultos%>)</td>  
                                                <td>$ <%=flightida.getAirport_Tax()%></td>
                                                <td>$ <%=(flightida.getAdult_Fare() * adultos + flightida.getAirport_Tax())%></td>
                                            </tr>
                                            <tr>
                                                <td>Ni&ntilde;os</td>
                                                <td><%=ninos%></td>
                                                <td>$ <%=flightida.getChild_Fare() * ninos%> (<%=flightida.getChild_Fare()%> x <%=ninos%>)</td>  
                                                <td>$ <%=flightida.getAirport_Tax()%></td>
                                                <td>$ <%=(flightida.getChild_Fare() * ninos + flightida.getAirport_Tax())%></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div>               
                                    <br />
                                    <span class="floatRight">
                                        -------------------------<br />
                                        Total: $ <%=((flightida.getAdult_Fare() * adultos + flightida.getAirport_Tax()) + (flightida.getChild_Fare() * ninos + flightida.getAirport_Tax()))%><br />
                                        -------------------------
                                    </span>
                                </div>
                            </div>
                            <% }%>
                            <% if (flightregreso != null) {%>
                            <div class="wrapper under">
                                <div id="flightDetails" class="under">
                                    <h3>Detalles del Vuelo de Regreso</h3>
                                    <div><span class="city"><%=flightregreso.getSource().getCityName()%></span> --> <span class="city"><%=flightregreso.getDestination().getCityName()%></span></div>
                                    <div>
                                        <table>
                                            <tr>
                                                <th>N&uacute;mero de Vuelo</th>
                                                <th>Aerol&iacute;nea</th>
                                                <th>Fecha de Salida</th>
                                                <th>Fecha de Llegada</th>
                                            </tr>
                                            <tr>
                                                <td><%=flightregreso.getFlight_No()%></td>
                                                <td><%=flightregreso.getAirline_Name()%></td>
                                                <td><%=flightregreso.getDeparture_Time()%></td>
                                                <td><%=flightregreso.getArrival_Time()%></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div id="fareDetails">
                                    <h3>Detalles de la Tarifa de Vuelo de Regreso</h3>
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
                                                <td><%=adultos%></td>
                                                <td>$ <%=flightregreso.getAdult_Fare() * adultos%> (<%=flightregreso.getAdult_Fare()%> x <%=adultos%>)</td>  
                                                <td>$ <%=flightregreso.getAirport_Tax()%></td>
                                                <td>$ <%=(flightregreso.getAdult_Fare() * adultos + flightregreso.getAirport_Tax())%></td>
                                            </tr>
                                            <tr>
                                                <td>Ni&ntilde;os</td>
                                                <td><%=ninos%></td>
                                                <td>$ <%=flightregreso.getChild_Fare() * ninos%> (<%=flightregreso.getChild_Fare()%> x <%=ninos%>)</td>  
                                                <td>$ <%=flightregreso.getAirport_Tax()%></td>
                                                <td>$ <%=(flightregreso.getChild_Fare() * ninos + flightregreso.getAirport_Tax())%></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div>               
                                    <br />
                                    <span class="floatRight">
                                        -------------------------<br />
                                        Total: $ <%=((flightregreso.getAdult_Fare() * adultos + flightregreso.getAirport_Tax()) + (flightregreso.getChild_Fare() * ninos + flightregreso.getAirport_Tax()))%><br />
                                        -------------------------
                                    </span>
                                </div>
                            </div>
                            <% }%>
                            <h2>Nombres</h2>
                            <!-- adultos -->
                            <div class="wrapper under">
                                <div class="wrapper pad_bot1">
                                    <h3>Adulto 1: Pasajero Principal</h3>
                                    <table style="border-style:none; border-color: white;">
                                        <tr>
                                        <div class="form">
                                            <td><label for="title1">T&iacute;tulo </label></td>
                                            <td>
                                                <select name="title1" id="title1">
                                                <option>Sr.</option>
                                                <option>Sra.</option>
                                                <option>Srta.</option>
                                                </select>
                                            </td>
                                        </div>   
                                        </tr>
                                        <tr>
                                        <div class="form">
                                            <td><label for="fname1">Nombre: </label></td>
                                            <td><input type="text" name="fname1" id="fname1" /></td>
                                        </div>
                                        </tr>
                                        <tr>
                                        <div class="form">
                                            <td><label for="lname1">Apellidos: </label></td>
                                            <td><input type="text" name="lname1" id="lname1" /></td>
                                        </div>
                                        </tr>
                                        <tr>
                                        <div class="form">
                                            <td><label for="lemail1">E-mail: </label></td>
                                            <td><input type="text" name="lemail1" id="lemail1" /></td>
                                        </div>
                                        </tr>
                                    </table>
                                </div>
                                <%
                                    for (int i = 2; i <= adultos; i++) {
                                %>
                                <div class="wrapper pad_bot1">
                                    <h3>Adulto <%=i%>:</h3>
                                    <table>
                                        <tr>
                                        <div class="form">
                                            <td><label for="title<%=i%>">T&iacute;tulo </label></td>
                                            <td>
                                                <select name="title<%=i%>" id="title<%=i%>">
                                                <option>Sr.</option>
                                                <option>Sra.</option>
                                                <option>Srta.</option>
                                                </select>
                                            </td>
                                        </div>   
                                        </tr>
                                        <tr>
                                        <div class="form">
                                            <td><label for="fname<%=i%>">Nombre: </label></td>
                                            <td><input type="text" name="fname<%=i%>" id="fname<%=i%>" /></td>
                                        </div>
                                        </tr>
                                        <tr>
                                        <div class="form">
                                            <td><label for="lname<%=i%>">Apellidos: </label></td>
                                            <td><input type="text" name="lname<%=i%>" id="lname<%=i%>" /></td>
                                        </div>
                                        </tr>
                                        <tr>
                                        <div class="form">
                                            <td><label for="lemail<%=i%>">E-mail: </label></td>
                                            <td><input type="text" name="lemail<%=i%>" id="lemail<%=i%>" /></td>
                                        </div>
                                        </tr>
                                    </table>
                                </div>
                                <%
                                }%>
                            </div>
                            <!-- ninos -->
                            <div class="wrapper under">
                                <% for (int i = 1; i <= ninos; i++) {%>
                                <div class="wrapper pad_bot1">
                                    <h3>Infante <%=i%></h3>
                                    <div class="form">
                                        <label for="child-fname<%=i%>">Nombre: </label>
                                        <input type="text" name="child-fname<%=i%>" id="child-fname<%=i%>" />
                                    </div>
                                    <div class="form">
                                        <label for="child-lname<%=i%>">Apellidos: </label>
                                        <input type="text" name="child-lname<%=i%>" id="child-lname<%=i%>" />
                                    </div>
                                </div>
                                <% }%>
                            </div>
                            <input type="submit" class="button" value="Reservar" />
                            <% Customer[] customers = Customer.getCustomers(); %>
                            <select name="customerId" id="customerId">
                                <% for(int i = 0; i < customers.length; i++){ %>
                                <option value="<%= customers[i].getCustomerID() %>"><%= customers[i].getFirstName() %> <%= customers[i].getLastName() %></option>
                                <% } %>
                            </select>
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
                            </div>-->
                            <!-- div class="wrapper">
                                <figure class="left marg_right1"><img src="images/page2_img1.jpg" alt=""></figure>
                                <p><strong>Sed ut perspiciatis unde omnis iste natus</strong> error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorehjum ipsum quia dolor sit amet, consectetur vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium.</p>
                                <p><strong>Voluptatum deleniti atque corrupti quos</strong> dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa. Et harum quidem rerum facilisest et expedita distinctio. Nam libero tepore cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus omnis voluptas assumenda.</p>
                            </div>
                            <div class="wrapper line1 marg_bot1">
                                <ul class="list1 cols">
                                    <li><a href="#">At vero eos et accusamus et iusto odio</a></li>
                                    <li><a href="#">Dignissimos ducimus qui blanditiis praesentium</a></li>
                                    <li><a href="#">Voluptatum deleniti atque corrupti quos dolores</a></li>
                                    <li><a href="#">Quas molestias excepturi sint occaecati</a></li>
                                </ul>
                                <ul class="list1 cols pad_left1">
                                    <li><a href="#">At vero eos et accusamus et iusto odio</a></li>
                                    <li><a href="#">Dignissimos ducimus qui blanditiis praesentium</a></li>
                                    <li><a href="#">Voluptatum deleniti atque corrupti quos dolores</a></li>
                                    <li><a href="#">Quas molestias excepturi sint occaecati</a></li>
                                </ul>
                            </div>
                            Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda. error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorehjum ipsum quia dolor sit amet, consectetur vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium.
                            -->
                        </form>
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