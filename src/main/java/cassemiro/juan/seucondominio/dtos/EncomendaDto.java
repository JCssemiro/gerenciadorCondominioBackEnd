package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.models.Morador;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record EncomendaDto(@NotNull Long id,
                           @NotNull String descricao,
                           @NotNull float peso,
                           @NotNull float altura,
                           @NotNull float comprimento,
                           @NotNull float largura,
                           @NotNull Instant dataRecepcao,
                           @NotNull Instant dataEntrega,
                           @NotNull boolean Entregue,
                           @NotNull Morador morador,
                           @NotNull Funcionario funcionario) {
}
