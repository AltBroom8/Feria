const carta = document.getElementById('verCarta');
carta.addEventListener('click', function() {
    console.log('entra');
    window.location.href = 'carta.jsp';
});

const carrito = document.getElementById('carrito');
carrito.addEventListener('click', function() {
    console.log('entra');
    window.location.href = 'articulo.jsp';
});