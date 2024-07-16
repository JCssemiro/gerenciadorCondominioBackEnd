package cassemiro.juan.seucondominio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false,length = 100)
    private String nome;

    @Column(name="salario_base",nullable = false)
    private float salarioBase;

    @Column(name="carga_horaria",nullable = false)
    private int cargaHoraria;



}
