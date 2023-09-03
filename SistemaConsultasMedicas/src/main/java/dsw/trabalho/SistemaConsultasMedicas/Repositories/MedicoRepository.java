package dsw.trabalho.SistemaConsultasMedicas.Repositories;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, UUID> {


    @Query(value = "SELECT * FROM TB_MEDICOS medicos WHERE medicos.nome = :nome", nativeQuery = true)
    public List<MedicoModel> findByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM TB_MEDICOS medicos WHERE medicos.crm = :crm", nativeQuery = true)
    public MedicoModel findByCrm(@Param("crm") Crm crm);


    void deleteByCrm(Crm id);
}
