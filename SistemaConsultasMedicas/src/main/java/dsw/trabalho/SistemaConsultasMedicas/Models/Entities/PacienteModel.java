package dsw.trabalho.SistemaConsultasMedicas.Models.Entities;

import dsw.trabalho.SistemaConsultasMedicas.Models.Converter.CpfConverter;
import dsw.trabalho.SistemaConsultasMedicas.Models.Converter.EmailConverter;
import dsw.trabalho.SistemaConsultasMedicas.Models.Converter.TelefoneConverter;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

//
@Entity
@Table(name = "TB_PACIENTES")
public class PacienteModel extends UsuarioModel {
    //@Id
    @Column(name = "cpf",unique = true,nullable = false)
    @Convert(converter = CpfConverter.class)
    @NotNull Cpf cpf;


    @Column(name = "telefone",unique = true,nullable = false)
    @Convert(converter = TelefoneConverter.class)
    @NotNull
    private Telefone telefone;

    @Column(name = "sexo",unique = false,nullable = false)
    @NotNull(message = "{NotNull.paciente.sexo}")
    @Size(max=1, min = 1, message = "{Size.paciente.sexo}")
    private String sexo;

    @Column(name = "dataNascimento",unique = false,nullable = false)
    @NotNull
    private String dataNascimento;

    public PacienteModel() {
        super("paciente");
    }

    public Cpf getCpf() {return cpf;}

    public void setCpf(Cpf cpf) {this.cpf = cpf;}

    public Telefone getTelefone() {return telefone;}

    public void setTelefone(Telefone telefone) {this.telefone = telefone;}

    public String getSexo() {return sexo;}

    public void setSexo(String sexo) {this.sexo = sexo;}

    public String getDataNascimento() {return dataNascimento;}

    public void setDataNascimento(String dataNascimento) {this.dataNascimento = dataNascimento;}
}