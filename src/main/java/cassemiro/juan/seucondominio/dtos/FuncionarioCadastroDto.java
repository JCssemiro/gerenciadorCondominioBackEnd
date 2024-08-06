package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Cargo;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record FuncionarioCadastroDto(
                                     @NotNull String nome,
                                     @NotNull String cpf,
                                     @NotNull Date dataNascimento,
                                     @NotNull String sexo,
                                     @NotNull String telefone,
                                     @NotNull  String email,
                                     @NotNull float salario,
                                     @NotNull Long cargoId) {
}
