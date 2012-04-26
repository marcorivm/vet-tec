function ocultarRegreso() {
    var regreso = $("#regreso");
    regreso.slideUp();
    regreso.children("input").attr("disabled", "disabled");
}
function mostrarRegreso() {
    var regreso = $("#regreso");
    regreso.slideDown();
    regreso.children("input").removeAttr("disabled");
}

function mostrarForma(tipo){
    var hotel = document.getElementById("form_2");
    var vuelo = document.getElementById("form_1");
    if(tipo == "hotel"){
        hotel.style.display = "";
        vuelo.style.display = "none";
        document.getElementById("opcionVuelo").setAttribute("class", "")
        document.getElementById("opcionHotel").setAttribute("class", "active")
    }else{
        vuelo.style.display = "";
        hotel.style.display = "none";
        document.getElementById("opcionVuelo").setAttribute("class", "active")
        document.getElementById("opcionHotel").setAttribute("class", "")
    }  
}

function animacionHeader(imagenes) {
    var img0 = $(imagenes[0]);
    var img1 = $(imagenes[1]);
    var img2 = $(imagenes[2]);
    
    
    img0.fadeToggle(1500, function() {
        img1.fadeToggle(1500, function() {
            img2. fadeToggle(1500);
        });
    });
    
}