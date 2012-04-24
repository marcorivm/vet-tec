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