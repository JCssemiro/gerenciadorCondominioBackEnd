package cassemiro.juan.seucondominio.dtos;

import cassemiro.juan.seucondominio.models.Cargo;
import cassemiro.juan.seucondominio.models.Funcionario;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record FuncionarioDto(@NotNull Long id,
                             @NotNull String nome,
                             @NotNull String cpf,
                             @NotNull Date dataNascimento,
                             @NotNull String sexo,
                             @NotNull String telefone,
                             @NotNull  String email,
                             @NotNull float salario,
                             @NotNull Cargo cargo) {

    public FuncionarioDto(Funcionario funcionario){
        this(funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getDataNascimento(),
                funcionario.getSexo(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                funcionario.getSalario(),
                funcionario.getCargo());
    }
}
