package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Torre;
import jakarta.validation.constraints.NotNull;

public record TorreDto(@NotNull Long id,
                       @NotNull String nome,
                       @NotNull int andares,
                       @NotNull int qtdUnidades) {

    public TorreDto(Torre torre){
        this(torre.getId(),torre.getNome(),torre.getAndares(),torre.getQtdUnidades());
    }
}
