package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record SolicitarRecuperacaoSenhaDto(@NotNull Long funcionarioId) {
}
