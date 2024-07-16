package cassemiro.juan.seucondominio.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="encomenda")
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao",nullable = false)
    private String descricao;

    @Column(name="peso",nullable = false)
    private float peso;

    @Column(name="altura",nullable = false)
    private float altura;

    @Column(name="comprimento",nullable = false)
    private float comprimento;

    @Column(name="largura",nullable = false)
    private float largura;

    @Column(name="data_recepcao",nullable = false)
    private Instant dataRecepcao;

    @Column(name="data_entrega")
    private Instant dataEntrega;

    @Column(name="entregue",nullable = false)
    private boolean entregue;

    @ManyToOne
    @JoinColumn(name = "morador_id",referencedColumnName = "id")
    private Morador morador;

    @ManyToOne
    @JoinColumn(name="funcionario_id",referencedColumnName = "id")
    private Funcionario funcionario;


}
