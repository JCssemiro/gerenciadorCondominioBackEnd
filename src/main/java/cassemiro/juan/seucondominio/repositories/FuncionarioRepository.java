package cassemiro.juan.seucondominio.repositories;

import cassemiro.juan.seucondominio.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    Optional<Funcionario> findByEmail(String email);
}
