<%-- 
    Document   : Error
    Created on : 26 feb 2024, 15:37:04
    Author     : solos
--%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erró</title>
    <link rel="stylesheet" type="text/css" href="error.css">
</head>
<body>
    <header>
        <h1 id="titulo">Illo, tu no tiene el carné de la caceta</h1>
    </header>
    <div id="image-container">
        <img src="img/tragabuche.png" alt="imagen de tragabuche">
    </div>
    <div id="descripcion">
        <h1 id="motivo">Ahcezo denegao, xurri</h1>
        
    </div>
    <button onclick="volver()">🢀 Volvé</button>
    <script src='javascript/error.js'></script>
</body>
</html>