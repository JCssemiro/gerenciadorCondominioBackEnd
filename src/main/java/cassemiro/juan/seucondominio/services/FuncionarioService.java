package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.FuncionarioCadastroDto;
import cassemiro.juan.seucondominio.dtos.FuncionarioDto;
import cassemiro.juan.seucondominio.models.Cargo;
import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.repositories.CargoRepository;
import cassemiro.juan.seucondominio.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    public List<FuncionarioDto> listarTodosFuncionarios(){
        return funcionarioRepository.findAll().stream().map(FuncionarioDto::new).toList();
    }

    public FuncionarioDto listarFuncionarioPorId(Long id){
        try {
            return new FuncionarioDto(funcionarioRepository.findById(id).get());
        }catch(NoSuchElementException e){
            throw new NoSuchElementException("Funcionário não encontrado!");
        }
    }

    public FuncionarioDto cadastrarFuncionario(FuncionarioCadastroDto dto){
        try {
            Cargo cargoDoFuncionario = cargoRepository.findById(dto.cargoId()).get();
            Funcionario funcionarioCadastrado = funcionarioRepository.save(new Funcionario(dto, cargoDoFuncionario));
            return new FuncionarioDto(funcionarioCadastrado);
        }catch(NoSuchElementException e){
            throw new NoSuchElementException("O cargo informado não existe!");
        }
    }

    public FuncionarioDto editarFuncionario(FuncionarioDto funcionario){
        if(funcionarioRepository.existsById(funcionario.id())){
            Cargo cargoAlterado = cargoRepository.findById(funcionario.cargo().getId())
                    .orElseThrow(()-> new NoSuchElementException("O Cargo informado não existe"));
            Funcionario funcionarioAlterado = new Funcionario(funcionario,cargoAlterado);
            funcionarioRepository.save(funcionarioAlterado);
            return new FuncionarioDto(funcionarioAlterado);
        }
        throw new NoSuchElementException("O Funcionário não pôde ser alterado!");
    }

    public void excluirFuncionarioPorId(Long id){
        funcionarioRepository.deleteById(id);
    }

}
