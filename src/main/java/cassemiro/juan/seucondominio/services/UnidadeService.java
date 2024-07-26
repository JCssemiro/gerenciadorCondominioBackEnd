package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.UnidadeDto;
import cassemiro.juan.seucondominio.models.Torre;
import cassemiro.juan.seucondominio.models.Unidade;
import cassemiro.juan.seucondominio.repositories.TorreRepository;
import cassemiro.juan.seucondominio.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private TorreRepository torreRepository;

    public List<UnidadeDto> listarTodasUnidades(){
        return unidadeRepository.findAll().stream().map(UnidadeDto::new).toList();
    }

    public UnidadeDto listarUnidadePorId(Long id){
        return new UnidadeDto(unidadeRepository.findById(id).get());
    }

    public List<UnidadeDto> listarUnidadesPorIdTorre(Long id){
        if(torreRepository.existsById(id)) {
            return unidadeRepository.findAllByTorre(id).stream().map(UnidadeDto::new).toList();
        }
        throw new NoSuchElementException("Está torre não existe!");
    }

}
