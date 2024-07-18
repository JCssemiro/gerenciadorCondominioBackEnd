package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.dtos.TorreCadastroDto;
import cassemiro.juan.seucondominio.dtos.TorreDto;
import cassemiro.juan.seucondominio.models.Torre;
import cassemiro.juan.seucondominio.repositories.TorreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TorreService {

    @Autowired
    private TorreRepository torreRepository;

    public List<TorreDto> listarTodasTorres(){
        return torreRepository.findAll().stream().map(TorreDto::new).toList();
    }

    public TorreDto listarTorrePorId(Long id){
        Torre torre = torreRepository.findById(id).orElseThrow(()->new NoSuchElementException("Torre não encontrada!"));
        return new TorreDto(torre);
    }

    public TorreDto cadastrarTorre(TorreCadastroDto dto){

        Torre torreNova = torreRepository.save(new Torre(dto));
        return new TorreDto(torreNova);
    }

    public TorreDto editarTorre(TorreDto dto){
        if(torreRepository.existsById(dto.id())){
            Torre torreAlterada = new Torre(dto);
            torreRepository.save(torreAlterada);
            return new TorreDto(torreAlterada);
        }
        throw new NoSuchElementException("A torre não pôde ser alterada!");
    }

    public void excluirTorrePorId(Long id){
        torreRepository.deleteById(id);
    }

}
