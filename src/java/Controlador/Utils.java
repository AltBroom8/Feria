/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author usuario
 */
public class Utils {
    public static String formatearPrecio(double precio) {
            DecimalFormat formato = new DecimalFormat("#,##0.00");
            return formato.format(precio);
        }
    public static String formatearPrecio(BigDecimal precio) {
        if(precio==null){
            return "0";
        }
        // Convertir BigDecimal a double
        double precioDouble = precio.doubleValue();

        // Formatear el valor como una cadena con dos decimales y separadores de miles
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        return formato.format(precioDouble);
    }
}
