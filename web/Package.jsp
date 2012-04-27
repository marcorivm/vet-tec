<%@page import="Clases.Flight_Booking"%>
<%@page import="Clases.Customer"%>
<%@page import="Clases.Flight"%>
<%@page import="Clases.City"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Seleccionar Vuelos</title>
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
                        <form method="POST" action="ReservarP">
                            <%
                                Flight_Booking fbTo = null;
                                Flight flightTo = null;
                                Flight_Booking fbFrom = null;
                                Flight flightFrom = null;
                                double totalAdultTo = 0;
                                double totalChildTo = 0;
                                double totalTo = 0;
                                double totalAdultFrom = 0;
                                double totalChildFrom = 0;
                                double totalFrom = 0;
                                //double discountTo = 0;
                                int noAdults = 0;
                                int noChild = 0;
                                if (request.getSession().getAttribute("fbTo") != null) {
                                    fbTo = (Flight_Booking) (request.getSession().getAttribute("fbTo"));
                                    flightTo = fbTo.getFlight();
                                    totalAdultTo = fbTo.getNoOfAdults() * flightTo.getAdult_Fare();
                                    totalChildTo = fbTo.getNoOfChildren() * flightTo.getChild_Fare();
                                    totalTo = totalAdultTo + totalChildTo + flightTo.getAirport_Tax();
                                    noAdults = fbTo.getNoOfAdults();
                                    noChild = fbTo.getNoOfChildren();
                                }
                                if (request.getSession().getAttribute("fbFrom") != null) {
                                    fbFrom = (Flight_Booking) (request.getSession().getAttribute("fbFrom"));
                                    flightFrom = fbFrom.getFlight();
                                    totalAdultFrom = fbFrom.getNoOfAdults() * flightFrom.getAdult_Fare();
                                    totalChildFrom = fbFrom.getNoOfChildren() * flightFrom.getChild_Fare();
                                    totalFrom = totalAdultFrom + totalChildFrom + flightFrom.getAirport_Tax();
                                }
                            %>
                            <h2>Reservar Vuelo</h2>
                            <!--INFORMACION VUELO DE IDA-->
                            <% if (fbTo != null) {%>
                            <h3>Datos de Vuelo de Ida</h3>
                            <div class="wrapper">
                                Boleto de <span class="city"><%= (flightTo.getSource()).getCityName()%></span> to <span class="city"><%= (flightTo.getDestination()).getCityName()%></span><br />
                                Adultos <span class="bold"><%= fbTo.getNoOfAdults()%></span><br />
                                Ni&ntilde;os <span class="bold"><%= fbTo.getNoOfChildren()%></span><br />
                                Tarifa Total Adultos <span class="bold">$ <%= totalAdultTo%></span><br />
                                Tarifa Total Ni&ntilde;os <span class="bold">$ <%= totalChildTo%></span><br />
                                Impuestos <span class="bold">$ <%= flightTo.getAirport_Tax()%></span><br />
                                Total <span class="bold">$ <%= totalTo%></span><br />
                            </div>
                            <% }%><!--FIN VUELO DE IDA-->
                            <!--INFORMACION VUELO DE VUELTA-->
                            <% if (fbFrom != null) {%>
                            <h3>Datos de Vuelo de VUELTA</h3>
                            <div class="wrapper">
                                Boleto de <span class="city"><%= (flightFrom.getSource()).getCityName()%></span> to <span class="city"><%= (flightFrom.getDestination()).getCityName()%></span><br />
                                Adultos <span class="bold"><%= fbFrom.getNoOfAdults()%></span><br />
                                Ni&ntilde;os <span class="bold"><%= fbFrom.getNoOfChildren()%></span><br />
                                Tarifa Total Adultos <span class="bold">$ <%= totalAdultFrom%></span><br />
                                Tarifa Total Ni&ntilde;os <span class="bold">$ <%= totalChildFrom%></span><br />
                                Impuestos <span class="bold">$ <%= flightFrom.getAirport_Tax()%></span><br />
                                Total <span class="bold">$ <%= totalFrom%></span><br />
                            </div>
                            <% }%><!--FIN VUELO DE VUELTA-->
                            <!-- adultos -->
                            <div class="wrapper under">
                                <div class="wrapper pad_bot1">
                                    <h3>Adulto 1: Pasajero Principal</h3>
                                    <label for="title1">T&iacute;tulo </label>
                                    <select name="title1" id="title1">
                                        <option>Sr.</option>
                                        <option>Sra.</option>
                                        <option>Srta.</option>
                                    </select><br/>
                                    <label for="lname1">Apellidos: </label>
                                    <input type="text" name="lname1" id="lname1" /><br/>
                                    <label for="fname1">Nombre: </label>
                                    <input type="text" name="fname1" id="fname1" /><br/>
                                    <label for="email1">E-mail: </label>
                                    <input type="text" name="lemail1" id="lemail1" />

                                </div>
                                <%
                                    for (int i = 2; i <= noAdults - 1; i++) {
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
                                            <td><label for="lname<%=i%>">Apellidos: </label></td>
                                            <td><input type="text" name="lname<%=i%>" id="lname<%=i%>" /></td>
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
                            <% for (int i = 1; i <= noChild; i++) {%>
                            <div class="wrapper under">
                                <div class="wrapper pad_bot1">
                                    <h3>Infante <%=i%></h3>
                                    <div class="form">
                                        <label for="child-lname<%=i%>">Apellidos: </label>
                                        <input type="text" name="child-lname<%=i%>" id="child-lname<%=i%>" />
                                    </div>
                                    <div class="form">
                                        <label for="child-fname<%=i%>">Nombre: </label>
                                        <input type="text" name="child-fname<%=i%>" id="child-fname<%=i%>" />
                                    </div>
                                </div>
                            </div>
                            <% }%>
                            
                            <input type="submit" class="button" value="Reservar" />
                            <% Customer[] customers = Customer.getCustomers();%>
                            <label for="customerId">Agente de Ventas:</label>
                            <select name="customerId" id="customerId">
                                <% for (int i = 0; i < customers.length; i++) {%>
                                <option value="<%= customers[i].getCustomerID()%>"><%= customers[i].getFirstName()%> <%= customers[i].getLastName()%></option>
                                <% }%>
                            </select>
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