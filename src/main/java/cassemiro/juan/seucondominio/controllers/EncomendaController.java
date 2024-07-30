package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.EncomendaCadastroDto;
import cassemiro.juan.seucondominio.dtos.EncomendaDto;
import cassemiro.juan.seucondominio.dtos.EncomendaEdicaoDto;
import cassemiro.juan.seucondominio.services.EncomendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/encomenda")
public class EncomendaController {

    @Autowired
    private EncomendaService encomendaService;

    @GetMapping
    public ResponseEntity<List<EncomendaDto>> listarEncomendas(@RequestParam(value = "page",defaultValue = "0")int page,
                                           @RequestParam(value = "funcionario",required = false)Long funcionarioId,
                                           @RequestParam(value = "morador",required = false)Long moradorId,
                                           @RequestParam(value = "unidade",required = false)Long unidadeId){

        List<EncomendaDto> listaEncomendas;

        if(funcionarioId != null){
            listaEncomendas = encomendaService.listarEncomendasPorFuncionario(funcionarioId,page);

        } else if (moradorId != null) {
            listaEncomendas = encomendaService.listarEncomendasPorMorador(moradorId,page);
        }else if (unidadeId != null){
            listaEncomendas = encomendaService.listarEncomendasPorUnidadeId(unidadeId,page);
        }else{
            listaEncomendas = encomendaService.listarTodasEncomendas(page);
        }
        return ResponseEntity.ok(listaEncomendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncomendaDto> listarEncomendaPorId(@PathVariable Long id){
        EncomendaDto encomendaEncontrada = encomendaService.listarEncomendaPorId(id);
        return ResponseEntity.ok(encomendaEncontrada);
    }

    @PostMapping
    public ResponseEntity<EncomendaDto> cadastrarEncomenda(@RequestBody @Valid EncomendaCadastroDto dto, UriComponentsBuilder uriBuilder){
        EncomendaDto encomendaCadastrada = encomendaService.cadastrarEncomenda(dto);
        URI uri = uriBuilder.path("/v1/encomenda/{id}").buildAndExpand(encomendaCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(encomendaCadastrada);
    }

    @PutMapping
    public ResponseEntity<EncomendaDto> editarEncomenda(@RequestBody @Valid EncomendaEdicaoDto dto){
        EncomendaDto encomendaEditada = encomendaService.editarEncomenda(dto);
        return ResponseEntity.ok(encomendaEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEncomendaPorId(@PathVariable Long id){
        encomendaService.deletarEncomendaPorId(id);
        return ResponseEntity.noContent().build();
    }

}
