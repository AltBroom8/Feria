function aumentarValor(button) {
    event.preventDefault();
    var row = button.closest('tr');
    var valorInput = row.querySelector('.valor');
    
    // Check for null and use 0 as default value
    var valorActual = parseInt(valorInput.value || "0");
    
    valorInput.value = valorActual + 1;
}

function disminuirValor(button) {
    event.preventDefault();
    var row = button.closest('tr');
    var valorInput = row.querySelector('.valor');
    
    // Check for null and use 0 as default value
    var valorActual = parseInt(valorInput.value || "0");
    
    valorInput.value = Math.max(0, valorActual - 1);
}

function abrirDetalle(id) {
    event.preventDefault();
    var url = 'detalle.jsp?id=' + id;
    window.open(url, '_blank');
}

function volver(){
    window.location.href = "MainWeb.jsp";
}