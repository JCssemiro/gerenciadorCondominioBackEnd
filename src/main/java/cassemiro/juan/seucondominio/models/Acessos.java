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
@Table(name="acessos")
public class Acessos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "morador_id",referencedColumnName = "id",nullable = false)
    private Morador morador;

    @ManyToOne
    @JoinColumn(name="visitante_id",referencedColumnName = "id",nullable = false)
    private Visitante visitante;

    @Column(name="data_entrada",nullable = false)
    private Instant dataEntrada;

    @Column(name = "data_saida",nullable = false)
    private Instant dataSaida;
}
