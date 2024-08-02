package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.RecuperacaoSenhaDto;
import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.models.TokenRecuperacao;
import cassemiro.juan.seucondominio.repositories.FuncionarioRepository;
import cassemiro.juan.seucondominio.repositories.TokenRecuperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class RecuperacaoSenhaService {

    @Autowired
    private TokenRecuperacaoRepository tokenRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String gerarCodigoRecuperacao(Long funcionarioId){
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(()->new NoSuchElementException("Não é possível recuperar a senha para usuário inexistente"));
        String codigo = UUID.randomUUID().toString().substring(0,6).toUpperCase();
        TokenRecuperacao recuperacaoSenhaToken = new TokenRecuperacao();
        recuperacaoSenhaToken.setFuncionario(funcionario);
        recuperacaoSenhaToken.setToken(codigo);
        recuperacaoSenhaToken.setCriadoEm(Instant.now());
        recuperacaoSenhaToken.setDataExpiracao(Instant.now().plusSeconds(60*30));
        tokenRepository.save(recuperacaoSenhaToken);
        return codigo;
    }

    public Boolean validarToken(RecuperacaoSenhaDto dto){
        if(tokenRepository.findByFuncionarioId(dto.funcionarioId()).isPresent()){
           TokenRecuperacao token = tokenRepository.findByFuncionarioId(dto.funcionarioId()).get();
           if(token.getDataExpiracao().isBefore(Instant.now())){
               System.out.println("TOKEN EXPIRADO");
               tokenRepository.deleteById(token.getId());
               return false;
           }else{
               System.out.println("TOKEN VÁLIDO");
               tokenRepository.deleteById(token.getId());
               return Objects.equals(dto.codigo(), token.getToken());
           }
        }
        System.out.println("TOKEN INEXISTENTE");
        return false;
    }

}
