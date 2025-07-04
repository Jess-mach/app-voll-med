package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
         @NotBlank(message = "Nome é obrigatório")
         String nome,

         @NotBlank (message = "Email é obrigatório")
         @Email (message = "Email inválido")
         String email,

         @NotBlank(message = "Telefone é obrigatório")
         String telefone,

         @NotBlank (message = "CRM é obrigatório")
         @Pattern(regexp = "\\d{4,6}", message = "CRM inválido. Deve conter entre 4 e 6 dígitos")
         String crm,

         @Valid
         @NotNull (message = "Especialidade é obrigatória")
         Especialidade especialidade,

         @Valid
         @NotNull (message = "Endereço é obrigatório")
         DadosEndereco endereco ) {
}
