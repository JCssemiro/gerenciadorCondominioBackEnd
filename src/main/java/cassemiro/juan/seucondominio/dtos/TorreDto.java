package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Torre;
import cassemiro.juan.seucondominio.models.Unidade;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TorreDto(@NotNull Long id,
                       @NotNull String nome,
                       @NotNull int andares,
                       @NotNull int qtdUnidades,

                       @NotNull List<Unidade> unidades) {

    public TorreDto(Torre torre){
        this(torre.getId(),torre.getNome(),torre.getAndares(),torre.getQtdUnidades(),torre.getUnidades());
    }
}
