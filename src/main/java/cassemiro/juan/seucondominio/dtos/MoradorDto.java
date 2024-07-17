package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Unidade;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MoradorDto(@NotNull Long id,
                         @NotNull String nome,
                         @NotNull String cpf,
                         @NotNull Date dataNascimento,
                         @NotNull String sexo,
                         @NotNull Unidade unidade
                         ) {
}
