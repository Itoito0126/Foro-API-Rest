package Alura.Foro.API.Challenge.Controller;

import Alura.Foro.API.Challenge.Domain.topic.*;
import Alura.Foro.API.Challenge.Domain.usuario.Usuario;
import Alura.Foro.API.Challenge.Domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public void registrarTopic(@RequestBody @Valid DatosRegistroTopic datosRegistroTopic){
        boolean existe = topicRepository.existsByTituloAndMensaje(datosRegistroTopic.titulo(), datosRegistroTopic.mensaje());
        if (existe) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ya existe un tópico con el mismo título y mensaje"
            );
        }

        Usuario autor = usuarioRepository.findById((long) datosRegistroTopic.idAutor())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El autor con el ID especificado no existe"
                ));

        topicRepository.save(new Topic(datosRegistroTopic));
    }

    @GetMapping({"", "/{id}"})
    public Page<DatosListadoTopic> listadoTopic(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion, @PathVariable(required = false) Long id){
        if (id != null) {
            return topicRepository.findById(id).map(topic -> { Usuario autor = usuarioRepository.findById((long) topic.getIdAutor())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado"));

                        return new PageImpl<>(
                                List.of(new DatosListadoTopic(topic, autor.getPerfil(), autor.getCorreo())), paginacion, 1);})
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        }
        return topicRepository.findByStatusTrue(paginacion).map(topic -> {Usuario autor = usuarioRepository.findById((long) topic.getIdAutor())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado"));

                    return new DatosListadoTopic(topic, autor.getPerfil(), autor.getCorreo());});
    }

    @PutMapping("/{id}")
    @Transactional
    public void actualizarTopic(@RequestBody DatosActualizarTopic datosActualizarTopic, @PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.actualizarDatos(datosActualizarTopic);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.desactivarMedico();
    }
}
