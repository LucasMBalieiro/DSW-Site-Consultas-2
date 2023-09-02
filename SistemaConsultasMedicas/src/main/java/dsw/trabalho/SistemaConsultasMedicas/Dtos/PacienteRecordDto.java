package dsw.trabalho.SistemaConsultasMedicas.Dtos;

import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteRecordDto(@NotBlank String nome,
                                @NotNull Cpf cpf,
                                @NotNull Telefone telefone,
                                @NotBlank String sexo,
                                @NotBlank String dataNascimento,
                                @NotNull Email email,
                                @NotBlank String senha)
{
}
