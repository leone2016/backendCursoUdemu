/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author leoz3
 */
public class DateUtil {
       public static final String FORMAT_ddMMyyyy = "dd/MM/yyyy";
    public static final String FORMAT_yyyyMMdd = "yyyyMMdd";
    public static final String FORMAT_HHmmss = "HH:mm:ss";
    public static final String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String FORMAT_yyyy_mm_dd = "yyyy-mm-dd";
    public static final String FORMAT_yyyy_MM_dd_T_HH_mm = "yyyy-MM-dd'T'HH:mm";
    public static final String FORMAT_yyyy_MM_dd_T_HH_mm_ss = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_yyyy_MM_dd_HH_mm_ss_Z = "yyyy-MM-dd HH:mm:ss '-05:00'";
    public static final String FORMAT_MMM_dd_yyyy = "MMM dd, yyyy";
    public static final String FORMAT_MMM_dd_yyyy__HHmm = "MMM dd, yyyy | HH:mm";
    public static final String FORMAT_MMM_dd_yyyy__HHmmss = "MMM dd, yyyy HH:mm:ss";
    public static final String FORMAT_MMM_dd_yyyy__HHmm_XXX = "MMM dd, yyyy | HH:mm XXX";
    public static final String FORMAT_MMM_dd = "MMM dd";
    public static final String FORMAT_dd = "dd";
    public static final String FORMAT_dd_MMM = "dd MMM";
    public static final String FORMAT_MMM_yyyy = "MMM, yyyy";
    public static final String FORMAT_HHmm = "HH:mm";
    public static final String FORMAT_MMM_dd_yyyy__HHmm_XXX_GMT = "MMM dd, yyyy | HH:mm 'GMT -05:00' ";
    public static final String FORMAT_Y = "y";
    public static final String FORMAT_yyyy_MMM_dd_T_HH_mm_ss = "yyyy_MMM_dd'T'HH_mm_ss";
    public static final String FORMAT_EEEE = "EEEE";
    public static final String FORMAT_EE = "EE";

    public static Date StringToDate(String formato, String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        Date retorno;
        try {
            retorno = formatter.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            retorno = null;
        }
        return retorno;
    }

    public static String DateToString(String formato, Date fecha) {

        SimpleDateFormat formatter = new SimpleDateFormat(formato, Locale.US);
        String retorno = null;
        try {
            if (fecha != null) {
                retorno = formatter.format(fecha);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retorno = null;
        }
        return retorno;
    }

    public static Integer calcularEdad(Date fecha) {

        Calendar fechaNacimiento = Calendar.getInstance();
        Calendar fechaActual = Calendar.getInstance();
        fechaNacimiento.setTime(fecha);

        int anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);

        if (mes < 0 || (mes == 0 && dia < 0)) {
            anio--;
        }

        return anio;
    }

    public static Integer calcularDiferenciasDias(Date fechaIni, Date fechaFin) {
        Integer valor = 0;
        System.out.println(fechaIni + "-----" + fechaFin);
        if (fechaIni != null && fechaFin != null) {
            valor = (int) ((fechaFin.getTime() - fechaIni.getTime()) / Constantes.VALOR_MILISEGUNDOS);
        }
        return valor;
    }

    public static Integer calculareDiferenciasDiasHoraCero(Date fechaIni, Date fechaFin) {
        Integer valor = 0;
        fechaIni = DateUtil.obtenerFechaSinHora(fechaIni);
        fechaFin = DateUtil.obtenerFechaSinHora(fechaFin);
        System.out.println(fechaIni + "---*888888--" + fechaFin);
        if (fechaIni != null && fechaFin != null) {
            valor = (int) ((fechaFin.getTime() - fechaIni.getTime()) / Constantes.VALOR_MILISEGUNDOS);
        }
        return valor;
    }

    public static void main(String[] args) {
        //System.out.println("-*-*-*-*-*-*-*-*-*");
        int dias = DateUtil.calculareDiferenciasDiasHoraCero(DateUtil.tomarFechaActual(), DateUtil.StringToDate(FORMAT_ddMMyyyy, "28/04/2018"));
        System.err.println(">>>" + dias);
    }

    public static Date obtenerFechaSinHora() {
        String fecha = DateToString(FORMAT_ddMMyyyy, new Date());
        return StringToDate(FORMAT_ddMMyyyy, fecha);
    }

    public static Date obtenerHoraSinFecha(Date fecha) {
        String fecha1 = DateToString(FORMAT_ddMMyyyy, fecha);
        return StringToDate(FORMAT_HHmm, fecha1);
    }

    public static Integer calcularEdad(Date fechaNa, Date fechFa) {

        try {
            Calendar fechaNacimiento = Calendar.getInstance();
            Calendar fechaFallecimiento = Calendar.getInstance();
            fechaNacimiento.setTime(fechaNa);
            fechaFallecimiento.setTime(fechFa);

            int anio = fechaFallecimiento.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
            int mes = fechaFallecimiento.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
            int dia = fechaFallecimiento.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);

            if (mes < 0 || (mes == 0 && dia < 0)) {
                anio--;
            }
            return anio;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String tomarFecha(Date fercha, String formato) {
        SimpleDateFormat format = new SimpleDateFormat(formato);
        String retorno = format.format(fercha);
        return retorno;

    }

    public static Date obtenerFechaSinHora(Date fechaIn) {
        String fecha = DateToString(FORMAT_ddMMyyyy, fechaIn);
        return StringToDate(FORMAT_ddMMyyyy, fecha);
    }

    public static Date tomarFechaActual() {
        Date fechaActual = null;
        DateFormat inFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        inFormat.setTimeZone(TimeZone.getTimeZone("America/Guayaquil"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            fechaActual = formatter.parse(inFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            new Date();
        }
        return fechaActual;
    }

    public static Date sumarRestarDiasFecha(Date fecha, int dias) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    public static Date sumarRestarHorasFecha(Date fecha, int horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.HOUR_OF_DAY, horas);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    public static int obtenerParametro(Date fecha, int parametro) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(parametro);
    }

    public static Date setearHoraMinutoFecha(Date fecha, int horas, int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.set(Calendar.HOUR_OF_DAY, horas);  // numero de días a añadir, o restar en caso de días<0
        calendar.set(Calendar.MINUTE, minutos);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    public static Integer calcularDiferenciasHoras(Date fechaSalida, Date fechaActual) {
        Integer maxBloqueo = 0;
        Calendar c = Calendar.getInstance();
        Calendar fechaInicial = new GregorianCalendar();
        Calendar fechaFinal = new GregorianCalendar();
        fechaInicial.setTime(fechaSalida);
        fechaFinal.setTime(fechaActual);
        c.setTimeInMillis(fechaFinal.getTime().getTime() - fechaInicial.getTime().getTime());
        maxBloqueo = (c.get(Calendar.DAY_OF_YEAR) * 24);
        return maxBloqueo;
    }

    public static Integer obtenerAnio(Date date) {
        if (null == date) {
            return 0;
        } else {
            String formato = "yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));
        }
    }

    public static Integer obtenerMes(Date date) {
        if (null == date) {
            return 0;
        } else {
            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));
        }
    }

    public static boolean compararIgualdadFechas(Date date1, Date date2) {
        boolean verifica = false;

        Calendar fecha1 = new GregorianCalendar();
        Calendar fecha2 = new GregorianCalendar();
        fecha1.setTime(date1);
        fecha2.setTime(date2);
        if (fecha1.equals(fecha2)) {
            return true;
        } else {
            return false;
        }

    }

    public static Integer compararFechas(Date date1, Date date2) {
        /*
         Si las fechas representan al mismo punto en el tiempo, el método devolverá un cero.
         Si la fecha que vas a comparar es anterior al argumento date, el método devolverá un valor menor a cero.
         Si la fecha que vas a comparar es posterior al argumento date, el método devolverá un valor mayor a cero.
         Si las fechas son iguales, el método devolverá un cero.
         */

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyy_MM_dd);
            Date fecha1 = sdf.parse(DateToString(FORMAT_yyyy_MM_dd, date1));
            Date fecha2 = sdf.parse(DateToString(FORMAT_yyyy_MM_dd, date2));
            return fecha1.compareTo(fecha2);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int obtenerHoras(Date fecha) {
        Calendar fechaRecibida = Calendar.getInstance();
        fechaRecibida.setTime(fecha);
        int hora = fechaRecibida.get(Calendar.HOUR_OF_DAY);
        return hora;
    }

    public static int obtenerMinutos(Date fecha) {
        Calendar fechaRecibida = Calendar.getInstance();
        fechaRecibida.setTime(fecha);
        int minuto = fechaRecibida.get(Calendar.MINUTE);
        return minuto;
    }

    public static Date sumarRestarMinutosFecha(Date fecha, int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MINUTE, minutos);  // numero de días a añadir, o restar en caso de días<0
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public static Date sumarRestarMinutos(Date fecha, int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MINUTE, minutos);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public static Integer obtenerDiadelaSemana(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * El objetivo de esta funcion es evitar consultar en base las fechas min y max de un grupo de bloqueos.
     * <p> 
     * Por ejemplo cuando se genera un bloqueo 5D lo normal es que se genere en 12D, 15D, 8D (grupo de bloqueos)etc ::: 
     
     * @param fechaMin
     * @param fechaMax
     * @param fechaMinNEW
     * @param fechaMaxNEW
     * @return 
     */
     public static Date[] buscarFechaMenorMayor(Date fechaMin, Date fechaMax,Date fechaMinNEW, Date fechaMaxNEW) {
           if(fechaMin == null && fechaMax == null){
            fechaMin=fechaMinNEW;
            fechaMax=fechaMaxNEW;
            }
            if(fechaMinNEW.getTime() < fechaMin.getTime()){
                    fechaMin=fechaMinNEW;
                }
            if (fechaMaxNEW.getTime() > fechaMax.getTime()){
                     fechaMax=fechaMaxNEW;
            }
           Date[] posFechas = {fechaMin, fechaMax};
           return posFechas;
    }
}
