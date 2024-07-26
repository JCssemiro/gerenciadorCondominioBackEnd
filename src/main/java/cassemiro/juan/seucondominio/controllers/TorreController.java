package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.TorreCadastroDto;
import cassemiro.juan.seucondominio.dtos.TorreDto;
import cassemiro.juan.seucondominio.services.TorreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/torre")
public class TorreController {

    @Autowired
    private TorreService torreService;

    @GetMapping
    public ResponseEntity<List<TorreDto>> listarTodasTorres(){
        List<TorreDto> listaTorres = torreService.listarTodasTorres();
        return ResponseEntity.ok(listaTorres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TorreDto> listarTorrePorId(@PathVariable Long id){
        TorreDto torreEncontrada = torreService.listarTorrePorId(id);
        return ResponseEntity.ok(torreEncontrada);
    }

    @PostMapping
    public ResponseEntity<TorreDto> cadastrarTorre(@RequestBody @Valid TorreCadastroDto dto, UriComponentsBuilder uriBuilder){
        TorreDto torreCadastrada = torreService.cadastrarTorre(dto);
        URI uri = uriBuilder.path("/v1/torre/{id}").buildAndExpand(torreCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(torreCadastrada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<TorreDto> excluirTorrePorId(@PathVariable Long id){
        torreService.excluirTorrePorId(id);
        return ResponseEntity.noContent().build();
    }
}
