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
@Table(name="ponto")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horario_entrada",nullable = false)
    private Instant horarioEntrada;

    @Column(name = "horario_saida")
    private Instant horarioSaida;

    @Column(name = "carga_horaria")
    private Instant cargaHoraria;

    @ManyToOne
    @JoinColumn(name="funcionario_id",referencedColumnName = "id",nullable = false)
    private Funcionario funcionario;

}
