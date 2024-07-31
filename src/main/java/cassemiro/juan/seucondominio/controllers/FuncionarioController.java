package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.FuncionarioCadastroDto;
import cassemiro.juan.seucondominio.dtos.FuncionarioDto;
import cassemiro.juan.seucondominio.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/staff")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;



    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> listarTodosFuncionarios(){
        List<FuncionarioDto> listaFuncionarios = funcionarioService.listarTodosFuncionarios();
        return ResponseEntity.ok(listaFuncionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDto> listarFuncionarioPorId(@PathVariable Long id){
        FuncionarioDto funcionarioEncontrado = funcionarioService.listarFuncionarioPorId(id);
        return ResponseEntity.ok(funcionarioEncontrado);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDto> cadastrarFuncionario(@RequestBody @Valid FuncionarioCadastroDto dto, UriComponentsBuilder uriBuilder){
        FuncionarioDto funcionarioNovo = funcionarioService.cadastrarFuncionario(dto);
        URI uri = uriBuilder.path("/staff/{id}").buildAndExpand(funcionarioNovo.id()).toUri();
        return ResponseEntity.created(uri).body(funcionarioNovo);
    }

    @PutMapping
    public ResponseEntity<FuncionarioDto> editarFuncionario(@RequestBody @Valid FuncionarioDto dto){
        FuncionarioDto funcionarioAlterado = funcionarioService.editarFuncionario(dto);
        return ResponseEntity.ok(funcionarioAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FuncionarioDto> excluirFuncionarioPorId(@PathVariable Long id){
        funcionarioService.excluirFuncionarioPorId(id);
        return ResponseEntity.noContent().build();
    }

}
