package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Funcionario;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record PontoDto(@NotNull Long id,
                       @NotNull Instant horarioEntrada,
                       @NotNull Instant horarioSaida,
                       @NotNull Instant cargaHoraria,
                       @NotNull Funcionario funcionario) {

}
