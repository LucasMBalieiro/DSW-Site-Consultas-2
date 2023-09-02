package dsw.trabalho.SistemaConsultasMedicas.Service.impl;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IPacienteService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class PacienteService implements IPacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public void salvar(PacienteModel paciente){
        pacienteRepository.save(paciente);
    }

    public void excluir(Cpf cpf){
        pacienteRepository.deleteByCpf(cpf);
    }

    @Transactional(readOnly = true)
    public List<PacienteModel> buscarTodos(){
        return pacienteRepository.findAll();
    }
}
