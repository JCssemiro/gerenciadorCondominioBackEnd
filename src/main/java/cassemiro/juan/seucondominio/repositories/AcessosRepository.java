package cassemiro.juan.seucondominio.repositories;

import cassemiro.juan.seucondominio.models.Acessos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AcessosRepository extends JpaRepository<Acessos,Long> {

    @Query("SELECT a FROM Acessos a WHERE a.morador.id = :id")
    Page<Acessos> findAllByMoradorId(Long id, Pageable pageable);

    @Query("SELECT a FROM Acessos a WHERE a.visitante.id = :id")
    Page<Acessos> findAllByVisitanteId(Long id, Pageable pageable);
}
