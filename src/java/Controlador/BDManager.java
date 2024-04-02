/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.Cesta;
import Modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Carlos Gowing
 */
public class BDManager {
    static Connection conexion;
    String url = "jdbc:derby://localhost:1527/practica10";
    String user = "root";
    String password = "1234";
    public static String actualUser="";
    public BDManager() {
        try {
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Ingresa a la base de datos");
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException(e);
        } 
    }
    public boolean login(String username, String password2) {
        String query = "SELECT * FROM socio WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password2);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                actualUser=username;
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT id, nombre,descripcion, precio, foto, cantidad FROM PRODUCTO"; // Reemplaza "tuTabla" con el nombre de tu tabla

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto(resultSet.getInt("id"),resultSet.getString("nombre"),
                resultSet.getString("descripcion"),resultSet.getDouble("precio"),resultSet.getBytes("foto"),resultSet.getInt("cantidad"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
    public Producto obtenerProductoPorId(int id){
        String query = "SELECT * FROM producto WHERE id = ?";
        
        try(PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            if(resultset.next()){
                Producto producto = new Producto(resultset.getInt("id"),
                resultset.getString("nombre"),resultset.getString("descripcion"),
                resultset.getDouble("precio"),null,0);
                return producto;
            }else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getUserId(String username) {
        String query = "SELECT id FROM socio WHERE username = ?";
        int userId = -1; // Valor predeterminado si no se encuentra el usuario

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en tu aplicación
        }

        return userId;
    }
    public Boolean userTieneCarrito(int id) {
        String query = "SELECT COUNT(*) AS carritos_count FROM Carrito WHERE ID_Usuario = ?";
        Boolean tieneCarrito = false;

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int carritosCount = resultSet.getInt("carritos_count");
                    tieneCarrito = carritosCount > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en tu aplicación
        }

        return tieneCarrito;
    }
     public int nuevoRegistroCarrito(int idUser) {
        int id = -1;
        try {
            // Suponiendo que ID_CARRITO es una columna autoincrementable
            String sql = "INSERT INTO carrito ( id_usuario) VALUES ( ?)";
            id = (this.tablaCarritoVacia())?1:this.obtenerUltimoIdCarrito();
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
              
                preparedStatement.setInt(1, idUser);

                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Nuevo registro de carrito creado con éxito.");
                } else {
                    System.out.println("Error al crear un nuevo registro de carrito.");
                }
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en una aplicación real
        }
        return id;
    }
    
    public int existeEnDetalleCarrito(int idProducto, int idCarrito) {
    try {
        String sql = "SELECT ID FROM DETALLECARRITO WHERE ID_CARRITO = ? AND ID_PRODUCTO = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCarrito);
            preparedStatement.setInt(2, idProducto);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Si hay un resultado, significa que existe en el detalle del carrito
                    return resultSet.getInt("ID");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Manejo apropiado de excepciones en una aplicación real
    }

    // Si no encuentra el producto en el detalle del carrito, devuelve -1
    return -1;
}
    
    public int cantidadEnTabla(int idDetalle) {
    try {
        String sql = "SELECT cantidad FROM DETALLECARRITO WHERE id = ? ";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idDetalle);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("cantidad");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }

    return 0;
}
    public void updateCantidad(int idDetalle,int nuevaCantidad){
        try{
            String sql = "Update DETALLECARRITO SET CANTIDAD = ? WHERE ID = ? ";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idDetalle);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Operación de actualización exitosa.");
            } else {
                System.out.println("No se encontraron registros para actualizar.");
            }
        }catch(SQLException e){
            e.printStackTrace(); 
        }
    }
    public boolean tablaCarritoVacia() {
        // Consulta para verificar si la tabla Carrito está vacía
        String query = "SELECT COUNT(*) AS total FROM Carrito";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next() && resultSet.getInt("total") == 0;
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean detalleCarritoVacia() {
        // Consulta para verificar si la tabla Carrito está vacía
        String query = "SELECT COUNT(*) AS total FROM detallecarrito";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next() && resultSet.getInt("total") == 0;
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public int obtenerUltimoIdCarrito()  {
        // Consulta para obtener el último ID en la tabla Carrito
        String query = "SELECT MAX(ID) AS ultimoId FROM Carrito";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt("ultimoId") : 0;
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public int obtenerUltimoIdDetalle()  {
        // Consulta para obtener el último ID en la tabla Carrito
        String query = "SELECT MAX(ID) AS ultimoId FROM detallecarrito";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt("ultimoId") : 0;
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public int idCarritoParaUser(int idUser){
        int idCarrito = -1;
        String query = "SELECT ID FROM CARRITO WHERE ID_USUARIO = ?";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, idUser); 
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idCarrito = resultSet.getInt("id");
            }
            return idCarrito;
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
            return idCarrito;
        }
    }
    public void insertarDetalleCarrito(int id, int idCarrito, int idProducto, int cantidad) {
        try {
            String sql = "INSERT INTO DETALLECARRITO (id,id_carrito, id_producto, CANTIDAD) VALUES (? ,?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, idCarrito);
                preparedStatement.setInt(3, idProducto);
                preparedStatement.setInt(4, cantidad);

                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Operación de inserción exitosa.");
                } else {
                    System.out.println("Error al insertar en DETALLECARRITO.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en una aplicación real
        }
    }
    public Map<Integer, Integer> cargarDetallesCarrito() {
        Map<Integer, Integer> detallesCarrito = new HashMap<>();

        try {
            String sql = "SELECT ID, CANTIDAD FROM DETALLECARRITO";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    int cantidad = resultSet.getInt("CANTIDAD");
                    detallesCarrito.put(id, cantidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en una aplicación real
        }

        return detallesCarrito;
    }
    public ArrayList<Cesta> obtenerListaPorCarrito(int idCarrito){
        ArrayList<Cesta> lista = new ArrayList<>();
        String sql = "SELECT P.ID as MIPRODUCTO, P.NOMBRE as NOMBRE, P.PRECIO AS PRECIO, D.CANTIDAD AS CANTIDAD FROM DETALLECARRITO D " +
                     "INNER JOIN PRODUCTO P ON D.ID_PRODUCTO = P.ID " +
                     "WHERE D.ID_CARRITO = ? AND D.CANTIDAD > 0";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCarrito);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idProducto = resultSet.getInt("MIPRODUCTO");
                    String nombre = resultSet.getString("NOMBRE");
                    double precio = resultSet.getDouble("PRECIO");
                    int cantidad = resultSet.getInt("CANTIDAD");
                    Cesta micesta = new Cesta(idProducto,nombre,precio,cantidad);
                    lista.add(micesta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo apropiado de excepciones en una aplicación real
        }
        return lista;
    }
    public double calcularTotal(int idCarrito){
        try {
            String sql = "SELECT SUM(P.PRECIO * D.CANTIDAD) AS precioTotal FROM DETALLECARRITO D " +
                         "INNER JOIN PRODUCTO P ON D.ID_PRODUCTO = P.ID WHERE ID_CARRITO = ?";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql); 
            preparedStatement.setInt(1, idCarrito);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("precioTotal");
            }
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.00;
    }
    public void eliminaRegistros(int idCarrito){
        try {
            String sql = "DELETE FROM DETALLECARRITO WHERE ID_CARRITO = ?";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql); 
            preparedStatement.setInt(1, idCarrito);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cierra(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, "Error al cerrar la base de datos.", ex);
        }
    }
}

