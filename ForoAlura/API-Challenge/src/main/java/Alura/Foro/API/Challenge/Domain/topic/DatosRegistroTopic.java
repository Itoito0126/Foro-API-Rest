package Alura.Foro.API.Challenge.Domain.topic;

//import Alura.Foro.API.Challenge.Domain.usuario.DatosUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopic(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotNull
        Boolean status,
        @NotNull
        int idAutor,
        @NotBlank
        String curso){
}
