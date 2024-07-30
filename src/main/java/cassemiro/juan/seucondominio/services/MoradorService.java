package cassemiro.juan.seucondominio.services;



import cassemiro.juan.seucondominio.dtos.MoradorCadastroDto;
import cassemiro.juan.seucondominio.dtos.MoradorDto;
import cassemiro.juan.seucondominio.models.Morador;
import cassemiro.juan.seucondominio.models.Unidade;
import cassemiro.juan.seucondominio.repositories.MoradorRepository;
import cassemiro.juan.seucondominio.repositories.TorreRepository;
import cassemiro.juan.seucondominio.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;


    public List<MoradorDto> listarTodosMoradores(int page){
        Pageable pageable = PageRequest.of(page,10);
        return moradorRepository.findAll(pageable).stream().map(MoradorDto::new).toList();
    }

    public MoradorDto listarMoradorPorId(Long id){
        return new MoradorDto(
                moradorRepository.findById(id)
                        .orElseThrow(()-> new NoSuchElementException("Este morador não existe!"))
        );
    }

    public List<MoradorDto> listarMoradoresPorUnidade(String torre, int numero){
        return moradorRepository.findAllByNumeroETorre(torre,numero).stream().map(MoradorDto::new).toList();
    }

    public List<MoradorDto> listarMoradoresPortorre(String torre){
        return moradorRepository.findAllByTorre(torre).stream().map(MoradorDto::new).toList();
    }

    public MoradorDto cadastrarMorador(MoradorCadastroDto dto){
        Unidade unidade = unidadeRepository.findById(dto.unidadeId())
                .orElseThrow(()-> new NoSuchElementException("A unidade informada não existe!"));
        Morador novoMorador = new Morador(dto,unidade);
        return new MoradorDto(moradorRepository.save(novoMorador));
    }

    public MoradorDto editarMorador(MoradorDto dto){
        if(moradorRepository.existsById(dto.id())){
            Unidade unidadeAlterada = unidadeRepository.findById(dto.unidadeId())
                    .orElseThrow(()-> new NoSuchElementException("A unidade informada não existe!"));
            Morador moradorAlterado = new Morador(dto,unidadeAlterada);
            moradorRepository.save(moradorAlterado);
            return new MoradorDto(moradorAlterado);
        }
        throw new NoSuchElementException("O Morador não pôde ser alterado!");
    }
    public void deletarMoradorPorId(Long id){moradorRepository.deleteById(id);}


}
