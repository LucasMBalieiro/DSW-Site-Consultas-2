package dsw.trabalho.SistemaConsultasMedicas.Service.Spec;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;

import java.util.List;

public interface IPacienteService {

    public void salvar(PacienteModel paciente);

    public void excluir(Cpf cpf);

    public List<PacienteModel> buscarTodos();
}
