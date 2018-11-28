/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.filter;

//import com.nimbusds.jwt.JWTClaimsSet;

import com.google.gson.Gson;
import ec.com.waki.DTO.MensajeDTO;
import ec.com.waki.rest.util.JWTUtil;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.DateUtil;
import ec.com.waki.util.Tipo_bundle;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;



/**
 *
 * @author leoz3
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class CrossOriginResourceSharingFilter implements ContainerRequestFilter, ContainerResponseFilter{
 //https://www.youtube.com/watch?v=dENRW9fhuDs
// https://bitbucket.org/senacbi/portafolio-backend/src/master/pom.xml 
    //ContainerRequestFilter
  
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        response.getHeaders().putSingle("Access-Control-Max-Age", "3600");
      
    }
    public String decodificarToken(String tokenEncoded) {
        String token = Constantes.CAMPO_BLANCO;
        try {
            if (tokenEncoded != null) {
                String[] tokenCabecera = tokenEncoded.split(" ");
                String tokenRecovered = tokenCabecera[1];
                byte[] decoded = Base64.decodeBase64(tokenRecovered.getBytes(StandardCharsets.UTF_8));
                String tokenDecoded = new String(decoded);
                String[] tokenArray = tokenDecoded.split(":");
                token = tokenArray[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    /**
     *Check if the Authorization header is valid
     *It must not be null and must be prefixed with "Bearer" plus a whitespace
     *The authentication scheme comparison must be case-insensitive
     * @param authorizationHeader
     * @return 
     */
     private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(Constantes.AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }
     /**
      * Abort the filter chain with a 401 status code response
      * The WWW-Authenticate header is sent along with the response
      * @param requestContext 
      */
       private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, 
                                Constantes.AUTHENTICATION_SCHEME + " realm=\"" + Constantes.REALM + "\"")
                        .build());
    }
    /**
     * En el header se puede enviar de esta manera
     * Authorization: "Bearer JHFoaoFQjhLIioiohg21245"
     * posicion [0] = Bearer
     * posicion [1] = JHFoaoFQjhLIioiohg21245
     * @param requestContext
     * @throws IOException 
     */
    private int intentos=0;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        SecurityContext originalContext = requestContext.getSecurityContext();
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String authorizationHeader =
        requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String url  = requestContext.getUriInfo().getAbsolutePath().toString();
        Gson gson = new Gson();
        MensajeDTO mensaje= new MensajeDTO();
        mensaje.setStatus(false);
        mensaje.setDate(""+DateUtil.tomarFechaActual());
        mensaje.setToken(null);
//        url.contains("/auth") || url.contains("/shop")
        if(true){     //no valida, esta intentalo ingresar credenciales
            return;
        }
        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }
            String undecodedToken = Constantes.CAMPO_BLANCO;
            String email =Constantes.CAMPO_BLANCO;
            Long tiempo = DateUtil.tomarFechaActual().getTime() ;
            try {
             undecodedToken = JWTUtil.decodificarToken(authHeader);
             String[] todo  = undecodedToken.split("[\\\"||\\\"]");
             String[] time = todo[10].split("[\\:||\\,]");
             tiempo = new Long(time[1]);
             email= todo[13];
            } catch (Exception e) {
             mensaje.setMessage(Tipo_bundle.TOKEN_DECODE);
             requestContext.abortWith(Response
                                    .status(Response.Status.UNAUTHORIZED)
                                    .entity(gson.toJson(mensaje))
                                    .type(MediaType.APPLICATION_JSON)
                                    .build());
            }
//            if(tiempo < DateUtil.tomarFechaActual().getTime()){
//                System.out.println("-------------token no valido");
//            }
          if (!email.equals(Constantes.CORREO_WAKiAPP)){
             mensaje.setMessage(Tipo_bundle.TOKEN_NO_VALID);
             requestContext.abortWith(Response
                                    .status(Response.Status.UNAUTHORIZED)
                                    .entity(gson.toJson(mensaje))
                                    .type(MediaType.APPLICATION_JSON)
                                    .build());
          }
        
        
    }
    
   
//    public static class Authorizer implements  SecurityContext {
//
////        List<Roles> roles;
//        String userName;
//        boolean isSecure;//esto quiere decir si se hace por https(true) o http(false);
//
//        public Authorizer(String userName, boolean isSecure) {
//            this.userName = userName;
//            this.isSecure = isSecure;
//        }
//        
//        @Override
//        public Principal getUserPrincipal() {
//            return new User(userName);
//        }
//
//        @Override
//        public boolean isUserInRole(String role) {
//            return true; //To change body of generated methods, choose Tools | Templates.
//        // return roles.contains(new Role(role)); valida si existe el role con true o false //
//        }
//
//        @Override
//        public boolean isSecure() {
////            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//               return isSecure;
//        }
//
//        @Override
//        public String getAuthenticationScheme() {
////            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//               return "JWT";
//        }
//    
//    }
//    //el usuario logeado
//    public static class User implements Principal{
//        String name;
//
//        public User(String name) {
//            this.name = name;
//        }
//        
//        @Override
//        public String getName() {
//            return name;
//        }
//    
//    }

    
}

