package dsw.trabalho.SistemaConsultasMedicas.Security;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.UsuarioModel;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //todo como que usa Email aaaaaaaaa
        UsuarioModel usuario = usuario.getUserByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UsuarioDetails(usuario);
    }
}
