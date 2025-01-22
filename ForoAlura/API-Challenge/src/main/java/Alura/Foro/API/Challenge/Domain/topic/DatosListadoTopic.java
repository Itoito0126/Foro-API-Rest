package Alura.Foro.API.Challenge.Domain.topic;

import java.time.LocalDateTime;

public record DatosListadoTopic(Long id, String titulo, String mensaje, LocalDateTime fechaCracion, Boolean status, Integer autor, String curso, String perfilAutor, String correoAutor) {

    public DatosListadoTopic(Topic topic, String perfilAutor, String correoAutor) {
        this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getFechaCreacion(), topic.getStatus(), topic.getIdAutor(), topic.getCurso(), perfilAutor, correoAutor);
    }
}
