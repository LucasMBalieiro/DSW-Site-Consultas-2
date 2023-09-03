package dsw.trabalho.SistemaConsultasMedicas.Service.Spec;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IConsultaService {

    public void salvar(ConsultaModel consulta);

    public void excluirPorID(UUID id);

    public List<ConsultaModel> buscarTodos();

    public Optional<ConsultaModel> buscarPorID(UUID id);

    public List<ConsultaModel> buscarPorMedico(UUID idMedico);

    public List<ConsultaModel> buscarPorPaciente(UUID idPaciente);
}
