package cassemiro.juan.seucondominio.repositories;

import cassemiro.juan.seucondominio.models.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PontoRepository extends JpaRepository<Ponto,Long> {

    @Query("SELECT p from Ponto p WHERE p.funcionario.id = :funcionarioId ORDER BY p.horarioEntrada DESC LIMIT 1")
    Optional<Ponto> findLatestPontoByFuncionarioId(Long funcionarioId);

    @Query("SELECT p from Ponto p WHERE p.funcionario.id = :id")
    List<Ponto> findAllByFuncionarioId(Long id);
}
