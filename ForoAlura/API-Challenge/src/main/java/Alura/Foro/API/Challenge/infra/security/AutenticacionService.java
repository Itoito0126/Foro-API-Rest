package Alura.Foro.API.Challenge.infra.security;

import Alura.Foro.API.Challenge.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//El service le dice a Spring que escanee esta clase porque es un servicio de la aplicación
@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuariosRepository.findByCorreo(username);
    }
}
