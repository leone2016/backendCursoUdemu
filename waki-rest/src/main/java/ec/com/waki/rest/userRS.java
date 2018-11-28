/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ec.com.waki.DTO.MensajeDTO;
import ec.com.waki.DTO.UsuarioDTO;
import ec.com.waki.entidades.Usuario;
import ec.com.waki.servicios.CommonService;
import ec.com.waki.servicios.UsuarioService;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.DateUtil;
import ec.com.waki.util.Tipo_bundle;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author leoz3
 */
@Path("/user")
public class userRS {
    
    @EJB(beanName = "CommonServiceImpl")
   private CommonService commonService;
   
     @EJB(beanName = "UsuarioServiceImpl")
    private UsuarioService usuarioService;
    
    @Context
    private HttpServletRequest httpServletRequest;
    
    @POST
    @Path("sendEmail")
    @Produces("application/json")
    public Response sendEmail(String params) {
            
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create(); 
        MensajeDTO mensaje= new MensajeDTO();
        mensaje.setDate(""+DateUtil.tomarFechaActual());
        mensaje.setMessage(Tipo_bundle.MENSAJE_NUEVO_CLIENTE);
        mensaje.setStatus(false);
        mensaje.setToken("");
//         com.ec.gbc.entidades.Usuario usuarioNuevo = gson.fromJson(params, com.ec.gbc.entidades.Usuario.class);
        UsuarioDTO usuarioDTO = gson.fromJson(params, UsuarioDTO.class);
        
         
        return Response.status(Response.Status.CREATED).entity(gson.toJson(mensaje)).build();
    }1
    
    public boolean verificaMaio(UsuarioDTO usuarioDTO ){
    
      return usuarioService.buscarUsuario(email, contrasenia)
    }
//    
//      @POST
//    @Path("/newUser/{params}")
//    public Response verificarUsuario( @PathParam("contrasenia") String pass, @PathParam("usuario") String user) throws JsonProcessingException, UnsupportedEncodingException {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create(); 
//         MensajeDTO mensaje= new MensajeDTO();
//         mensaje.setDate(""+DateUtil.tomarFechaActual());
//         mensaje.setMessage(Tipo_bundle.LOGING_ERROR_MSG);
//         mensaje.setStatus(false);
//         mensaje.setToken("");
//         Usuario registroUsuario = usuarioService.buscarUsuario(user,pass);
//          System.out.println("-----"+(registroUsuario.getCodigo()!= null));
//           if(registroUsuario.getCodigo()!= null){
////              JWTUtil jwt = new JWTUtil();
////               String JWT = jwt.createToken(user);
////                       Jwts.builder()
////                             .setIssuer("WAKiLabs")
////                             .signWith(SignatureAlgorithm.HS256,TextCodec.BASE64.decode(KEY))
////                             .setSubject(registroUsuario.getNombres() +" "+ registroUsuario.getApellidos())
////                             .setIssuedAt(DateUtil.tomarFechaActual())
////                             .setExpiration(DateUtil.sumarRestarMinutosFecha(DateUtil.tomarFechaActual(), 60))
////                             .claim("test", "Test")
////                             .compact();
//             mensaje.setDate(""+DateUtil.tomarFechaActual());
//             mensaje.setMessage("Welcome");
//             mensaje.setStatus(true);
////             mensaje.setToken(generaToken(registroUsuario));
////            JsonObject json = Json.createArrayBuilder()
////                                  .add("message", "Incorrect Credencials ").build();
//            return Response.status(Response.Status.CREATED)
//                                    .entity(gson.toJson(mensaje))
//                                    .type(MediaType.APPLICATION_JSON)
//                                    .build();             
//                             
//        }
//       
//        return Response.status(Response.Status.UNAUTHORIZED).entity(gson.toJson(mensaje)).build();
//    } 
}
