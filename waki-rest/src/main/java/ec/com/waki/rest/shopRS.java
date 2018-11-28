/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import ec.com.waki.DTO.MensajeDTO;
import ec.com.waki.DTO.ArticuloDTO;
import ec.com.waki.DTO.FotosDTO;
import ec.com.waki.entidades.FotoArticulo;
import ec.com.waki.entidades.StockArticulo;
import ec.com.waki.rest.util.UtilRS;
import ec.com.waki.util.Constantes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.com.waki.servicios.ArticuloService;
import ec.com.waki.servicios.FotoArticuloService;
import ec.com.waki.servicios.StockArticuloService;
import ec.com.waki.util.Tipo_bundle;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.container.ContainerRequestContext;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;

/**
 *
 * @author leoz3
 */
@Path("/shop")
public class shopRS {
   @EJB(beanName = "ArticuloServiceImpl")
   private ArticuloService articuloService;
   
   
   @EJB(beanName = "FotoArticuloServiceImpl")
   private FotoArticuloService fotoArticuloService;
    
   @EJB(beanName = "StockArticuloServiceImpl")
   private StockArticuloService stockArticuloService;
   
   @Context
   private HttpServletRequest httpServletRequest;
   
    @GET
    @Path("/articleViewer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataJson(@HeaderParam(HttpHeaders.AUTHORIZATION) String tokenEncoded) throws JsonProcessingException, ParseException, java.text.ParseException{
         UtilRS utilRS = new UtilRS();
          MensajeDTO menajeDTO = new MensajeDTO();
          Gson gson = new Gson();
        String jsonInString = Constantes.CAMPO_BLANCO;
        String metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        try {
            List<ArticuloDTO> listadoArticuloDTO = buscadorArticulo();
            menajeDTO.setDate(""+new Date());
            menajeDTO.setMessage("HOLA MUNDO");
            menajeDTO.setStatus(true);
            return Response.status(Response.Status.OK).entity(gson.toJson(listadoArticuloDTO)).build();
        } catch (Exception e) {
            Logger.getLogger(shopRS.class.getName()).log(Level.SEVERE, "Se ha producido un error en: " + metodo, e);
            e.printStackTrace();
            ObjectMapper mapper = new ObjectMapper();
            menajeDTO.setMessage(e.getCause().toString());
            jsonInString = mapper.writeValueAsString(menajeDTO);
            return Response.status(Response.Status.OK).entity(gson.toJson("HOLA")).build();   
        }
    }
    
    public List<ArticuloDTO> buscadorArticulo(){
            UtilRS utilRS = new UtilRS();
            String metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
            List<ArticuloDTO> retornoListado = new ArrayList<>();
            List<StockArticulo> listStockArticulo = new ArrayList<>();
                try {
                     listStockArticulo = stockArticuloService.buscarArticuloStock(""+Constantes.ARTICULO_ACTIVO);
                     retornoListado = utilRS.cargarArticulo(listStockArticulo);
                     List<FotoArticulo> listadoFoto = fotoArticuloService.buscarFotoArticulo(utilRS.getCodigosArticulos().substring(0, utilRS.getCodigosArticulos().length() - 1));
                     retornoListado = utilRS.cargarFotosArticulo(retornoListado,listadoFoto);
                } catch (Exception e) {
                     Logger.getLogger(shopRS.class.getName()).log(Level.SEVERE, "It has occurred an  error : " + metodo, e);
                     return null;
                }
     return retornoListado;
   }
 
}
