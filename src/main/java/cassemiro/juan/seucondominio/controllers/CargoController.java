package cassemiro.juan.seucondominio.controllers;



import cassemiro.juan.seucondominio.dtos.CargoCadastroDto;
import cassemiro.juan.seucondominio.dtos.CargoDto;
import cassemiro.juan.seucondominio.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity listarCargos(){
        List<CargoDto> listaCargos = cargoService.listarTodosCargos();
        return ResponseEntity.ok(listaCargos);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarCargoPorId(@PathVariable Long id){
        CargoDto cargoEncontrado = cargoService.listarCargoPorId(id);
        return ResponseEntity.ok(cargoEncontrado);
    }

    @PostMapping
    public ResponseEntity cadastrarCargo(@RequestBody CargoCadastroDto dto, UriComponentsBuilder uriBuilder){
        CargoDto cargoCadastrado = cargoService.cadastrarCargo(dto);
        URI uri = uriBuilder.path("/cargo/{id}").buildAndExpand(cargoCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(cargoCadastrado);
    }

    @PutMapping
    public ResponseEntity editarCargo(@RequestBody CargoDto cargo){
        CargoDto cargoEditado = cargoService.editarCargo(cargo);
        return ResponseEntity.ok(cargoEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCargoPorId(@PathVariable Long id){
        cargoService.excluirCargoPorId(id);
        return ResponseEntity.noContent().build();
    }

    
}
