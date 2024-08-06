package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.FuncionarioCadastroDto;
import cassemiro.juan.seucondominio.dtos.FuncionarioDto;
import cassemiro.juan.seucondominio.dtos.RecuperacaoSenhaDto;
import cassemiro.juan.seucondominio.models.Cargo;
import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.repositories.CargoRepository;
import cassemiro.juan.seucondominio.repositories.FuncionarioRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private MailService mailService;

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

    public FuncionarioDto listarFuncionarioPorEmail(String email){
        return new FuncionarioDto(funcionarioRepository.findByEmail(email)
                .orElseThrow(()->new NoSuchElementException("Funcionário não encontrado!")));
    }

    public FuncionarioDto cadastrarFuncionario(FuncionarioCadastroDto dto) throws MessagingException, IOException {
            Cargo cargoDoFuncionario = cargoRepository.findById(dto.cargoId()).orElseThrow(()-> new NoSuchElementException("O cargo informado não existe!"));
            String senhaRandom = UUID.randomUUID().toString().substring(0,16);
            String senhaCodificada = passwordEncoder.encode(senhaRandom);
            Funcionario funcionarioCadastrado = funcionarioRepository.save(new Funcionario(dto, cargoDoFuncionario,senhaCodificada));
            mailService.enviarEmailCadastro(funcionarioCadastrado.getEmail(),funcionarioCadastrado.getNome().split(" ")[0]);
            return new FuncionarioDto(funcionarioCadastrado);

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

    public void alterarSenha(RecuperacaoSenhaDto dto){
        String senhaCodificada = passwordEncoder.encode(dto.senha());
        Funcionario funcionario = funcionarioRepository.findByEmail(dto.funcionarioEmail()).get();
        funcionario.setSenha(senhaCodificada);
        funcionarioRepository.save(funcionario);
    }

}
