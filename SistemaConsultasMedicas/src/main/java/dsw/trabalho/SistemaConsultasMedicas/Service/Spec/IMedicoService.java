package dsw.trabalho.SistemaConsultasMedicas.Service.Spec;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMedicoService {

    public List<String> buscarEspecialidades();

    public MedicoModel buscarMedicoPorCRM(String crm);

    public Optional<MedicoModel> buscarMedicoPorID(UUID id);

    public List<MedicoModel> buscarPorEspecialidade(String especialidade);

    public List<MedicoModel> buscarTodos();

    public void salvar(MedicoModel medico);

    public void excluirPorCrm(Crm id);

    public void excluirPorID(UUID id);

    public UUID getIdByEmail(String email);
    
    public List<String> buscarTodasEspecialidades();
}
