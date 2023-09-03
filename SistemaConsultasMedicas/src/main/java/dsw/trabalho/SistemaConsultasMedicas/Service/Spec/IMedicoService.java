package dsw.trabalho.SistemaConsultasMedicas.Service.Spec;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMedicoService {
    public void salvar(MedicoModel medico);

    public void excluir(Crm id);

    public void excluirPorID(UUID id);

    @Transactional(readOnly = true)
    public List<String> buscarEspecialidades();

    @Transactional(readOnly = true)
    public MedicoModel buscarMedicoPorCRM(Crm crm);

    @Transactional(readOnly = true)
    public Optional<MedicoModel> buscarMedicoPorID(UUID id);

    @Transactional(readOnly = true)
    public List<MedicoModel> buscarPorEspecialidade(String especialidade);

    @Transactional(readOnly = true)
    public List<MedicoModel> buscarTodos();
}
