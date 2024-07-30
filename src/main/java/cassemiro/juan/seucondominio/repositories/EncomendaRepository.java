package cassemiro.juan.seucondominio.repositories;

import cassemiro.juan.seucondominio.models.Encomenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EncomendaRepository extends JpaRepository<Encomenda,Long> {

    @Query("SELECT e FROM Encomenda e WHERE e.morador.id = :id")
    Page<Encomenda> findAlLByMoradorId(Long id, Pageable pageable);

    @Query("SELECT e from Encomenda e WHERE e.funcionario.id = :id")
    Page<Encomenda> findAllByFuncionarioId(Long id, Pageable pageable);

    @Query("SELECT e FROM Encomenda e WHERE e.morador.unidade.id = :id")
    Page<Encomenda> findAllByUnidadeId(Long id,Pageable pageable);

}
