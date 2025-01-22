package Alura.Foro.API.Challenge.Domain.usuario;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByPerfil(@NotBlank String perfil);

    UserDetails findByCorreo(String username);
}
