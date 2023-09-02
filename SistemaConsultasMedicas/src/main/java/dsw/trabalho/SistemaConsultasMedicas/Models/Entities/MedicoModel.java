package dsw.trabalho.SistemaConsultasMedicas.Models.Entities;


import dsw.trabalho.SistemaConsultasMedicas.Models.Converter.CrmConverter;
import dsw.trabalho.SistemaConsultasMedicas.Models.Converter.EmailConverter;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_MEDICOS")
public class MedicoModel extends UsuarioModel {

    @Id
    @Column(name = "crm",unique = true,nullable = false)
    @Convert(converter = CrmConverter.class)
    @NotNull
    private Crm crm;

    @Column(name = "especialidade",unique = false,nullable = false)
    @NotNull
    private String especialidade;

    //@OneToMany(mappedBy = "medico")
    //private List<ConsultaModel> consultas;

    public MedicoModel() {
        super("medico");
    }

    public Crm getCrm() {return crm;}

    public void setCrm(Crm crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }



}
