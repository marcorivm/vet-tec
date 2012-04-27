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
var validator = {
    /**
     * Metodo que recibe un objeto, ya sea un elemento DOM o una variable, recupera
     * su valor real y valida que este sea numerico
     */
    isNumeric: function(obj){
        var real_val = this.getRaw(obj);
        if(!this.isRawEmpty(real_val)) {
                return !isNaN(real_val)
        } else {
            return false;
        }
    },
    isDate: function(obj){
      var real_val = this.getRaw(obj);
      var date_pttrn = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
      var matches = real_val.match(date_pttrn);
      return !this.isRawEmpty(matches);
    },
    /**
     * Metodo que recibe un objeto, ya sea un elemento DOM o una variable, recupera
     * su valor real y valida que este no se encuentre vacio.
     */
    isEmpty: function(obj) {
        var real_val = this.getRaw(obj);
        return this.isRawEmpty(real_val);
    },
    /**
     * Metodo que recibe un valor y valida que este no se encuentre vacio.
     */
    isRawEmpty: function(real_val) {
        if(real_val == null || typeof(real_val) == "undefined" || real_val == "" || real_val.length == 0) {
            return true;
        } else {
            return false;
        }        
    },
    isEqual: function(obj_1, obj_2) {
        var real_val_1 = this.getRaw(obj_1);
        var real_val_2 = this.getRaw(obj_2);
        return (real_val_1 == real_val_2);
    },
    getRaw: function(obj) {
        if(this.isRawEmpty(obj)) {
            return null;
        } else if(typeof(obj) == "object") {
            obj =  (obj.jquery == "undefined")? obj : obj.get(0);
            if(this.isDomElement(obj)) {
                switch(obj.tagName.toLowerCase()) {
                    case "input":
                        /**
                         * Tipos a verificar:
                         * button
                         * checkbox
                         * file
                         * hidden
                         * image
                         * password
                         * radio
                         * reset
                         * submit
                         * text
                         */
                        switch(obj.type) {
                            case "button":
                            case "checkbox":
                            case "file":
                            case "hidden":
                            case "image":
                            case "password":
                            case "radio":
                            case "reset":
                            case "submit":
                            case "text":
                                return obj.value;
                                break;
                        }
                        break;
                }
            } else {
                //Iterar sobre las propiedades del objeto
            }
        } else if (typeof(obj) == "funciton") {
            return this.getRaw(obj());
        }
        return null;
    },
    isDomElement: function(obj) {
        return ((typeof HTMLElement === "object")? 
            obj instanceof HTMLElement :
            obj && typeof obj === "object" && obj.nodeType === 1 && typeof obj.nodeName==="string");
    }
}
