package cassemiro.juan.seucondominio.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


@Data
@Entity
@Table(name="token_recuperacao")
public class TokenRecuperacao{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "funcionario_id",referencedColumnName = "id")
    private Funcionario funcionario;

    @Column(name = "data_expiracao",nullable = false)
    private Instant dataExpiracao;

    @Column(name = "criado_em",nullable = false)
    private Instant criadoEm;

    @Column(name="token",length = 6,nullable = false)
    private String token;

}
