package cassemiro.juan.seucondominio.services;

import cassemiro.juan.seucondominio.dtos.AcessosCadastroDto;
import cassemiro.juan.seucondominio.dtos.AcessosDto;
import cassemiro.juan.seucondominio.models.Acessos;
import cassemiro.juan.seucondominio.models.Morador;
import cassemiro.juan.seucondominio.models.Visitante;
import cassemiro.juan.seucondominio.repositories.AcessosRepository;
import cassemiro.juan.seucondominio.repositories.MoradorRepository;
import cassemiro.juan.seucondominio.repositories.VisitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AcessosService {

    @Autowired
    private AcessosRepository acessosRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private VisitanteRepository visitanteRepository;

    public List<AcessosDto> listarTodosAcessos(int page){
        Pageable pageable = PageRequest.of(page,10);
        return acessosRepository.findAll(pageable).stream().map(AcessosDto::new).toList();
    }

    public AcessosDto listarAcesosPorId(Long id){
        return new AcessosDto(acessosRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Esse registro não existe!")));
    }

    public List<AcessosDto> listarTodosAcessosPorMorador(Long idMorador, int page){
        Pageable pageable = PageRequest.of(page,10);
        return acessosRepository.findAllByMoradorId(idMorador,pageable).stream().map(AcessosDto::new).toList();
    }

    public List<AcessosDto> listarTodosAcessosPorVisitante(Long idVisitante, int page){
        Pageable pageable = PageRequest.of(page,10);
        return acessosRepository.findAllByVisitanteId(idVisitante,pageable).stream().map(AcessosDto::new).toList();
    }

    public AcessosDto cadastrarNovoAcesso(AcessosCadastroDto dto){
        Morador morador = moradorRepository.findById(dto.idMorador())
                .orElseThrow(()-> new NoSuchElementException("Este morador não existe!"));
        Visitante visitante = visitanteRepository.findById(dto.idVisitante())
                .orElseThrow(()-> new NoSuchElementException("Este visitante não está cadastrado!"));
        Acessos acesso = new Acessos(morador,visitante);
        acessosRepository.save(acesso);
        return new AcessosDto(acesso);
    }

}
