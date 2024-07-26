package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record AcessosCadastroDto(@NotNull Long idMorador,
                                 @NotNull Long idVisitante){
}
