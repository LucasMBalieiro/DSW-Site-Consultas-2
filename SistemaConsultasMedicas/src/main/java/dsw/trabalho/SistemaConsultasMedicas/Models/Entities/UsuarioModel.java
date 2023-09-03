package dsw.trabalho.SistemaConsultasMedicas.Models.Entities;

import dsw.trabalho.SistemaConsultasMedicas.Models.Converter.EmailConverter;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioModel extends RepresentationModel<UsuarioModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userID;

    @Column(name = "email",unique = true,nullable = false)
    @Convert(converter = EmailConverter.class)
    @NotNull
    private Email email;

    @Column(name = "senha",unique = false,nullable = false)
    @NotNull
    private String senha;

    @Column(name = "nome",unique = false,nullable = false)
    @NotNull
    private String nome;

    @Column(name = "papel",unique = false,nullable = true)
    @NotNull
    private String papel;

    public UsuarioModel(String papel) {
        this.papel = papel;
    }

    public UsuarioModel() {

    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
