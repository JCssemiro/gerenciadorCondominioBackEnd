package cassemiro.juan.seucondominio.repositories;

import cassemiro.juan.seucondominio.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}
