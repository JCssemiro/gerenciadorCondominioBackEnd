package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record EncomendaCadastroDto(
        @NotNull String descricao,
        @NotNull Float peso,
        @NotNull Float altura,
        @NotNull Float comprimento,
        @NotNull Float largura,
        @NotNull Long moradorId,
        @NotNull Long funcionarioId
) {
}
