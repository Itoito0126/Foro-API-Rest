package Alura.Foro.API.Challenge.Controller;

import Alura.Foro.API.Challenge.Domain.usuario.DatosAutenticacionUsuario;
import Alura.Foro.API.Challenge.Domain.usuario.Usuario;
import Alura.Foro.API.Challenge.infra.security.DatosJWTToken;
import Alura.Foro.API.Challenge.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correo(), datosAutenticacionUsuario.clave());
        authenticationManager.authenticate(authToken);
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generadorToken((Usuario) usuarioAutenticado.getPrincipal()) ;
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
