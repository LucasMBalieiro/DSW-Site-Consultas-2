package service.impl;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.ConsultaRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.MedicoRepository;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
// pois a depender das regras aplicadas aqui, pode ocorrer inconsistências (sobreescrita)
public class MedicoService implements IMedicoService {

    @Autowired
    MedicoRepository dao;

    @Autowired
    ConsultaRepository consultaDao;

    public void salvar(MedicoModel medico) {
        dao.save(medico);
    }

    public void excluir(Crm id) {
        dao.deleteByCrm(id);
    }

    @Transactional(readOnly = true)
    public List<String> buscarEspecialidades() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<MedicoModel> buscarMedicoPorCRM(Crm crm) {
        return consultaDao.findAllById(dao.findByCrm(crm));
    }

    @Transactional(readOnly = true)
    public List<MedicoModel> buscarPorEspecialidade(String especialidade) {
        return dao.findAllById(especialidade);
    }

    @Transactional(readOnly = true)
    public MedicoModel buscarPorCRM(Crm crm) {
        return dao.findByCrm(crm);
    }
    @Transactional(readOnly = true)
    public List<MedicoModel> buscarTodos() {
        return dao.findAll();
    }
}