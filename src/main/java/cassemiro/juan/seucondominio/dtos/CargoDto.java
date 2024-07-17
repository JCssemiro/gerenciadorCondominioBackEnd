package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Cargo;
import jakarta.validation.constraints.NotNull;

public record CargoDto(
        @NotNull Long id,
        @NotNull String nome,
        @NotNull float salarioBase,
        @NotNull int cargaHoraria
){
}
