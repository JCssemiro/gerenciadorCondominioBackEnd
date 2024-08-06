package cassemiro.juan.seucondominio.controllers;

import cassemiro.juan.seucondominio.dtos.*;
import cassemiro.juan.seucondominio.infra.security.TokenDto;
import cassemiro.juan.seucondominio.models.Funcionario;
import cassemiro.juan.seucondominio.services.FuncionarioService;
import cassemiro.juan.seucondominio.services.MailService;
import cassemiro.juan.seucondominio.services.RecuperacaoSenhaService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cassemiro.juan.seucondominio.infra.security.TokenService;

import java.io.IOException;


@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RecuperacaoSenhaService recuperacaoSenhaService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid AuthDto data){

            var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
            var auth = this.authenticationManager.authenticate(UsernamePassword);
            var token = tokenService.generateToken((Funcionario) auth.getPrincipal());
            Funcionario funcionario = (Funcionario)auth.getPrincipal();
            return ResponseEntity.ok(new TokenDto(token,funcionario.getId()));

    }

    @PostMapping("/solicitar-recuperacao")
    public ResponseEntity<MessageDto> solicitarRecuperacao(@RequestBody @Valid SolicitarRecuperacaoSenhaDto dto) throws MessagingException, IOException {
        FuncionarioDto funcionario = funcionarioService.listarFuncionarioPorEmail(dto.funcionarioEmail());
        String code = recuperacaoSenhaService.gerarCodigoRecuperacao(dto.funcionarioEmail());
        mailService.enviarEmailRedefinicaoSenha(funcionario.email(),code);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageDto("Código de recuperação de senha enviado para o e-mail cadastrado!"));
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<MessageDto> recuperarSenha(@RequestBody @Valid RecuperacaoSenhaDto dto){
        if(recuperacaoSenhaService.validarToken(dto)){
            funcionarioService.alterarSenha(dto);
            return ResponseEntity.ok(new MessageDto("Senha alterada com sucesso!"));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageDto("Código Incorreto ou Expirado!"));
        }
    }

}
