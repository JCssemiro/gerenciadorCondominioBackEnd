package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.VisitanteCadastroDto;
import cassemiro.juan.seucondominio.dtos.VisitanteDto;
import cassemiro.juan.seucondominio.models.Visitante;
import cassemiro.juan.seucondominio.repositories.VisitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VisitanteService {

    @Autowired
    private VisitanteRepository visitanteRepository;

    public List<VisitanteDto> listarTodosVisitantes(int page){
        Pageable pageable = PageRequest.of(page,10);
        return visitanteRepository.findAll().stream().map(VisitanteDto::new).toList();
    }
    public VisitanteDto listarVisitantePorId(Long id){
        Visitante visitanteEncontrado = visitanteRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Este visitante não existe!"));
        return new VisitanteDto(visitanteEncontrado);
    }

    public VisitanteDto cadastrarVisitante(VisitanteCadastroDto dto){
        Visitante novoVisitante = new Visitante(dto);
        visitanteRepository.save(novoVisitante);
        return new VisitanteDto(novoVisitante);
    }

    public VisitanteDto editarVisitante(VisitanteDto dto){
        Visitante visitanteEditado = new Visitante(dto);
        visitanteRepository.save(visitanteEditado);
        return new VisitanteDto(visitanteEditado);
    }

}
