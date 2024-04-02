<%-- 
    Document   : articulo
    Created on : 28-feb-2024, 14:18:56
    Author     : Carlos Gowing
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="Controlador.Utils"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Cesta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controlador.BDManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="articulo.css">
    <title>Carrito</title>
</head>
<body>
    <%
        int idCarrito = (Integer)session.getAttribute("carrito");
        BDManager manager = new BDManager();
        List<Cesta> lista = manager.obtenerListaPorCarrito(idCarrito);
        BigDecimal total = (BigDecimal)session.getAttribute("total");
        String usuario = (String) session.getAttribute("username");
        
        
    %>
    <header>
        <h2>¿Cuanto e' la murta der <%= usuario %> ?</h2>
        <h3><%= Utils.formatearPrecio(total) %> €</h3>
        <form action="Pago" method="post">
            <button type='submit'>A paga'</button>
        </form>
        
    </header>
    <button id="volver" onclick='volver()'>Inicio</button>
    <h1>Dehgloze:</h1>
    <% if(lista.isEmpty()){%>
    <div id="cosa2">
        <h1 id="error"> No Tas' pedío na, miarma!!</h1>
        
    </div>
    <%}else{%>
    <div id="cosa">
        <% for (Cesta elemento: lista){
            String imagenSrc = "bbddImages/" + elemento.getId_producto() + ".jpg";
        %>
        <article>
            <img src=<%= imagenSrc %> alt="imagen">
            <div>
                <h3 class="nombre"> <%= elemento.getNombre() %></h3>
                <h3 class="precio"><%=Utils.formatearPrecio(elemento.getPrecio())  %> €/ unidad</h3>
                <h3 class="unidades"><%= elemento.getCantidad() %> Unidade'</h3>
            </div>
        </article>
        <%}%>
        
    </div>
    <%}%>
    <script src='javascript/articulo.js'></script>
</body>
</html>
