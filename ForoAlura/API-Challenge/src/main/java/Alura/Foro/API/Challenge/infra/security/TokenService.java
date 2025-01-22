package Alura.Foro.API.Challenge.infra.security;

import Alura.Foro.API.Challenge.Domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    public String generadorToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("Foro Alura")
                    .withSubject(usuario.getCorreo())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubjet(String token) {
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro Alura")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
