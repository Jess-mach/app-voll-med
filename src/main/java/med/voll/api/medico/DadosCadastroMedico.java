package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
         @NotBlank String nome,
         @NotBlank String email,
         @NotBlank String crm,
         @Valid @NotNull Especialidade especialidade,
         @Valid @NotNull DadosEndereco endereco) {
}
