package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.PontoDto;
import cassemiro.juan.seucondominio.dtos.PontoRegistrarDto;
import cassemiro.juan.seucondominio.services.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @GetMapping
    public ResponseEntity<List<PontoDto>> listarTodosPontos(){
        List<PontoDto> listaRegistros = pontoService.listarTodosRegistros();
        return ResponseEntity.ok(listaRegistros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoDto> listarPontoPorId(@PathVariable Long id){
        PontoDto registroEncontrado = pontoService.listarRegistroPorId(id);
        return ResponseEntity.ok(registroEncontrado);
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<List<PontoDto>> listasrPontoPorIdFuncionario(@PathVariable Long id){
        List<PontoDto> listaRegistros = pontoService.listarRegistrosPorIdFuncionario(id);
        return ResponseEntity.ok(listaRegistros);
    }

    @PostMapping
    public ResponseEntity<PontoDto> registrarPonto(@RequestBody PontoRegistrarDto dto, UriComponentsBuilder uriBuilder){
        PontoDto ponto = pontoService.registrarPonto(dto);
        URI uri = uriBuilder.path("/ponto/{id}").buildAndExpand(ponto.id()).toUri();
        return ResponseEntity.created(uri).body(ponto);
    }

    @PutMapping
    public ResponseEntity<PontoDto> fecharPonto(@RequestBody PontoRegistrarDto dto){
        PontoDto ponto = pontoService.fecharPonto(dto);
        return ResponseEntity.ok(ponto);
    }





}
