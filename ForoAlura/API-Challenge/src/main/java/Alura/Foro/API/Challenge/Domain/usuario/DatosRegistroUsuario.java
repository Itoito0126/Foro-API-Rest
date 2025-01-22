package Alura.Foro.API.Challenge.Domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String correo,
        @NotBlank
        String clave,
        @NotBlank
        String perfil) {
}
