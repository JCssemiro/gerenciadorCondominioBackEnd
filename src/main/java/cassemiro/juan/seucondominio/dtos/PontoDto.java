package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.models.Ponto;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.Instant;

public record PontoDto(@NotNull Long id,
                        Instant horarioEntrada,
                        Instant horarioSaida,
                        Instant cargaHoraria,
                       @NotNull Funcionario funcionario) {

    public PontoDto(Ponto ponto){
         this(
                 ponto.getId(),
                 ponto.getHorarioEntrada(),
                 ponto.getHorarioSaida(),
                 ponto.getCargaHoraria(),
                 ponto.getFuncionario());
    }

}
