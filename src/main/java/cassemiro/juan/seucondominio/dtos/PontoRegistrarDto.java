package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record PontoRegistrarDto(@NotNull Long funcionarioId) {
}
