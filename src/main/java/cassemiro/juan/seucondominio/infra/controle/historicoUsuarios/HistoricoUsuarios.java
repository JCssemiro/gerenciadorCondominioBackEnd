package cassemiro.juan.seucondominio.infra.controle.historicoUsuarios;


import cassemiro.juan.seucondominio.models.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historico_usuarios")
public class HistoricoUsuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant data;

    @Column(nullable = false,length = 11)
    private String acao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id",referencedColumnName = "id")
    private Funcionario funcionario;

}
