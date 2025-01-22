package Alura.Foro.API.Challenge.Controller;

import Alura.Foro.API.Challenge.Domain.topic.DatosListadoTopic;
import Alura.Foro.API.Challenge.Domain.usuario.DatosListadoUsuarios;
import Alura.Foro.API.Challenge.Domain.usuario.DatosRegistroUsuario;
import Alura.Foro.API.Challenge.Domain.usuario.Usuario;
import Alura.Foro.API.Challenge.Domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){
        boolean existe = usuarioRepository.existsByPerfil(datosRegistroUsuario.perfil());
        if (existe) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ya existe un usuario con ese nombre de perfil"
            );
        }
        usuarioRepository.save(new Usuario(datosRegistroUsuario));
    }

    @GetMapping({"", "/{id}"})
    public PageImpl<DatosListadoUsuarios> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion, @PathVariable(required = false) Long id){
        if (id != null) {
            return usuarioRepository.findById(id).map(usuario -> new PageImpl<>(List.of(new DatosListadoUsuarios(usuario)), paginacion, 1))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic no encontrado"));
        }
        return (PageImpl<DatosListadoUsuarios>) usuarioRepository.findAll(paginacion).map(DatosListadoUsuarios::new);
    }
}
