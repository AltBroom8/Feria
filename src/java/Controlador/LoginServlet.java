/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BDManager manager;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String username = request.getParameter("socio");
        String password = request.getParameter("pswd");

        // Verificar si el usuario existe en la base de datos (simulado)
        boolean usuarioExiste = verificarUsuarioEnBaseDeDatos(username, password);

        // Redirigir a una página según el resultado
        if (usuarioExiste) {
            sesion.setAttribute("username",username);
            sesion.setAttribute("password", password);
            Map<Integer,Integer> diccionario = manager.cargarDetallesCarrito();
            sesion.setAttribute("mapa", diccionario);
            int idUser = manager.getUserId(username);
            Boolean tieneCarrito = manager.userTieneCarrito(idUser);
            int idCarrito = -1;
            if (tieneCarrito==false){
                idCarrito = manager.nuevoRegistroCarrito(idUser);
            }else{
                idCarrito = manager.idCarritoParaUser(idUser);
            }
            sesion.setAttribute("carrito", idCarrito);
            double total = manager.calcularTotal(idCarrito);
            sesion.setAttribute("total", BigDecimal.valueOf(total));
            response.sendRedirect("MainWeb.jsp");
        } else {
            response.sendRedirect("Error.jsp");
        }
    }
    
    
    private boolean verificarUsuarioEnBaseDeDatos(String username, String password) {
        manager = new BDManager();
        Boolean result = manager.login(username,password);
        return result;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

