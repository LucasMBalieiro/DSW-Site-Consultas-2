package dsw.trabalho.SistemaConsultasMedicas.Repositories;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.UsuarioModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.Security.UsuarioDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    @Query(value = "SELECT u FROM TB_USUARIOS usuarios WHERE usuarios.email = :email", nativeQuery = true)
    public UsuarioModel getUserByEmail(@Param("email")Email email);
}
