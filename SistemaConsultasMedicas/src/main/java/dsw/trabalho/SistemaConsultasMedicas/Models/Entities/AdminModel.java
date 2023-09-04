package dsw.trabalho.SistemaConsultasMedicas.Models.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ADMINS")
public class AdminModel extends UsuarioModel{
    public AdminModel(){
        super("admin");
    }
}
