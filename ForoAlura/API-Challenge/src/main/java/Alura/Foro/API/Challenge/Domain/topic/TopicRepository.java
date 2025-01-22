package Alura.Foro.API.Challenge.Domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByStatusTrue(Pageable paginacion);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
