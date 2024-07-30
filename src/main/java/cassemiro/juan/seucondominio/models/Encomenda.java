package cassemiro.juan.seucondominio.models;


import cassemiro.juan.seucondominio.dtos.EncomendaCadastroDto;
import cassemiro.juan.seucondominio.dtos.EncomendaEdicaoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;


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
    private Float peso;

    @Column(name="altura",nullable = false)
    private Float altura;

    @Column(name="comprimento",nullable = false)
    private Float comprimento;

    @Column(name="largura",nullable = false)
    private Float largura;

    @Column(name="data_recepcao",nullable = false)
    private Instant dataRecepcao;

    @Column(name="data_entrega")
    private Instant dataEntrega;

    @Column(name="entregue",nullable = false)
    private Boolean entregue;

    @ManyToOne
    @JoinColumn(name = "morador_id",referencedColumnName = "id")
    private Morador morador;

    @ManyToOne
    @JoinColumn(name="funcionario_id",referencedColumnName = "id")
    private Funcionario funcionario;




    public Encomenda(EncomendaCadastroDto encomenda, Morador morador, Funcionario funcionario) {
        this.descricao = encomenda.descricao();
        this.peso = encomenda.peso();
        this.altura = encomenda.altura();
        this.comprimento = encomenda.comprimento();
        this.largura = encomenda.largura();
        this.dataRecepcao = Instant.now();
        this.entregue = false;
        this.morador = morador;
        this.funcionario = funcionario;
    }
}
