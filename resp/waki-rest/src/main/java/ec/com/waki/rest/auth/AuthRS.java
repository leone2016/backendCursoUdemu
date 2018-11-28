/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.rest.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ec.com.waki.DTO.MensajeDTO;
import ec.com.waki.entidades.Usuario;
import ec.com.waki.rest.util.JWTUtil;
import ec.com.waki.servicios.UsuarioService;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.DateUtil;
import ec.com.waki.util.Tipo_bundle;
import java.io.UnsupportedEncodingException;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.http.HttpHeaders;

/**
 *
 * @author leoz3
 */
@Path("auth")
public class AuthRS {
     public static final String NOT_FOUND_MSG = Tipo_bundle.NOT_FOUND_MSG;

     public static final String KEY = Constantes.KEY; 

    @EJB(beanName = "UsuarioServiceImpl")
    private UsuarioService usuarioService;
    
    @Context
    private HttpServletRequest httpServletRequest;
//    
//    @POST
//    @Path("/login/{pass}/{user}")
//    //@Consumes({MediaType.APPLICATION_JSON})
//    //@Produces({MediaType.APPLICATION_JSON})
//    public Response login(@PathParam("user") String user, @PathParam("pass") String pass) throws JOSEException {
//            System.out.println("------ ingresa");
////        Usuario foundUser;
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
////        Usuario regustroUsuario = usuarioService.buscarUsuario(user,pass);
////        if(regustroUsuario!= null){
////            String JWT = Jwts.builder()
////                             .setIssuer("WAKiLabs")
////                             .signWith(SignatureAlgorithm.HS256,TextCodec.BASE64.decode(KEY))
////                             .setSubject(regustroUsuario.getNombres() +" "+ regustroUsuario.getApellidos())
////                             .setIssuedAt(DateUtil.tomarFechaActual())
////                             .setExpiration(DateUtil.sumarRestarMinutosFecha(DateUtil.tomarFechaActual(), 60))
////                             .claim("test", "Test")
////                             .compact();
////             MensajeDTO mensaje= new MensajeDTO();
////             mensaje.setDate(""+DateUtil.tomarFechaActual());
////             mensaje.setMessage("Welcome");
////             mensaje.setStatus(true);
////             mensaje.setToken(JWT);
//////            JsonObject json = Json.createArrayBuilder()
//////                                  .add("message", "Incorrect Credencials ").build();
////            return Response.status(Response.Status.CREATED)
////                                    .entity(gson.toJson(mensaje))
////                                    .type(MediaType.APPLICATION_JSON)
////                                    .build();             
////                             
////        }
////        foundUser = ejbUsuarioFacade.findByEmail(user.getEmail());
////        if (foundUser == null) {
////            return Response.status(Status.UNAUTHORIZED).entity(gson.toJson(NOT_FOUND_MSG)).build();
////        } else if (user.getPassword().equals(foundUser.getPassword())) {
////            final Token token = AuthUtils.createToken(request.getRemoteHost(), foundUser);
////            return Response.ok().entity(gson.toJson(token)).build();
////        }
//        return Response.status(Status.UNAUTHORIZED).entity(gson.toJson(LOGING_ERROR_MSG)).build();
//    }
    
    @POST
    @Path("/loginAPP/{contrasenia}/{usuario}")
    public Response verificarUsuario( @PathParam("contrasenia") String pass, @PathParam("usuario") String user) throws JsonProcessingException, UnsupportedEncodingException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create(); 
         MensajeDTO mensaje= new MensajeDTO();
         mensaje.setDate(""+DateUtil.tomarFechaActual());
         mensaje.setMessage(Tipo_bundle.LOGING_ERROR_MSG);
         mensaje.setStatus(false);
         mensaje.setToken("");
         Usuario registroUsuario = usuarioService.buscarUsuario(user,pass);
          System.out.println("-----"+(registroUsuario.getCodigo()!= null));
           if(registroUsuario.getCodigo()!= null){
//              JWTUtil jwt = new JWTUtil();
//               String JWT = jwt.createToken(user);
//                       Jwts.builder()
//                             .setIssuer("WAKiLabs")
//                             .signWith(SignatureAlgorithm.HS256,TextCodec.BASE64.decode(KEY))
//                             .setSubject(registroUsuario.getNombres() +" "+ registroUsuario.getApellidos())
//                             .setIssuedAt(DateUtil.tomarFechaActual())
//                             .setExpiration(DateUtil.sumarRestarMinutosFecha(DateUtil.tomarFechaActual(), 60))
//                             .claim("test", "Test")
//                             .compact();
             mensaje.setDate(""+DateUtil.tomarFechaActual());
             mensaje.setMessage("Welcome");
             mensaje.setStatus(true);
             mensaje.setToken(generaToken(registroUsuario));
//            JsonObject json = Json.createArrayBuilder()
//                                  .add("message", "Incorrect Credencials ").build();
            return Response.status(Response.Status.CREATED)
                                    .entity(gson.toJson(mensaje))
                                    .type(MediaType.APPLICATION_JSON)
                                    .build();             
                             
        }
       
        return Response.status(Status.UNAUTHORIZED).entity(gson.toJson(mensaje)).build();
    } 
    
    @POST
    @Path("login")
    public Response login(@HeaderParam(javax.ws.rs.core.HttpHeaders.AUTHORIZATION) String tokenEncoded) throws UnsupportedEncodingException, JsonProcessingException{
      MensajeDTO mensaje= new MensajeDTO();
      GsonBuilder builder = new GsonBuilder();
      Gson gson = builder.create(); 
      String jsonInString = Constantes.CAMPO_BLANCO;
      ObjectMapper mapper = new ObjectMapper();
      String usuario = JWTUtil.decodificarTokenLogin(tokenEncoded,0);
      String contrasenia = JWTUtil.decodificarTokenLogin(tokenEncoded,1);
       mensaje.setStatus(false);
       mensaje.setMessage(Tipo_bundle.ERROR_AUTH_LOGIN);
        try {
            
            System.out.println("----- :: "+usuario+" :: "+contrasenia);
            System.out.println("---- ?? "+(!usuario.equals("") && !contrasenia.equals("")));
            if(!usuario.equals("") && !contrasenia.equals("")){
             Usuario registroUsuario = usuarioService.buscarUsuario(usuario,contrasenia);
             mensaje.setMessage(null);
             mensaje.setStatus(true);
             mensaje.setToken(generaToken(registroUsuario));
             jsonInString = mapper.writeValueAsString(mensaje);
                System.out.println("---- test "+jsonInString);
            }
        } catch (Exception e) {
             e.printStackTrace();
             jsonInString = mapper.writeValueAsString(mensaje);
             return Response.ok(jsonInString, MediaType.APPLICATION_JSON).status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
      return Response.status(Status.OK).entity(gson.toJson(mensaje)).build();
    }
    
    
  
    private String generaToken(Usuario usuario ) throws UnsupportedEncodingException{
         JWTUtil jwt = new JWTUtil();
         String JWT = jwt.createToken(usuario);
         return JWT;
    }

}
