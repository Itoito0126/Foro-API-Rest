package Alura.Foro.API.Challenge.Domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private int idAutor;
    private String curso;
    //    private String respuestas;

    public Topic(){}

    public Topic(DatosRegistroTopic datosRegistroTopic) {
        this.titulo = datosRegistroTopic.titulo();
        this.mensaje = datosRegistroTopic.mensaje();
        this.fechaCreacion = datosRegistroTopic.fechaCreacion();
        this.status = datosRegistroTopic.status();
        this.idAutor = datosRegistroTopic.idAutor();
        this.curso = datosRegistroTopic.curso();
    }

    public void actualizarDatos(DatosActualizarTopic datosActualizarTopic) {
        if (datosActualizarTopic.titulo() != null){
            this.titulo = datosActualizarTopic.titulo();
        }

        if (datosActualizarTopic.mensaje() != null){
            this.mensaje = datosActualizarTopic.mensaje();
        }
    }

    public void desactivarMedico() {
        this.status = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public String getCurso() {
        return curso;
    }

}
