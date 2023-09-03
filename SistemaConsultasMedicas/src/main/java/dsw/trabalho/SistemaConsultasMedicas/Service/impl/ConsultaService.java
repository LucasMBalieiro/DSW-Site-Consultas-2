package dsw.trabalho.SistemaConsultasMedicas.Service.impl;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.ConsultaRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IConsultaService;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    public void salvar(ConsultaModel consulta){
        consultaRepository.save(consulta);
    }

    public void excluirPorID(UUID id){
        consultaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ConsultaModel> buscarTodos(){
        return consultaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ConsultaModel> buscarPorID(UUID id){
        return consultaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ConsultaModel> buscarPorMedico(UUID idMedico){
        return consultaRepository.findByMedico(idMedico);
    }

    @Transactional(readOnly = true)
    public List<ConsultaModel> buscarPorPaciente(UUID idPaciente){
        return consultaRepository.findByMedico(idPaciente);
    }
}
