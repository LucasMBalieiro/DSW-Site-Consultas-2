package dsw.trabalho.SistemaConsultasMedicas.Repositories;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.AdminModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.UsuarioModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, UUID> {
    @Query(value = "SELECT * FROM TB_ADMINS admins WHERE admins.email = :email", nativeQuery = true)
    public AdminModel findByEmail(@Param("email")String email);
}
