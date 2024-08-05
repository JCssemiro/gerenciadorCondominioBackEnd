package cassemiro.juan.seucondominio.models;

import cassemiro.juan.seucondominio.dtos.CargoCadastroDto;
import cassemiro.juan.seucondominio.dtos.CargoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="cargo")
public class Cargo implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false,length = 100)
    private String nome;

    @Column(name="salario_base",nullable = false)
    private float salarioBase;

    @Column(name="carga_horaria",nullable = false)
    private int cargaHoraria;


    public Cargo(CargoDto cargo) {
        this.id = cargo.id();
        this.nome = cargo.nome();
        this.salarioBase = cargo.salarioBase();
        this.cargaHoraria = cargo.cargaHoraria();
    }

    public Cargo(CargoCadastroDto cargo) {
        this.nome = cargo.nome();
        this.salarioBase = cargo.salarioBase();
        this.cargaHoraria = cargo.cargaHoraria();
    }

    @Override
    public String getAuthority() {
        if(Objects.equals(nome, "Supervisor")){
            return ROLES.ROLE_SUPERVISOR.name();
        }else{
            return ROLES.ROLE_FUNCIONARIO.name();
        }
    }
}
