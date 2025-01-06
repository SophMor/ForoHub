package com.allura.ForoHub.infra.Seguridad;


import com.allura.ForoHub.users.UsuariosTable;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;
    /*Metodo encargado de generar el token
     * con el metodo hash3
     * @Param{Usuario}*/
    public String generarToken(UsuariosTable usuarios){

        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("Allura")
                    .withSubject(usuarios.getUsername())
                    .withClaim("id",usuarios.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            return token;
            //ISSUAES QUIEN
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo Genera Fecha expiracion durante 2 HORAS
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token){
        if (token==null){throw new RuntimeException("NULL");}
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            verifier  = JWT.require(algorithm)
                    .withIssuer("Allura")
                    .build()
                    .verify(token);

            verifier.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }
        if(verifier.getSubject() == null){
            throw  new RuntimeException("invalid");
        }
        return verifier.getSubject();
    }
}