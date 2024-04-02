<%-- 
    Document   : carta
    Created on : 27-feb-2024, 17:46:39
    Author     : Carlos Gowing
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="Controlador.Utils"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%@page import="Controlador.BDManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>
<%
    String nombreUsuario = (String) session.getAttribute("username");
    BDManager manager = new BDManager();
    Integer idCarrito = (Integer)session.getAttribute("carrito");
    
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="carta.css">
    <title>Carta</title>
</head>
<body>
    <header>
        <button id="volver" onclick='volver()'>🢀 Volvé</button>
        
    </header>
    <div>
        <%
    
    List<Producto> productos = manager.obtenerProductos();
        %>      
        <form method="post" action="addCarrito">
            <div id="carrito-container">
                <button id="Carrito" type="submit">
                    Añadí ar carrito
                    <img src="img/carrito.png">
                </button>
            </div>
            <div id="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Detalle</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int contador = 0;
                            for (Producto producto : productos) {
                            %>  
                            <tr>             
                                    <td><%= producto.getNombre()%></td>
                                    <td><%= Utils.formatearPrecio(producto.getPrecio())+ "€"%></td>
                                    <td><button class="detalleButton" onclick="abrirDetalle('<%= producto.getId() %>')">Ficha</td>
                                    <td><label for="valor">Pide:</label>
                                        <input type="hidden" class="necesitoId" name="necesitoId_<%= contador %>" value="<%= producto.getId() %>">
                                        <input type="text" class="valor" name="cantidad_<%= contador %>" value="0" readonly>
                                        <button onclick="aumentarValor(this)">🢁</button>
                                        <button onclick="disminuirValor(this)">🢃</button>
                                </td>
                            </tr><%
                                contador++;
                                }
                            
                            manager.cierra();%>
                    </tbody>
                </table>
            </div>
            <input type="hidden" name="contador" value="<%= contador %>">
        </form>
    </div>
    <script src="javascript/carta.js"></script>
   
</body>
</html>
