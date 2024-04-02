package Controlador;

import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "addCarrito", urlPatterns = {"/addCarrito"})
public class addCarrito extends HttpServlet {
    Map<Integer,Integer> diccionario ;
   


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Integer idCarrito = (Integer)sesion.getAttribute("carrito");
        BDManager manager = new BDManager();
        String contadorValue = request.getParameter("contador");
        System.out.print("El valor del contador es: "+contadorValue);
        int contador = Integer.parseInt(contadorValue);
        for(int i = 0; i<contador ; i++){
            String idProductoString = request.getParameter("necesitoId_" + i);
            String cantidadProductoString = request.getParameter("cantidad_" + i);
            System.out.print("El valor del id es: "+idProductoString);
            System.out.print("El valor del cantidad es: "+cantidadProductoString);
            int idProducto = Integer.parseInt(idProductoString);
            int cantidad = Integer.parseInt(cantidadProductoString);

            int idDetalle = manager.existeEnDetalleCarrito(idProducto, idCarrito);
            if(cantidad>0){
                if(idDetalle!=-1){
                    int cantidadActual = manager.cantidadEnTabla(idDetalle);
                    int nuevaCantidad = cantidadActual + cantidad;
                    manager.updateCantidad(idDetalle, nuevaCantidad);
                }else{
                    if(manager.detalleCarritoVacia()){
                        idDetalle=1;
                    }else{
                        idDetalle = manager.obtenerUltimoIdDetalle()+1;
                    }

                    manager.insertarDetalleCarrito(idDetalle, idCarrito, idProducto, cantidad);
                }
            }
        }
        double total = manager.calcularTotal(idCarrito);
        sesion.setAttribute("total", BigDecimal.valueOf(total));
        manager.cierra();
        response.sendRedirect("articulo.jsp");
    }
}
