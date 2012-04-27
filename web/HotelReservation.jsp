<%@page import="Clases.Hotel"%>
<%@page import="Clases.City"%>
<%@page import="Clases.Customer"%>
<%
    City[] cities = City.getCities();
    Customer[] customer = Customer.getCustomers();
    
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Seleccionar Hotel</title>
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
                        <form id="form_2" method="POST" action="SaveHB">
                        <div class="wrapper under">
                            <div class="wrapper">
                                <h3>Ingrese los datos de la reservaci&oacute;n.</h3>
                                <div class="form">
                                    <label for="lname1">Client ID </label>
                                    <select name="clientId" id="clientId" />
                                          <% if (customer != null)
                                               for (Customer c : customer) {%>
                                                <option value="<%=c.getCustomerID()%>"><%=c.getCustomerID()%></option>
                                               <% }%>
                                    </select>
                                </div>
                                <div class="wrapper">
                                                Tipo de Habitaci&oacute;n<br />
                                                <select name="capacidadHab">
                                                    <option value="1">Sencilla</option>
                                                    <option value="2">Doble</option>
                                                    <option value="3">Triple</option>
                                                    <option value="4">Cu&aacute;druple</option>
                                                </select><br />
                                </div>
                                <div class="wrapper">
                                                <input type ="radio" name="tipo" id="tipo" value="deluxe"/>                                                
                                                <label for="deluxe">Cuartos Deluxe</label> <br/>
                                                <input type="radio" name ="tipo" id="tipo" value="exe" />
                                                <i<label for="exe">Cuartos Exe</label>
                                            </div>
                                <div class="wrapper"><label for="date1">Llegada (dd/mm/aaaa)</label>
                                                <input type="text" name="dateLleg" id="dateLleg" /></div>
                                            <div id="regreso" class="wrapper"><label for="date2">Salida (dd/mm/aaaa)</label>
                                                <input type="text" name="dateSal" id="dateSal" /></div>
                            </div>
                        </div>
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