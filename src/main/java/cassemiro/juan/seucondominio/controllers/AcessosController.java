package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.AcessosCadastroDto;
import cassemiro.juan.seucondominio.dtos.AcessosDto;
import cassemiro.juan.seucondominio.services.AcessosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/acesso")
public class AcessosController {
    @Autowired
    private AcessosService acessosService;

    @GetMapping
    public ResponseEntity<List<AcessosDto>> listarTodosAcessos(@RequestParam(value = "page",defaultValue = "0") int page,
                                                               @RequestParam(value = "morador",required = false) Long idMorador,
                                                               @RequestParam(value = "visitante",required = false) Long idVisitante){
        List<AcessosDto> listaAcessos = new ArrayList<>();
        if(idMorador != null){
            listaAcessos = acessosService.listarTodosAcessosPorMorador(idMorador,page);
        }else if(idVisitante != null){
            listaAcessos = acessosService.listarTodosAcessosPorVisitante(idVisitante,page);
        }else{
            listaAcessos = acessosService.listarTodosAcessos(page);
        }
        return ResponseEntity.ok(listaAcessos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcessosDto> listarAcessoPorId(@PathVariable Long id){
        AcessosDto acessoEncontrado = acessosService.listarAcesosPorId(id);
        return ResponseEntity.ok(acessoEncontrado);
    }

    @PostMapping
    public ResponseEntity<AcessosDto> cadastrarAcesso(@RequestBody @Valid AcessosCadastroDto dto, UriComponentsBuilder uriBuilder){
        AcessosDto novoAcesso = acessosService.cadastrarNovoAcesso(dto);
        URI uri = uriBuilder.path("/av1/acesso/{id}").buildAndExpand(novoAcesso.id()).toUri();
        return ResponseEntity.created(uri).body(novoAcesso);
    }

}
