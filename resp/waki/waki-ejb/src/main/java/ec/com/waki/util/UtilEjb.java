/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leoz3
 */
public class UtilEjb {
     public static void imprimirMensajeError(String mensaje, Exception ex, Logger LOG) {
        ex.printStackTrace();
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("\n");
            sb.append("----------------------------------------------------------------------------\n");
            sb.append("                        SE HA PRESENTADO UN ERROR\n");
            sb.append("----------------------------------------------------------------------------\n");
            sb.append(" Mensaje: " + mensaje + "\n");
            if (ex != null) {
                sb.append(" Error:   " + ex.getMessage() + "\n");
                StackTraceElement[] elementosError = ex.getStackTrace();
                if (elementosError != null && elementosError.length > 0) {
                    sb.append(" Detalle:\n");
                    for (int i = 0; i < elementosError.length; i++) {
                        if (elementosError[i].getClassName().contains("ec.com.mall")) {
                            sb.append("\n      Clase:  " + elementosError[i].getClassName() + "\n");
                            sb.append("      Metodo: " + elementosError[i].getMethodName() + "\n");
                            sb.append("      Linea:  " + elementosError[i].getLineNumber() + "\n");
                        }
                    }
                }
            }
            sb.append("----------------------------------------------------------------------------\n");
            LOG.severe(sb.toString());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, " Problemas en imprimirMensajeError: ", e);
        }

    }

    public static String upperFirstLetter(String word) {
        if (word != null) {
            return Character.toUpperCase(word.charAt(0)) + word.substring(1);
        } else {
            return word;
        }
    }
}
