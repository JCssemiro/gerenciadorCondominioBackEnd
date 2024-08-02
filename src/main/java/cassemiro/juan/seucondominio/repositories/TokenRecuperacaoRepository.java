package cassemiro.juan.seucondominio.repositories;

import cassemiro.juan.seucondominio.models.TokenRecuperacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRecuperacaoRepository extends JpaRepository<TokenRecuperacao,Long> {

    @Query("SELECT t FROM TokenRecuperacao t WHERE t.funcionario.id = :funcionarioId")
    Optional<TokenRecuperacao> findByFuncionarioId(Long funcionarioId);
}
