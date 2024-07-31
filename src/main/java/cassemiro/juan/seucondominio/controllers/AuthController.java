package cassemiro.juan.seucondominio.controllers;

import cassemiro.juan.seucondominio.dtos.AuthDto;
import cassemiro.juan.seucondominio.infra.security.TokenDto;
import cassemiro.juan.seucondominio.models.Funcionario;
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


@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid AuthDto data){
        try {
            var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
            var auth = this.authenticationManager.authenticate(UsernamePassword);
            var token = tokenService.generateToken((Funcionario) auth.getPrincipal());
            Funcionario funcionario = (Funcionario)auth.getPrincipal();

            return ResponseEntity.ok(new TokenDto(token,funcionario.getId()));

        } catch (Exception e){
            System.out.println("Falha de Autenticação: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
