package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record TorreDto(@NotNull Long id,
                       @NotNull String nome,
                       @NotNull int andares,
                       @NotNull int qtdUnidade) {
}
