package cassemiro.juan.seucondominio.models;


import cassemiro.juan.seucondominio.dtos.FuncionarioCadastroDto;
import cassemiro.juan.seucondominio.dtos.FuncionarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="funcionario")
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;
    @Column(name="cpf",length = 11,nullable = false,unique = true)
    private String cpf;

    @Column(name="data_nascimento",nullable = false)
    private Date dataNascimento;

    @Column(name = "sexo",nullable = false,length = 9)
    private String sexo;

    @Column(name="telefone",length = 14,nullable = false,unique = true)
    private String telefone;

    @Column(name="email",length = 100,nullable = false,unique = true)
    private String email;

    @Column(name = "salario",nullable = false)
    private float salario;

    @Column(name = "senha",nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "cargo_id",referencedColumnName = "id")
    private Cargo cargo;

    public Funcionario(FuncionarioDto funcionario,Cargo cargoAlterado) {
        this.id = funcionario.id();
        this.nome = funcionario.nome();
        this.cpf = funcionario.cpf();
        this.dataNascimento = funcionario.dataNascimento();
        this.sexo = funcionario.sexo();
        this.telefone = funcionario.telefone();
        this.email = funcionario.email();
        this.salario = funcionario.salario();
        this.cargo = cargoAlterado;
    }

    public Funcionario(FuncionarioCadastroDto funcionario,Cargo cargo,String senha) {
        this.nome = funcionario.nome();
        this.cpf = funcionario.cpf();
        this.dataNascimento = funcionario.dataNascimento();
        this.sexo = funcionario.sexo();
        this.telefone = funcionario.telefone();
        this.email = funcionario.email();
        this.salario = funcionario.salario();
        this.cargo = cargo;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
