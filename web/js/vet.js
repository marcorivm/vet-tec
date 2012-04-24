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