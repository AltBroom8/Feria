<%-- 
    Document   : detalle
    Created on : 27-feb-2024, 21:43:26
    Author     : Carlos Gowing
--%>

<%@page import="Controlador.Utils"%>
<%@page import="Controlador.BDManager"%>
<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>
<% BDManager manager = new BDManager();
   String productIdString = request.getParameter("id");
   int id = Integer.parseInt(productIdString);
   Producto miproducto = manager.obtenerProductoPorId(id);
   String imagenSrc = "bbddImages/" + miproducto.getId() + ".jpg";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="detalle.css">
    <title>INFO</title>
</head>
<body>
    <div id="foto_y_titulo">
        <div id="image_div">
            <img src=<%= imagenSrc %> alt="imagen del elemento">
        
        </div>
        <h3 id="nombre"><%= miproducto.getNombre() %></h3>
    </div>
    <div id="info">
        <h3 id="desc">DEHCRIÇION:</h3><br/>
        <p><%= miproducto.getDesc()  %> 
        </p>
        <h3 id="precio"><%= Utils.formatearPrecio(miproducto.getPrecio())+ " €" %></h3>
    </div>
</body>
</html>