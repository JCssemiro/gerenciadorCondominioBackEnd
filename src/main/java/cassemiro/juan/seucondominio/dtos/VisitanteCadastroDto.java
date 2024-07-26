package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record VisitanteCadastroDto(@NotNull String nome,
                                    @NotNull String cpf,
                                    @NotNull Date dataNascimento,
                                    @NotNull String sexo
                            ) {
}