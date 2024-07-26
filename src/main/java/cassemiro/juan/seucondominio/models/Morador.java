package cassemiro.juan.seucondominio.models;


import cassemiro.juan.seucondominio.dtos.MoradorCadastroDto;
import cassemiro.juan.seucondominio.dtos.MoradorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="morador")
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome",nullable = false)
    private String nome;

    @Column(name="cpf",nullable = false,unique = true,length = 11)
    private String cpf;

    @Column(name="data_nascimento",nullable = false)
    private Date dataNascimento;

    @Column(name="sexo",length = 9,nullable = false)
    private String sexo;

    @ManyToOne
    @JoinColumn(name="unidade_id",nullable = false,referencedColumnName = "id")
    private Unidade unidade;

    public Morador(MoradorCadastroDto dto,Unidade unidade) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.sexo = dto.sexo();
        this.unidade = unidade;
    }

    public Morador(MoradorDto dto, Unidade unidadeAlterada) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.sexo = dto.sexo();
        this.unidade = unidadeAlterada;
    }
}
