package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Morador;
import cassemiro.juan.seucondominio.models.Unidade;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MoradorDto(@NotNull Long id,
                         @NotNull String nome,
                         @NotNull String cpf,
                         @NotNull Date dataNascimento,
                         @NotNull String sexo,
                         @NotNull Long unidadeId
                         ) {

    public MoradorDto(Morador morador){
        this(morador.getId(),morador.getNome(),morador.getCpf(),morador.getDataNascimento(),morador.getSexo(),morador.getUnidade().getId());
    }

}
