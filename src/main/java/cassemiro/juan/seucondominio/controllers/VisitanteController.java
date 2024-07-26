package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.VisitanteCadastroDto;
import cassemiro.juan.seucondominio.dtos.VisitanteDto;
import cassemiro.juan.seucondominio.services.VisitanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/visitante")
public class VisitanteController {

    @Autowired
    private VisitanteService visitanteService;

    @GetMapping
    public ResponseEntity<List<VisitanteDto>> listarTodosVisitantes(@RequestParam(value = "page",defaultValue = "0")int page){
        List<VisitanteDto> listaVisitantes = visitanteService.listarTodosVisitantes(page);
        return ResponseEntity.ok(listaVisitantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitanteDto> listarVisitantePorId(@PathVariable Long id){
        VisitanteDto visitanteEncontrado = visitanteService.listarVisitantePorId(id);
        return ResponseEntity.ok(visitanteEncontrado);
    }

    @PostMapping
    public ResponseEntity<VisitanteDto> cadastrarVisitante(@RequestBody @Valid VisitanteCadastroDto dto, UriComponentsBuilder uriBuilder){
        VisitanteDto visitanteCadastrado = visitanteService.cadastrarVisitante(dto);
        URI uri = uriBuilder.path("/v1/visitante/{id}").buildAndExpand(visitanteCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(visitanteCadastrado);
    }

    @PutMapping
    public ResponseEntity<VisitanteDto> editarVisitante(@RequestBody @Valid VisitanteDto dto){
        VisitanteDto visitanteEditado = visitanteService.editarVisitante(dto);
        return ResponseEntity.ok(visitanteEditado);
    }

}
