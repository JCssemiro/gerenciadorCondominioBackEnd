package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Encomenda;
import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.models.Morador;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record EncomendaDto(@NotNull Long id,
                           @NotNull String descricao,
                           @NotNull Float peso,
                           @NotNull Float altura,
                           @NotNull Float comprimento,
                           @NotNull Float largura,
                           @NotNull Instant dataRecepcao,
                           @NotNull Instant dataEntrega,
                           @NotNull Boolean entregue,
                           @NotNull Long morador,
                           @NotNull Long funcionario) {

    public EncomendaDto(Encomenda encomenda){
        this(
          encomenda.getId(),
          encomenda.getDescricao(),
          encomenda.getPeso(),
          encomenda.getAltura(),
          encomenda.getComprimento(),
          encomenda.getLargura(),
          encomenda.getDataRecepcao(),
          encomenda.getDataEntrega(),
          encomenda.getEntregue(),
          encomenda.getMorador().getId(),
          encomenda.getFuncionario().getId()
        );
    }
}
