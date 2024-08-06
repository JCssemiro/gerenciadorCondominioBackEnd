package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record RecuperacaoSenhaDto(@NotNull String codigo,
                                  @NotNull String funcionarioEmail,
                                  @NotNull String senha) {
}
