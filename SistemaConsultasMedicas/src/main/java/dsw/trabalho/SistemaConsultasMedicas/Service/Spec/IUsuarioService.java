package dsw.trabalho.SistemaConsultasMedicas.Service.Spec;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.security.UsuarioDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUsuarioService {
    public UsuarioDetails findByEmail(String email);
}
