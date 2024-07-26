package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Acessos;
import cassemiro.juan.seucondominio.models.Morador;
import cassemiro.juan.seucondominio.models.Visitante;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record AcessosDto(@NotNull Long id,
                         @NotNull Morador morador,
                         @NotNull Visitante visitante,
                         @NotNull Instant dataEntrada,
                         @NotNull Instant dataSaida
                         ) {
    public AcessosDto(Acessos acessos){
        this(acessos.getId(), acessos.getMorador(),acessos.getVisitante(),acessos.getDataEntrada(),acessos.getDataSaida());
    }
}
