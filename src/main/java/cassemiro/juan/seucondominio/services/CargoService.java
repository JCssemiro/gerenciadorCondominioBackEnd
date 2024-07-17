package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.CargoCadastroDto;
import cassemiro.juan.seucondominio.dtos.CargoDto;
import cassemiro.juan.seucondominio.models.Cargo;
import cassemiro.juan.seucondominio.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<CargoDto> listarTodosCargos(){
        return cargoRepository.findAll().stream().map(CargoDto::new).toList();
    }

    public CargoDto listarCargoPorId(Long id) {
        try {
            return new CargoDto(cargoRepository.findById(id).get());
        }catch(NoSuchElementException e){
            throw new NoSuchElementException("Cargo não encontrado!");
        }
    }

    public CargoDto cadastrarCargo(CargoCadastroDto cargo){
        Cargo cargoNovo = cargoRepository.save(new Cargo(cargo));
        return new CargoDto(cargoNovo);
    }
    public CargoDto editarCargo(CargoDto cargo){
        if(cargoRepository.existsById(cargo.id())){
            Cargo cargoAlterado = new Cargo(cargo);
            cargoRepository.save(cargoAlterado);
            return new CargoDto(cargoAlterado);
        }
        throw new NoSuchElementException("O cargo informado não pôde ser alterado porque não existe");
    }

    public void excluirCargoPorId(Long id){
        cargoRepository.deleteById(id);
    }

}
