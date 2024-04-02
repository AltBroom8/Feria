<%-- 
    Document   : MainWeb
    Created on : 26 feb 2024, 15:36:09
    Author     : Carlos
--%>

<%@page import="Controlador.Utils"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>
<%
    HttpSession sesion = request.getSession();
    // Obtén el nombre del usuario de la sesión (reemplázalo con el atributo correcto)
    
    String nombreUsuario = (String) sesion.getAttribute("username");
    BigDecimal total = (BigDecimal)session.getAttribute("total");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pahina prinçipá</title>
        <link rel="stylesheet" type="text/css" href="MainStyles.css">
    </head>
    <body>
        <header>
        <button id="verCarta">Vé Carta</button>
        </header>
        <div id="main">
            <h1 id="user"><%= nombreUsuario%></h1>
            <h3 id="total">La murta: <%= Utils.formatearPrecio(total) %> l'euro</h3>
            <button id="carrito" >Carrito
                <img src="img/carrito.png" alt="Icono">
                </button>
            <form action='Cierra' method="post">
                <button id="cerrar" type="submit">🢀 Cerrá zeción</button>
            </form>
        </div>
        <script src="javascript/main.js"></script>
    </body>
</html>