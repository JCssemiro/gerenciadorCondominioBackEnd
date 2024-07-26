package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Torre;
import cassemiro.juan.seucondominio.models.Unidade;
import jakarta.validation.constraints.NotNull;

public record UnidadeDto(@NotNull Long id,
                         @NotNull Integer numero,
                         @NotNull Long idTorre) {

    public UnidadeDto(Unidade unidade){
        this(unidade.getId(),unidade.getNumero(),unidade.getTorre().getId());
    }

}
