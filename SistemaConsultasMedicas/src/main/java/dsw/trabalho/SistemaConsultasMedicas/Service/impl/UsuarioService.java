package dsw.trabalho.SistemaConsultasMedicas.Service.impl;

import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.UsuarioRepository;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IUsuarioService;
import dsw.trabalho.SistemaConsultasMedicas.security.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {

    @Autowired
    UsuarioRepository usuario;
    public UsuarioDetails findByEmail(String email){
        return new UsuarioDetails(usuario.findByEmail(new Email(email)));
    }
}
