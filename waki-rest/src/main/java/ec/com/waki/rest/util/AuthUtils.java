package ec.com.waki.rest.util;


import ec.com.waki.entidades.Usuario;
import ec.com.waki.util.Constantes;
import ec.com.waki.util.DateUtil;
import ec.com.waki.util.Tipo_bundle;
import java.util.Arrays;
import javax.mail.internet.HeaderTokenizer.Token;
import org.apache.http.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leoz3
 */
public final class AuthUtils {
//    private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
//    public static final String AUTH_HEADER_KEY = "Authorization";
    
     
//     public static String getSubject(String authHeader) throws ParseException, JOSEException, java.text.ParseException {
//        return decodeToken(authHeader).getSubject();
//    }

//     
//     public static ReadOnlyJWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException, java.text.ParseException {
//        SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
//        if (signedJWT.verify(new MACVerifier(Constantes.KEY))) {
//            return signedJWT.getJWTClaimsSet();
//        } else {
//            throw new JOSEException("Signature verification failed");
//        }
//    }
    

//     public static Token createToken(String host, Usuario user) throws JOSEException {
//        JWTClaimsSet claim = new JWTClaimsSet();
//        claim.setSubject(Integer.toString(user.getCodigo()));
//        claim.setIssuer(host);
//        claim.setIssueTime(DateUtil.tomarFechaActual());
//        claim.setExpirationTime(DateUtil.sumarRestarMinutosFecha(DateUtil.tomarFechaActual(), 60));// 60 min de expiracion token
//        claim.setCustomClaim("user", user.getNombres() + " " + user.getApellidos());
////        claim.setCustomClaim("roles", Arrays.toString(user.getRolesList().toArray()));
//
//        JWSSigner signer = new MACSigner(TOKEN_SECRET);
//        SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
//        jwt.sign(signer);
//
//        return new Token(0,jwt.serialize());
//    } 
//    private static String getSerializedToken(String authHeader) {
//        return authHeader.split(" ")[1];
//    }
}
