package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Visitante;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record VisitanteDto(@NotNull Long id,
                           @NotNull String nome,
                           @NotNull String cpf,
                           @NotNull Date dataNascimento,
                           @NotNull String sexo) {
    public VisitanteDto(Visitante visitante){
        this(visitante.getId(),visitante.getNome(),visitante.getCpf(),visitante.getDataNascimento(),visitante.getSexo());
    }
}
