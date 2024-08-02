package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record RecuperacaoSenhaDto(@NotNull String codigo,
                                  @NotNull Long funcionarioId,
                                  @NotNull String senha) {
}
