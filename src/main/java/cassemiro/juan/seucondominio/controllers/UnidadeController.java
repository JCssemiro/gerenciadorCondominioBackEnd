package cassemiro.juan.seucondominio.controllers;


import cassemiro.juan.seucondominio.dtos.UnidadeDto;
import cassemiro.juan.seucondominio.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<List<UnidadeDto>> listarTodasUnidadesPaginadas(@RequestParam(value = "torre",required = false) Long torreId,@RequestParam(value ="page",defaultValue = "0") int page){
        if(torreId != null){
            List<UnidadeDto> listaUnidades = unidadeService.listarUnidadesPorIdTorre(torreId);
            return ResponseEntity.ok(listaUnidades);
        }
        List<UnidadeDto> listaUnidades = unidadeService.listarTodasUnidadesPaginadas(page);

        return ResponseEntity.ok(listaUnidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDto> listarUnidadePorId(@PathVariable Long id){
        UnidadeDto unidadeEncontrada = unidadeService.listarUnidadePorId(id);
        return ResponseEntity.ok(unidadeEncontrada);
    }

}
