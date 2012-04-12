<!DOCTYPE html>
<html lang="en">
    <head>
        <title>About</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_600.font.js"></script>
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
                        <h3>Buscar Hotel</h3>
                        <div class="pad">
                            <div class="wrapper under">
                                <form>
                                    <div id="aldo">
                                        <div><label for="customerId">Id Cliente</label>
                                            <select name="customerId" id="customerId">
                                                <option value="blank">---------------</option>
                                            </select>
                                        </div>
                                        <div><label for="city">City</label>
                                            <select name="city" id="city">
                                                <option value="blank">Chennai</option>
                                            </select>
                                        </div>
                                        <div>
                                            <label for="arrival">Arrival Date</label>
                                            <input type="text" name="arrival" id="arrival" />
                                            <input type="button" value=">>" />
                                        </div>
                                        <div>
                                            <label for="departure">Departure Date</label>
                                            <input type="text" name="departure" id="departure" />
                                            <input type="button" value=">>" />
                                        </div>
                                        <div>
                                            <label for="dlx_rooms">DLX Rooms</label>
                                            <input type="text" name="dlx_rooms" id="dlx_rooms" />
                                            <label for="exe_rooms">EXE Rooms</label>
                                            <input type="text" name="exe_rooms" id="exe_rooms" />
                                        </div>
                                        <div>
                                            <input type="button" value="Search For Hotels" />
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="wrapper">
                                <figure class="left marg_right1"><img src="images/page1_img3.jpg" alt=""></figure>
                                <p class="pad_bot2"><strong>Cruise<br>Holidays</strong></p>
                                <p class="pad_bot2">Lorem ipsum dolor sit amet, consect etuer adipiscing.</p>
                                <a href="#" class="marker_1"></a>
                            </div>
                        </div>
                    </article>
                    <!-- columna derecha -->
                    <article class="col2 pad_left1">
                        <nav>
				<ul id="menu">
					<li><a href="index.jsp" class="nav6">Home</a></li>
					<li><a href="vuelos.jsp" class="nav6">Vuelos</a></li>
					<li><a href="Hotel.jsp" class="nav6">Hoteles</a></li>
					<li><a href="Destinations.html" class="nav6">Destinations</a></li>
					<li class="end"><a href="Contacts.html" class="nav6">Contacts</a></li>
				</ul>
			</nav>
                        <h2>Vuelos Disponibles</h2>
                        <div class="wrapper under">
                            <form>
                                <div><table>
                <tr>
                    <th>Hotel</th>
                    <th>Location</th>
                    <th>Book This</th>
                </tr>
                <tr>
                    <td>Taj</td>
                    <td>Chennai</td>
                    <td><input type="button" value="Book This" /></td>
                </tr>
                <tr>
                    <td>Oberoi</td>
                    <td>Chennai</td>
                    <td><input type="button" value="Book This" /></td>
                </tr>
				<tr>
                    <td>Ashoka</td>
                    <td>Chennai</td>
                    <td><input type="button" value="Book This" /></td>
                </tr>
				<tr>
                    <td>Princeton</td>
                    <td>Chennai</td>
                    <td><input type="button" value="Book This" /></td>
                </tr>
            </table></div>
                            </form>
                        </div>
                        <h2>Revisar Hotel</h2>
                        <div class="wrapper under">
                            <div id="flightDetails" class="under">
                                <h3>Revisar Detalles del Hotel</h3>
                                <div><span class="city">Chennai</span></div>
                                <div>
                                    <table>
                    <tr>
                        <th>Hotel Name</th>
                        <th>Check In Date</th>
                        <th>Check Out Date</th>
                        <th>DLX Rooms</th>
						<th>EXE Rooms</th>
                    </tr>
                    <tr>
                        <td>Princeton</td>
                        <td>16-Jan-2012</td>
                        <td>20-Jan-2012</td>
                        <td><b>2</b></td>
						<td><b>2</b></td>
                    </tr>
                </table>
                                </div>
                            </div>
                            <div id="fareDetails">
                                <h3>Revisar Detalles de la Tarifa</h3>
                                <div>
                                    <table>
                    <tr>
                        <th>Room Type</th>
                        <th>Fare per day</th>
                        <th>No of Rooms</th>
                        <th>Fare</th>
                        <th>Tax</th>
						<th>Total Amount</th>
                    </tr>
                    <tr>
                        <td>DLX</td>
                        <td>1000</td>
                        <td>2</td>
						<td>2000</td>
                        <td>150</td>
                        <td>2150</td>
                    </tr>
                    <tr>
                        <td>DLX</td>
                        <td>2000</td>
                        <td>2</td>
						<td>4000</td>
                        <td>150</td>
                        <td>4150</td>
                    </tr>
                </table>
                                </div>
                            </div>
                            <div>
			<br/>
            <span class="floatRight">
                -------------------------<br />
                Grand Total: Rs. 6300<br />
                -------------------------
            </span>
			<br/>
			<span><input type="button" value="Continue Booking" /></span>
        </div>
                        </div>
                        
                        <div class="wrapper">
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
                    <a rel="nofollow" href="http://www.templatemonster.com/" target="_blank">Website template</a> designed by TemplateMonster.com<br>
                    <a href="http://www.templates.com/product/3d-models/" target="_blank">3D Models</a> provided by Templates.com
                </footer>
                <!-- / footer -->
            </div>
        </div>
        <script type="text/javascript"> Cufon.now(); </script>
    </body>
</html>