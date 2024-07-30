package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.models.Morador;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record EncomendaEdicaoDto(@NotNull Long id,
                                 @NotNull String descricao,
                                 @NotNull Float peso,
                                 @NotNull Float altura,
                                 @NotNull Float comprimento,
                                 @NotNull Float largura,
                                 @NotNull Instant dataEntrega,
                                 @NotNull Boolean entregue,
                                 @NotNull Long moradorId,
                                 @NotNull Long funcionarioId) {
}
