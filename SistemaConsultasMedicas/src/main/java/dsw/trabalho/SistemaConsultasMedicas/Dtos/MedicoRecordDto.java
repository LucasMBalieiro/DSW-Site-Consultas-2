package dsw.trabalho.SistemaConsultasMedicas.Dtos;

import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoRecordDto( //todo mudar os tipos de variaveis
                               @NotBlank String nome,
                               @NotNull Crm crm,
                               @NotBlank String especialidade,
                               @NotNull Email email,
                               @NotBlank String senha
)
{

}
