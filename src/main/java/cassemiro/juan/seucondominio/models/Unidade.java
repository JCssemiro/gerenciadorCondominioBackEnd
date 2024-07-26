package cassemiro.juan.seucondominio.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero",nullable = false)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name="torre_id",referencedColumnName = "id",nullable = false)
    @JsonManagedReference
    private Torre torre;

    public Unidade(int numero, Torre torreNova) {
        this.numero = numero;
        this.torre = torreNova;
    }
}
