/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ec.com.waki.DTO.MensajeDTO;
import ec.com.waki.entidades.Usuario;
import ec.com.waki.servicios.CommonService;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.Tipo_bundle;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author leoz3
 */
@Path("/user")
public class userRS {
    
    @EJB(beanName = "CommonServiceImpl")
   private CommonService commonService;
    @POST
    @Path("/newUser/{nombre}/{mail}/{contrasenia}")
    public Response nuevoUsuario( @PathParam("nombre") String nombre, @PathParam("mail") String mail,@PathParam("contrasenia") String contrasenia) {
         GsonBuilder builder = new GsonBuilder();
         Gson gson = builder.create(); 
         MensajeDTO mensajeDTO = new MensajeDTO();
         mensajeDTO.setDate(""+new Date());
         mensajeDTO.setMessage(Tipo_bundle.MENSAJE_REGISTRO_ERROR);
         mensajeDTO.setStatus(false);
         try {
            Usuario registroUsuario = new Usuario();
            registroUsuario.setEstado(Constantes.ESTADO_ACTIVO);
            registroUsuario.setNombres(nombre);
            registroUsuario.setMail(mail);
            registroUsuario.setContrasenia(contrasenia);
            commonService.crearRegistro(registroUsuario);
            mensajeDTO.setMessage(Tipo_bundle.MENSAJE_REGISTRO_OK);
            mensajeDTO.setStatus(true);
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(mensajeDTO)).build();
        }
        return Response.status(Response.Status.CREATED).entity(gson.toJson(mensajeDTO)).build();
    }
}
