package cassemiro.juan.seucondominio.controllers;



import cassemiro.juan.seucondominio.dtos.CargoCadastroDto;
import cassemiro.juan.seucondominio.dtos.CargoDto;
import cassemiro.juan.seucondominio.services.CargoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<List<CargoDto>> listarCargos(){
        List<CargoDto> listaCargos = cargoService.listarTodosCargos();
        return ResponseEntity.ok(listaCargos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDto> listarCargoPorId(@PathVariable Long id){
        CargoDto cargoEncontrado = cargoService.listarCargoPorId(id);
        return ResponseEntity.ok(cargoEncontrado);
    }

    @PostMapping
    public ResponseEntity<CargoDto> cadastrarCargo(@RequestBody @Valid CargoCadastroDto dto, UriComponentsBuilder uriBuilder){
        CargoDto cargoCadastrado = cargoService.cadastrarCargo(dto);
        URI uri = uriBuilder.path("/cargo/{id}").buildAndExpand(cargoCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(cargoCadastrado);
    }

    @PutMapping
    public ResponseEntity<CargoDto> editarCargo(@RequestBody @Valid CargoDto cargo){
        CargoDto cargoEditado = cargoService.editarCargo(cargo);
        return ResponseEntity.ok(cargoEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CargoDto> excluirCargoPorId(@PathVariable Long id){
        cargoService.excluirCargoPorId(id);
        return ResponseEntity.noContent().build();
    }

    
}
