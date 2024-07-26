package cassemiro.juan.seucondominio.repositories;

import cassemiro.juan.seucondominio.models.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoradorRepository extends JpaRepository<Morador,Long> {

    @Query("SELECT m FROM Morador m WHERE m.unidade.numero = :numero AND m.unidade.torre.nome = :torre")
    List<Morador> findAllByNumeroETorre(String torre,int numero);

    @Query("SELECT m FROM Morador m WHERE m.unidade.torre.nome = :torre")
    List<Morador> findAllByTorre(String torre);
}
