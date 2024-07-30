package cassemiro.juan.seucondominio.infra.controle.historicoUsuarios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoUsuarioRepository extends JpaRepository<HistoricoUsuarios,Long> {
}
