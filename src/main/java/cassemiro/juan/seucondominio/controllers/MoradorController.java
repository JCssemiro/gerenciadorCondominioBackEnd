package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.MoradorCadastroDto;
import cassemiro.juan.seucondominio.dtos.MoradorDto;
import cassemiro.juan.seucondominio.services.MoradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/morador")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @GetMapping
    public ResponseEntity<List<MoradorDto>> listarTodosMoradores(@RequestParam(value = "page",defaultValue = "0") int page,
                                                                 @RequestParam(value="torre",required = false) String torre,
                                                                 @RequestParam(value="numero",required = false,defaultValue = "-1") int numero){
        if(torre != null && numero != -1){
            List<MoradorDto> listaMoradores = moradorService.listarMoradoresPorUnidade(torre,numero);
            return ResponseEntity.ok(listaMoradores);
        }else if(torre != null){
            List<MoradorDto> listaMoradores = moradorService.listarMoradoresPortorre(torre);
            return ResponseEntity.ok(listaMoradores);
        }

        List<MoradorDto> listaMoradores = moradorService.listarTodosMoradores(page);
        return ResponseEntity.ok(listaMoradores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoradorDto> listarMoradorPorId(@PathVariable Long id){
        MoradorDto moradorEncontrado = moradorService.listarMoradorPorId(id);
        return ResponseEntity.ok(moradorEncontrado);
    }

    @PostMapping
    public ResponseEntity<MoradorDto> cadastrarMorador(@RequestBody @Valid MoradorCadastroDto dto, UriComponentsBuilder uriBuilder){
        MoradorDto novoMorador = moradorService.cadastrarMorador(dto);
        URI uri = uriBuilder.path("/v1/morador/{id}").buildAndExpand(novoMorador.id()).toUri();
        return ResponseEntity.created(uri).body(novoMorador);
    }

    @PutMapping
    public ResponseEntity<MoradorDto> editarMorador(@RequestBody @Valid MoradorDto dto){
        MoradorDto moradorEditado = moradorService.editarMorador(dto);
        return ResponseEntity.ok(moradorEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMoradorPorId(@PathVariable Long id){
        moradorService.deletarMoradorPorId(id);
        return ResponseEntity.noContent().build();
    }


}
