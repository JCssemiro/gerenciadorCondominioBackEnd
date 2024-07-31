package cassemiro.juan.seucondominio.infra.security;

import jakarta.validation.constraints.NotNull;

public record TokenDto(@NotNull String token,@NotNull Long id) {
}
