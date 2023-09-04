package dsw.trabalho.SistemaConsultasMedicas.Service.Spec;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPacienteService {

    public void salvar(PacienteModel paciente);

    public void excluir(Cpf cpf);
    public void excluirPorID(UUID id);

    public List<PacienteModel> buscarTodos();

    public Optional<PacienteModel> buscarPorID(UUID id);

    public UUID getIdByEmail(String email);
}
