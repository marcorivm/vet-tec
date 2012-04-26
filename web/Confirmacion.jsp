<%
    String mensaje = (String) request.getAttribute("msg");
    if (mensaje == null) {
        mensaje = "fail";
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Vettec</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/jquery.datepick.css" type="text/css" media="all">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
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
                    <jsp:include page="includes/navbar.jsp" />
                    <div class="wrapper" style="text-align: center">
                        <h3>Confirmacion Reservacion</h3>
                        <% if (!mensaje.equalsIgnoreCase("success")) {
                        %>
                        <h2>La reservacion fue exitosa</h2>
                        <%
                            int bookingId = 0;
                            if (request.getParameter("bookingId") != null) {
                                bookingId = Integer.parseInt(request.getParameter("bookingId"));
                            }
                        %>
                        <span>Para ver los detalles de la reservacion haz clic <a href="Reservacion?bookingId=<%=bookingId%>">aqu&iacute;</a></span>
                        <% } else {%>
                        <h2>No se pudo realizar la reservacion</h2>
                        <% }%>
                    </div>
                </header>
                <!-- / header -->
                <!-- content -->

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