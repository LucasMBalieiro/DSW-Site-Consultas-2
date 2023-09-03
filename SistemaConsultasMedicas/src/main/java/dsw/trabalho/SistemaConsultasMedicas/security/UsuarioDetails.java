package dsw.trabalho.SistemaConsultasMedicas.security;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UsuarioDetails implements UserDetails {

    private UsuarioModel usuario;

    public UsuarioDetails(UsuarioModel u){ this.usuario = u;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getPapel());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

}
