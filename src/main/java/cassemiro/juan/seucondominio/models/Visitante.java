package cassemiro.juan.seucondominio.models;


import cassemiro.juan.seucondominio.dtos.VisitanteCadastroDto;
import cassemiro.juan.seucondominio.dtos.VisitanteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="visitante")
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome",nullable = false)
    private String nome;

    @Column(name="cpf",nullable = false,unique = true,length = 11)
    private String cpf;

    @Column(name="data_nascimento",nullable = false)
    private Date dataNascimento;

    @Column(name = "sexo",nullable = false,length = 9)
    private String sexo;

    public Visitante(VisitanteCadastroDto dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.sexo = dto.sexo();
    }

    public Visitante(VisitanteDto dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.sexo = dto.sexo();
    }
}
