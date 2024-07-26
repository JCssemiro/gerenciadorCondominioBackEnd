package cassemiro.juan.seucondominio.repositories;


import cassemiro.juan.seucondominio.models.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnidadeRepository extends JpaRepository<Unidade,Long> {

    @Query("SELECT u FROM Unidade u WHERE u.torre.id = :id")
    List<Unidade> findAllByTorre(Long id);
}
