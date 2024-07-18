package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record TorreCadastroDto(@NotNull String nome,
                               @NotNull int andares,
                               @NotNull int qtdUnidades){
}
