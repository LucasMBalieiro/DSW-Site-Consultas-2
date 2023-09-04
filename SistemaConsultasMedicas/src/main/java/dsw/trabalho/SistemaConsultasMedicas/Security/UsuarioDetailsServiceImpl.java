package dsw.trabalho.SistemaConsultasMedicas.Security;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.AdminModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.UsuarioModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.AdminRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.MedicoRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.UsuarioRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UsuarioModel usuario0 = new UsuarioModel();

        MedicoModel medico0 = medicoRepository.findbyEmail(username);

        PacienteModel paciente0 = pacienteRepository.findByEmail(username);

        AdminModel admin0 = adminRepository.findByEmail(username);

        if(medico0 != null){
            System.out.println("Login como médico");
            usuario0.setEmail(medico0.getEmail());
            usuario0.setSenha(medico0.getSenha());
            usuario0.setNome(medico0.getNome());
            usuario0.setPapel("medico");
        }
        else if(paciente0 != null){
            System.out.println("Login como paciente");
            usuario0.setEmail(paciente0.getEmail());
            usuario0.setSenha(paciente0.getSenha());
            usuario0.setNome(paciente0.getNome());
            usuario0.setPapel("paciente");
        }
        else if(admin0 != null){
            System.out.println("Login como admin");
            usuario0.setEmail(admin0.getEmail());
            usuario0.setSenha(admin0.getSenha());
            usuario0.setNome(admin0.getNome());
            usuario0.setPapel("admin");
        }
        else {
            System.out.println("Usuário não encontrado");
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UsuarioDetails(usuario0);
    }
}
