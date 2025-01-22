package Alura.Foro.API.Challenge.Domain.usuario;

public record DatosListadoUsuarios(Long id, String nombre, String correo, String perfil) {

    public DatosListadoUsuarios(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getPerfil());
    }
}
