package cassemiro.juan.seucondominio.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthDto(@NotNull String login,@NotNull String senha) {
}
