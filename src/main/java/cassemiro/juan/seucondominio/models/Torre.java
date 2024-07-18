package cassemiro.juan.seucondominio.models;


import cassemiro.juan.seucondominio.dtos.TorreCadastroDto;
import cassemiro.juan.seucondominio.dtos.TorreDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="torre")
public class Torre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome",length = 100,nullable = false,unique = true)
    private String nome;

    @Column(name = "andares",nullable = false)
    private int andares;

    @Column(name="qtd_unidades",nullable = false)
    private int qtdUnidades;

    public Torre(TorreDto dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.andares = dto.andares();
        this.qtdUnidades = dto.qtdUnidades();
    }

    public Torre(TorreCadastroDto dto) {
        this.nome = dto.nome();
        this.andares = dto.andares();
        this.qtdUnidades = dto.qtdUnidades();
    }
}
