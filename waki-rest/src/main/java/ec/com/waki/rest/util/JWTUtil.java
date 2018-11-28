/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.waki.rest.util;

import ec.com.waki.entidades.Usuario;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.DateUtil;
import ec.com.waki.util.Tipo_bundle;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;




/**
 *
 * @author Fernando Merino
 */
public final class  JWTUtil {

    Key key = MacProvider.generateKey();

    String compactJws = "";

    public String createToken(Usuario user) throws UnsupportedEncodingException {
        Date fechaCaduca = new Date(DateUtil.tomarFechaActual().getTime() + (1000 * 3600 * 48));
        System.out.println("-*-*-*-*>>>" + fechaCaduca);
        compactJws = Jwts.builder().setSubject(user.getMail()).setExpiration(fechaCaduca).claim(user.getNombres()+" "+user.getApellidos(), user.getMail())
                .signWith(SignatureAlgorithm.HS256, Constantes.KEY.getBytes("UTF-8")).compact();
        return compactJws;
    }

     public static boolean verifyToken(String token) throws UnsupportedEncodingException {
        try {
            Jws<Claims> claims = Jwts.parser()
                                .setSigningKey(Constantes.KEY.getBytes("UTF-8"))
                                .parseClaimsJws(token);
            String scope = (String) claims.getBody().get("leo");
            System.out.println("--------- QUE VALIDA : "+scope);
            if (scope.equalsIgnoreCase("leomedinae.sc@gmail.com")) {
                return true;
            }
            return false;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            
            return false;
        } catch (SignatureException se) {
            se.printStackTrace();
            System.out.println("Signature not valid");
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
     
    public static  String decodificarToken(String tokenEncoded) {
        String token = Constantes.CAMPO_BLANCO;
        System.out.println("--------- LLEGA TOKEN :: "+tokenEncoded);
        try {
            if (tokenEncoded != null) {
                String[] tokenCabecera = tokenEncoded.split(" ");
                String tokenRecovered = tokenCabecera[1];
                byte[] decoded = Base64.decodeBase64(tokenRecovered.getBytes(StandardCharsets.UTF_8));
               
                String tokenDecoded = new String(decoded);
                return tokenDecoded;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("------------ ERROR "+tokenEncoded);
            return Tipo_bundle.TOKEN_NO_VALID;
        }
        return token;
    }
    
     public static  String decodificarTokenLogin(String tokenEncoded,int parameter) {
        String token = Constantes.CAMPO_BLANCO;
        System.out.println("--------- LLEGA TOKEN :: "+tokenEncoded);
        try {
            if (tokenEncoded != null) {
                String[] tokenCabecera = tokenEncoded.split(" ");
                String tokenRecovered = tokenCabecera[1];
                byte[] decoded = Base64.decodeBase64(tokenRecovered.getBytes(StandardCharsets.UTF_8));
                String tokenDecoded = new String(decoded);
                if(tokenDecoded.split(":")!= null && tokenDecoded.split(":").length==2){
                    String[] tokenArray = tokenDecoded.split(":");
                    return tokenArray[parameter];
                }
                return token;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("------------ ERROR "+tokenEncoded);
            return Tipo_bundle.TOKEN_NO_VALID;
        }
        return token;
    }

}
