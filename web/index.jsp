<%-- 
    Document   : index
    Created on : 25 feb 2024, 20:12:12
    Author     : Carlos Gowing
--%>

<%
    response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ya huele a feria</title>
        <link rel="stylesheet" type="text/css" href="stylesIndex.css">
    </head>
    <body>
        <h1>Ya huele a feria!!!</h1>
        <form action="LoginServlet" method="post">
            <label for="fname">Socio:</label><br>
            <input type="text" id="socio" name="socio"><br>
            <label for="lname">Contraseña:</label><br>
            <input type="password" id="pswd" name="pswd">
            <input type="submit" value="Pa' Entro" id='acceder'>
        </form>
        <button id="playButton"><img src='img/play.png' alt='Play Icon' id='play-icon'></button>
        <script src="javascript/login.js"></script>
    </body>
</html>