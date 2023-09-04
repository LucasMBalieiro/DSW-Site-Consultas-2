package dsw.trabalho.SistemaConsultasMedicas.Repositories;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, UUID> {

    @Query(value = "SELECT * FROM TB_PACIENTES pacientes WHERE pacientes.cpf = :cpf", nativeQuery = true)
    public PacienteModel findByCpf(@Param("cpf") Cpf cpf);

    @Query(value = "SELECT userID FROM TB_PACIENTES pacientes WHERE pacientes.email = :email", nativeQuery = true)
    public UUID findIdByEmail(@Param("email") Email email);

    void deleteByCpf(Cpf cpf);
}
