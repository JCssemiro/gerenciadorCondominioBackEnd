package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record CargoCadastroDto(@NotNull String nome, @NotNull Float salarioBase,@NotNull Integer cargaHoraria) {
}
